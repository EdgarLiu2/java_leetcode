package edgar.canal;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edgar.try_new.canal.ItemRedisCache;


public class TestItemRedisCache {
	private ItemRedisCache itemRedisCache;

	@Before
	public void setUp() throws Exception {
		itemRedisCache = new ItemRedisCache();
	}

	@After
	public void tearDown() throws Exception {
		itemRedisCache = null;
	}

	@Test
	public void test_setValue() {
		String itemName = "item_test";
		int itemValue = 10;
		
		itemRedisCache.setValue(itemName, itemValue);
		assertEquals(itemValue, itemRedisCache.getValue(itemName));
	}

}
