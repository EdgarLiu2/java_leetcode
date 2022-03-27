package edgar.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedByKey {

	private Map<String, Object> mutexCache = new ConcurrentHashMap<>();
	
	public void run(String key, Runnable statement) {
		Object mutex4Key = mutexCache.computeIfAbsent(key, k -> new Object());
		
		synchronized (mutex4Key) {
			try {
				statement.run();
			} finally {
				mutexCache.remove(mutex4Key);
			}
		}
	}
}
