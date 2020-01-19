package edgar.try_new.rabbitmq;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import edgar.util.RabbitMQUtil;

public class Sample6RPCClient implements AutoCloseable {
	
	public static final String QUEUE_CONFIG_KEY = "edgar.rabbitmq.queue3";
	private RabbitMQUtil rabbitMQ;
	private String requestQueueName;
	private Channel channel;
	
	public Sample6RPCClient() {
		this.rabbitMQ = new RabbitMQUtil(QUEUE_CONFIG_KEY);
		this.requestQueueName = this.rabbitMQ.getChannelName();
		this.channel = this.rabbitMQ.createQueueChannel();
	}

	@Override
	public void close() throws IOException {
		this.rabbitMQ.closeConnection();
	}
	
	public String call(String message) throws IOException, InterruptedException {
		final String corrId = UUID.randomUUID().toString();
		
		String replyQueueName = this.channel.queueDeclare().getQueue();
		AMQP.BasicProperties props = new AMQP.BasicProperties
				.Builder()
//				.deliveryMode()						// Marks a message as persistent (with a value of 2) or transient (any other value). MessageProperties.PERSISTENT_TEXT_PLAIN
//				.contentType(contentType)	// Used to describe the mime-type of the encoding. For example for the often used JSON encoding it is a good practice to set this property to: application/json.
				.correlationId(corrId)				// set to a unique value for every request, useful to correlate RPC responses with requests
				.replyTo(replyQueueName)	// set to a anonymous exclusive queue created just for the request, commonly used to name a callback queue
				.build();
		this.channel.basicPublish("", this.requestQueueName, props, message.getBytes("UTF-8"));
		
		// a dedicated exclusive queue for the reply, with capacity set to 1 as we need to wait for only one response.
		final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			// If we see an unknown correlationId value, we may safely discard the message
			if (delivery.getProperties().getCorrelationId().equals(corrId)) {
				// If it matches the value from the request it returns the response to the application.
				response.offer(new String(delivery.getBody(), "UTF-8"));
			}
		};
		String ctag = this.channel.basicConsume(replyQueueName, rabbitMQ.isAutoAck(), deliverCallback, consumerTag -> {});
		
		// Since our consumer delivery handling is happening in a separate thread, we're going to need something to suspend the main thread before the response arrives. Usage of BlockingQueue is one possible solutions to do so.
		String result = response.take();
		channel.basicCancel(ctag);
		
		return result;
	}

	
	public static void main(String[] args) {
		try (Sample6RPCClient fibonacciRpc  = new Sample6RPCClient()) {
			for (int i = 0; i < 32; i++) {
				String i_str = Integer.toString(i);
				System.out.printf(" [x] Requesting fib(%s)\n", i_str);
				
				String response = fibonacciRpc.call(i_str);
				System.out.printf(" [.] Got '%s'\n", response);
			}
		} catch (IOException  | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
