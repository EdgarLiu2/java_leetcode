package edgar.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import edgar.util.RabbitMQUtil;

/**
 * Work Queues
 * 
 * @author Administrator
 *
 */
public class Sample2Sender {
	
	public static final String QUEUE_CONFIG_KEY = "edgar.rabbitmq.queue2";

	public static void main(String[] args) throws IOException, TimeoutException {
		
		RabbitMQUtil rabbitMQ = new RabbitMQUtil(QUEUE_CONFIG_KEY);
		String queueName = rabbitMQ.getChannelName();

		try (Channel channel = rabbitMQ.createQueueChannel()) {
			// publish a message to the queue
			String message = "Hello...";
			
			// mark our messages as persistent: PERSISTENT_TEXT_PLAIN
			channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.printf(" [x] Sent '%s'\n", message);
		}
		
		rabbitMQ.closeConnection();
	}

}
