/**
 * 
 */
package edgar.try_new.canal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * @author Administrator
 *
 */
public class ItemRedisCache {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String redisHost;
	private int redisPort;
	private Jedis redisConnection;
	private static String ITEM_REDIS_KEYBASE = "leetcode:canal:item"; 
			

	public ItemRedisCache() {
		this("localhost", 6379);
	}
	
	public ItemRedisCache(String host, int port) {
		this.redisHost = host;
		this.redisPort = port;
		
		connect();
	}
	
	private void connect() {
		redisConnection = new Jedis(this.redisHost, this.redisPort);
		logger.info("Connected Redis Server: {}:{}", this.redisHost, this.redisPort);
	}
	
	private String getItemKey(String itemName) {
		return String.format("%s:%s", ITEM_REDIS_KEYBASE, itemName);
	}
	
	public void setValue(String itemName, int itemValue) {
		setValue(itemName, String.valueOf(itemValue));
	}
	
	public void setValue(String itemName, String itemValue) {
		redisConnection.set(getItemKey(itemName), itemValue);
		logger.info("Updated value of {} to {}", itemName, itemValue);
	}
	
	public int getValue(String itemName) {
		String value = redisConnection.get(getItemKey(itemName));
		return Integer.parseInt(value);
	}
}
