package edgar.interview.collection;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class MapDemo {
	
	private static void testConcurrentMap() {
		Map<String, String> map = new ConcurrentHashMap<>();
		Random r = new Random();
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);
		
		for (int i = 0; i < threads.length; i++) {
			if (i % 2 == 0) {
				threads[i] = new Thread(() -> {
					// 50个写线程
					for (int j = 0; j < 10000; j++) {
						int value = r.nextInt(100000);
						map.put("a" + value, "a" + value);
					}
					latch.countDown();
				});
			} else {
				threads[i] = new Thread(() -> {
					// 50个读线程
					for (int j = 0; j < 10000; j++) {
						int value = r.nextInt(100000);
						map.get("a" + value);
					}
					latch.countDown();
				});
			}
		}
		
		long start = System.currentTimeMillis();
		Arrays.asList(threads).forEach(t -> t.start());
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		System.out.printf("processTime=%d(ms) map.size()=%d\n", end - start, map.size());
	}

	private static void testConcurrentSkipListMap() {
		Map<String, String> map = new ConcurrentSkipListMap<>();
		Random r = new Random();
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);
		
		for (int i = 0; i < threads.length; i++) {
			if (i % 2 == 0) {
				threads[i] = new Thread(() -> {
					// 50个写线程
					for (int j = 0; j < 10000; j++) {
						int value = r.nextInt(100000);
						map.put("a" + value, "a" + value);
					}
					latch.countDown();
				});
			} else {
				threads[i] = new Thread(() -> {
					// 50个读线程
					for (int j = 0; j < 10000; j++) {
						int value = r.nextInt(100000);
						map.get("a" + value);
					}
					latch.countDown();
				});
			}
		}
		
		long start = System.currentTimeMillis();
		Arrays.asList(threads).forEach(t -> t.start());
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		System.out.printf("processTime=%d(ms) map.size()=%d\n", end - start, map.size());
	}
	
	public static void main(String[] args) {
//		testConcurrentMap();
		testConcurrentSkipListMap();
	}

}
