package edgar.try_new.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import edgar.util.RabbitMQUtil;

public class Sample3Subscriber {
	
	public static final String EXCHANGE_CONFIG_KEY = "edgar.rabbitmq.exchange1";

	public static void main(String[] args) throws IOException, TimeoutException {
		RabbitMQUtil rabbitMQ = new RabbitMQUtil(EXCHANGE_CONFIG_KEY);

		Channel channel = rabbitMQ.createExchangeReceiverChannel();
		String queueName = rabbitMQ.getExchangeBindedQueueName(channel);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        System.out.println(" [x] Received '" + message + "'");
	    };
	    channel.basicConsume(queueName, rabbitMQ.isAutoAck(), deliverCallback, consumerTag -> {});
	}

}
