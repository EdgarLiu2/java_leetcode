package edgar.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

public class RabbitMQUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private PropertiesUtil props;
	private String env;
	private String channelConfigKey;
	
	private String host;
	private String username;
	private String passname;
	private Connection connection = null;
	
	private Map<Channel, String> exchangeBindedQueues = new HashMap<Channel, String>();

	public RabbitMQUtil(String channelConfigKey) {
		this(channelConfigKey, System.getProperty("ENV", "dev"));
	}
	
	public RabbitMQUtil(String channelConfigKey, String env) {
		this.channelConfigKey = channelConfigKey;
		this.env = env;
		
		initConnection();
	}
	
	private void initConnection() {
		props = new PropertiesUtil(this.env);
		this.host = props.getProperty("edgar.rabbitmq.host");
		this.username = props.getProperty("edgar.rabbitmq.user");
		this.passname = props.getProperty("edgar.rabbitmq.pass");
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(this.host);
		factory.setUsername(this.username);
		factory.setPassword(this.passname);
		try {
			connection = factory.newConnection();
			logger.info("Established connection to RabbitMQ server on {}", this.host);
		} catch (IOException e) {
			logger.error("Failed to open connection to RabbitMQ server on " +  this.host, e);
		} catch (TimeoutException e) {
			logger.error("Failed to open connection to RabbitMQ server on " +  this.host, e);
		}
	}
	
	public Channel createQueueChannel() {
		return createQueueChannel(false);
	}
	
	public Channel createQueueChannel(boolean isAutoPurge) {
		Channel channel = null;
		String queue_config_key = this.channelConfigKey;
		String queueName = getChannelName();
		boolean durable = props.getBooleanProperty(queue_config_key + ".durable");
		int prefetchCount = props.getIntProperty(queue_config_key + ".prefetchCount");
		
		try {
			channel = connection.createChannel();
			
			// declare a queue for us to send to, RabbitMQ will never lose our queue
			channel.queueDeclare(queueName, durable, false, false, null);
			
			if (isAutoPurge) {
				channel.queuePurge(queueName);
			}
			
			// Fair dispatch, don't dispatch a new message to a worker until it has processed and acknowledged the previous one
			if (prefetchCount > 0) {
				channel.basicQos(prefetchCount);
			}
			
			logger.info("Created new channel to queue={}, durable={}, prefetchCount={}", queueName, durable, prefetchCount);
		} catch (IOException e) {
			logger.error("Failed to create a channel to queue: " +  queueName, e);
		}
		
		return channel;
	}
	
	public Channel createExchangeSenderChannel() {
		Channel channel = null;
		String exchange_config_key = this.channelConfigKey;
		String exchangeName = getChannelName();
		String exchangeType = props.getProperty(exchange_config_key + ".type");
		
		try {
			channel = connection.createChannel();
			channel.exchangeDeclare(exchangeName, exchangeType);
			
			logger.info("Created new channel to exchange={}, type={}", exchangeName, exchangeType);
		} catch (IOException e) {
			logger.error("Failed to create a channel to exchange: " +  exchangeName, e);
		}
		
		return channel;
	}
	
	public Channel createExchangeReceiverChannel() {
		return createExchangeReceiverChannel(null);
	}
	
	public Channel createExchangeReceiverChannel(String subTag) {
		Channel channel  = createExchangeSenderChannel();
		String exchangeName = getChannelName();
		String temporaryQueueName = "N/A";
		
		try {
			// need a fresh, empty queue with a random name, once we disconnect the consumer the queue should be automatically deleted.
			// when we supply no parameters to queueDeclare() we create a non-durable, exclusive, autodelete queue with a generated name
			temporaryQueueName = channel.queueDeclare().getQueue();
			
			// tell the exchange to send messages to our queue
			if (subTag == null || subTag.isEmpty()) {
				// no routingKey
				channel.queueBind(temporaryQueueName, exchangeName, "");
				logger.info("Binded queue={} to exchange={}", temporaryQueueName, exchangeName);
			} else {
				// have bindingKey;
				List<String> bindingKeys = getExchangeBindingKeys(subTag);
				for (String bindingKey : bindingKeys) {
					channel.queueBind(temporaryQueueName, exchangeName, bindingKey);
				}
				logger.info("Binded queue={} to exchange={}, with bindingKeys={}", temporaryQueueName, exchangeName, bindingKeys);
			}
			
			exchangeBindedQueues.put(channel, temporaryQueueName);
			
		} catch (IOException e) {
			logger.error("Failed to bind a queue=" + temporaryQueueName + " to exchange=" +  exchangeName, e);
		}
		
		return channel;
	}
	
	public void closeConnection() {
		try {
			connection.close();
			logger.info("Close RabbitMQ connection to {}", this.host);
		} catch (IOException e) {
			logger.error("Failed to close RabbitMQ connection", e);
		}
	}
	
	public String getChannelName() {
		return props.getProperty(this.channelConfigKey);
	}
	
	public boolean isAutoAck() {
		return props.getBooleanProperty(this.channelConfigKey + ".autoAck");
	}
	
	public String getExchangeBindedQueueName(Channel channel) {
		return exchangeBindedQueues.get(channel);
	}
	
	public void removeExchangeBindedQueue(Channel channel) {
		exchangeBindedQueues.remove(channel);
	}
	
	public List<String> getExchangeBindingKeys(String subTag) {
		String full_config_key = String.format("%s.%s.%s", this.channelConfigKey, subTag, "bindingKeys");
		return getExchangeKeys(full_config_key);
	}
	
	public List<String> getExchangeRoutingKeys(String subTag) {
		String full_config_key = String.format("%s.%s.%s", this.channelConfigKey, subTag, "routingKeys");
		return getExchangeKeys(full_config_key);
	}
	
	private List<String> getExchangeKeys(String full_config_key) {
		List<String> keys = new ArrayList<String>();
		String allKeys = props.getProperty(full_config_key);
		
		if(!allKeys.isEmpty()) {
			for (String key : allKeys.split(",")) {
				keys.add(key.trim());
			}
		}
		
		return keys;
	}
}
