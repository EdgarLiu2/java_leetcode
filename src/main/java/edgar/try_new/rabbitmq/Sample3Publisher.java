package edgar.try_new.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

import edgar.util.RabbitMQUtil;

public class Sample3Publisher {

	public static final String EXCHANGE_CONFIG_KEY = "edgar.rabbitmq.exchange1";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		RabbitMQUtil rabbitMQ = new RabbitMQUtil(EXCHANGE_CONFIG_KEY);
		String exchangeName = rabbitMQ.getChannelName();

		try (Channel channel = rabbitMQ.createExchangeSenderChannel()) {
			// publish a message to the queue
			String message = "info: Hello World!";
			
			channel.basicPublish(exchangeName, "", null, message.getBytes("UTF-8"));
	        System.out.println(" [x] Sent '" + message + "'");
		}
		
		rabbitMQ.closeConnection();
	}

}
