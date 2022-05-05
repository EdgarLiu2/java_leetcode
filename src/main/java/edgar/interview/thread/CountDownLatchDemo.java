package edgar.interview.thread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：倒数门栓
 * 
 * @author liuzhao
 *
 */
public class CountDownLatchDemo {
	
	private static void usingCountDownLatch() {
		final int MAX = 100;
		Thread[] threads = new Thread[MAX];
		CountDownLatch latch = new CountDownLatch(threads.length);
		
		for (int i = 0; i < MAX; i++) {
			threads[i] = new Thread(() -> {
				int result = 0;
				for (int j = 0; j < 10000; j++) {
					result += j;
				}
				System.out.println(String.format("result=%d", result));
				
				latch.countDown();
			});
		}
		
		for (int i = 0; i < MAX; i++) {
			threads[i].start();
		}
		
		try {
			latch.await();
			System.out.println("return from latch.await()");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		usingCountDownLatch();
	}

}
