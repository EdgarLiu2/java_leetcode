/**
 * 
 */
package edgar.canal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * @author Administrator
 *
 */
public class ItemRedisCache {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String redis_host;
	private int redis_port;
	private Jedis redis_connection;
	private static String ITEM_REDIS_KEYBASE = "leetcode:canal:item"; 
			

	public ItemRedisCache() {
		this("localhost", 6379);
	}
	
	public ItemRedisCache(String host, int port) {
		this.redis_host = host;
		this.redis_port = port;
		
		connect();
	}
	
	private void connect() {
		redis_connection = new Jedis(this.redis_host, this.redis_port);
		logger.info("Connected Redis Server: {}:{}", this.redis_host, this.redis_port);
	}
	
	private String getItemKey(String itemName) {
		return String.format("%s:%s", ITEM_REDIS_KEYBASE, itemName);
	}
	
	public void setValue(String itemName, int itemValue) {
		setValue(itemName, String.valueOf(itemValue));
	}
	
	public void setValue(String itemName, String itemValue) {
		redis_connection.set(getItemKey(itemName), itemValue);
		logger.info("Updated value of {} to {}", itemName, itemValue);
	}
	
	public int getValue(String itemName) {
		String value = redis_connection.get(getItemKey(itemName));
		return Integer.parseInt(value);
	}
}
