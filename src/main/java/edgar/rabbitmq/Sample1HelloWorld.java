package edgar.rabbitmq;

import com.rabbitmq.client.DeliverCallback;

import edgar.util.RabbitMQUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

/**
 * @author Administrator
 *
 */
public class Sample1HelloWorld {
	
	public static final String QUEUE_CONFIG_KEY = "edgar.rabbitmq.queue1";

	public static void sendMessage() throws IOException, TimeoutException {
		
		RabbitMQUtil rabbitMQ = new RabbitMQUtil(QUEUE_CONFIG_KEY);
		String queueName = rabbitMQ.getChannelName();

		try (Channel channel = rabbitMQ.createQueueChannel()) {

			// publish a message to the queue
			String message = "Hello World!";
			channel.basicPublish("", queueName, null, message.getBytes());
			System.out.printf(" [x] Sent '%s'\n", message);
		}
		
		rabbitMQ.closeConnection();
	}
	
	public static void receiveMessage() throws IOException, TimeoutException {
	
		RabbitMQUtil rabbitMQ = new RabbitMQUtil(QUEUE_CONFIG_KEY);
		String queueName = rabbitMQ.getChannelName();
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		Channel channel = rabbitMQ.createQueueChannel();
		// provide a callback in the form of an object that will buffer the messages until we're ready to use them
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.printf(" [x] Received '%s'\n", message);
		};
		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			sendMessage();
			
			receiveMessage();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}

	}

}
