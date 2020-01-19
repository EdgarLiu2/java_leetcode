package edgar.try_new.rabbitmq;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

import edgar.util.RabbitMQUtil;

public class Sample5TopicPublisher {

	public static final String EXCHANGE_CONFIG_KEY = "edgar.rabbitmq.exchange3";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		RabbitMQUtil rabbitMQ = new RabbitMQUtil(EXCHANGE_CONFIG_KEY);
		String exchangeName = rabbitMQ.getChannelName();

		try (Channel channel = rabbitMQ.createExchangeSenderChannel()) {
			// publish a message to the queue
			String message = "A critical kernel error";
			List<String> routingKeys = rabbitMQ.getExchangeRoutingKeys("sender");
			
			for (String routingKey : routingKeys) {
				channel.basicPublish(exchangeName, routingKey, null, message.getBytes("UTF-8"));
		        System.out.printf(" [x] Sent '%s' with routingKey=%s\n", message, routingKey);
			}
		}

		rabbitMQ.closeConnection();
	}
}
