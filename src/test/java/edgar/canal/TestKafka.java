package edgar.canal;

import static org.junit.Assert.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edgar.util.KafkaUtil;

public class TestKafka {

	private KafkaUtil kafka;
	public final static String TOPIC_CONFIG_KEY  = "edgar.kafka.test_topic";
	private String topicName;
	public final static String KEY_PREFIX = "MyKey-";
	public final static String MESSAGE_PREFIX = "hello KafkaProducer message ";
	
	@Before
	public void setUp() throws Exception {
		kafka = new KafkaUtil(TOPIC_CONFIG_KEY);
		topicName = kafka.getTopicName();
	}
	
	@After
	public void tearDown() throws Exception {
		kafka.close();
		kafka = null;
	}
	
	@Test
	public void test_produceTenMessages() {
		for(int i = 0; i < 10; i++) {
			String data = MESSAGE_PREFIX + i;
			kafka.sendMessage(topicName, KEY_PREFIX + i, data);
		}
	}
	
	@Test
	public void test_consumeTenMessages() {
		ConsumerRecords<String, String> records = kafka.consumeMessages();
		
		for (ConsumerRecord<String, String> record : records) {
			System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
			
			assertTrue(record.key().startsWith(KEY_PREFIX));
			assertTrue(record.value().startsWith(MESSAGE_PREFIX));
		}
	}
}
