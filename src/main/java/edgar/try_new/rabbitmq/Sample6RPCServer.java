package edgar.try_new.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import edgar.util.RabbitMQUtil;

public class Sample6RPCServer {
	
	public static final String QUEUE_CONFIG_KEY = "edgar.rabbitmq.queue3";
	
	private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

	public static void main(String[] args) throws IOException {
		RabbitMQUtil rabbitMQ = new RabbitMQUtil(QUEUE_CONFIG_KEY);
		String queueName = rabbitMQ.getChannelName();

		Channel channel = rabbitMQ.createQueueChannel(true);
		System.out.println(" [x] Awaiting RPC requests");
		Object monitor = new Object();
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			AMQP.BasicProperties replyProps = new AMQP.BasicProperties()
					.builder()
					.correlationId(delivery.getProperties().getCorrelationId())
					.build();
			
			String response = "";
			
			try {
				String message = new String(delivery.getBody(), "UTF-8");
				int n = Integer.parseInt(message);
				System.out.printf(" [.] fib(%s)\n", message);
				response += fib(n);
			} catch (RuntimeException  e) {
				System.out.println(" [.] " + e.toString());
			} finally {
				// When a request appears, it does the job and sends a message with the result back to the Client, using the queue from the replyTo field.
				channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
				
				// RabbitMq consumer worker thread notifies the RPC server owner thread
				synchronized (monitor) {
					monitor.notify();
				}
			}
		};
		
		channel.basicConsume(queueName, rabbitMQ.isAutoAck(), deliverCallback, consumerTag -> {});
		// Wait and be prepared to consume the message from RPC client.
		while (true) {
			synchronized (monitor) {
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
