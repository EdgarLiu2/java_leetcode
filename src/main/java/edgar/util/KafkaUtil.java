package edgar.util;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaUtil {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private PropertiesUtil props;
	private String env;
	private String topicConfigKey;
	
	private String bootstrap_servers_config;
	private String group_id_config;
	private KafkaProducer<String, String> producer;
	private Consumer<String, String> consumer;

	
	public KafkaUtil(String topicConfigKey) {
		this(topicConfigKey, System.getProperty("ENV", "dev"));
	}
	
	public KafkaUtil(String topicConfigKey, String env) {
		this.env = env;
		this.topicConfigKey = topicConfigKey;
		
		initConnection();
	}
	
	private void initConnection() {
		props = new PropertiesUtil(this.env);
		this.bootstrap_servers_config = props.getProperty("edgar.kafka.servers_config");
		this.group_id_config = this.getGroupId();
				
		Properties producer_props = new Properties();
		producer_props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrap_servers_config);
		producer_props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		producer_props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
 
		try {
	        producer = new KafkaProducer<String, String>(producer_props);
	        logger.info("Created KafkaProducer to {}", this.bootstrap_servers_config);
		} catch (KafkaException e) {
			logger.error("Failed to create KafkaProducer to " +  this.bootstrap_servers_config, e);
		}
		
		Properties consumer_props = new Properties();
		consumer_props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrap_servers_config);
		consumer_props.put(ConsumerConfig.GROUP_ID_CONFIG, this.group_id_config);
		
		consumer_props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		consumer_props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		consumer_props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		consumer_props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");			// 自动commit
		consumer_props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");	// 自动commit的间隔
		consumer_props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

		try {
			consumer = new KafkaConsumer<String, String>(consumer_props);
	        logger.info("Created KafkaConsumer to {}", this.bootstrap_servers_config);
	        
	        consumer.subscribe(Arrays.asList(getTopicName()));	// 可消费多个topic,组成一个list
	        logger.info("KafkaConsumer subscribed topic: {}", this.getTopicName());
		} catch (KafkaException e) {
			logger.error("Failed to create KafkaConsumer to {}" +  this.bootstrap_servers_config, e);
		}
	}
	
	public void close() {
		if (producer != null) {
			producer.close(Duration.ofSeconds(10));
			logger.info("Closed KafkaProducer to {}", this.bootstrap_servers_config);
		}
		
		if (consumer != null) {
			consumer.close(Duration.ofSeconds(10));
			logger.info("Closed KafkaConsumer to {}", this.bootstrap_servers_config);
		}
	}
	
	public String getTopicName() {
		return props.getProperty(this.topicConfigKey);
	}
	
	public String getGroupId() {
		return props.getProperty(this.topicConfigKey + ".group_id");
	}
	
	public void sendMessage(String topic, String key, String data) {
		producer.send(new ProducerRecord<String, String>(topic, key, data));
		logger.info("Send message to topic {}", topic);
	}
	
	public ConsumerRecords<String, String> consumeMessages() {
		return consumer.poll(Duration.ofSeconds(60));
	}
}
