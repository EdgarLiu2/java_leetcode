package edgar.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import edgar.util.RabbitMQUtil;

public class Sample2Worker {
	
	public static final String QUEUE_CONFIG_KEY = "edgar.rabbitmq.queue2";

	/**
	 * Our fake task to simulate execution time
	 * 
	 * @param task
	 * @throws InterruptedException
	 */
	private static void doWork(String task) throws InterruptedException {
		for (char ch : task.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}

	public static void main(String[] args) throws IOException, TimeoutException {
		
		RabbitMQUtil rabbitMQ = new RabbitMQUtil(QUEUE_CONFIG_KEY);
		String queueName = rabbitMQ.getChannelName();
		
		Channel channel = rabbitMQ.createQueueChannel();
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.printf(" [x] Received '%s'\n", message);
			
			try {
				doWork(message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println(" [x] Done");
				
				// send a proper acknowledgment from the worker, once we're done with a task
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		};
		
		// turn off auto acknowledgment
		channel.basicConsume(queueName, rabbitMQ.isAutoAck(), deliverCallback, consumerTag -> {});
	}
	
	

}
