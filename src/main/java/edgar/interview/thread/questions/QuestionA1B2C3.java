package edgar.interview.thread.questions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 一个线程输出数字，一个线程输出字母，交替输出实现输出1A2B3C4D5E6F7G
 * 
 * @author liuzhao
 *
 */
public class QuestionA1B2C3 {
	
	static Thread t1 = null;
	static Thread t2 = null;
	
	static char[] aI = "1234567".toCharArray();
	static char[] aC = "ABCDEFG".toCharArray();

	public static void main(String[] args) {
		solution1_lockSupport();
		solution2_notifywait();
	}

	static void solution1_lockSupport() {
		
		t1 = new Thread(() -> {
			for (char c : aI) {
				System.out.print(c);
				
				// resume t2
				LockSupport.unpark(t2);
				
				// suspend t1
				LockSupport.park();
			}
		}, "t1");
		
		t2 = new Thread(() -> {
			for (char c : aC) {
				// suspend t2
				LockSupport.park();
				
				System.out.print(c);
				
				// resume t1
				LockSupport.unpark(t1);
			}
		}, "t2");
		
		t1.start();
		t2.start();

		System.out.println("");
	}
	
	static void solution2_notifywait() {
		final Object o = new Object();
		final CountDownLatch latch = new CountDownLatch(1);
		
		t1 = new Thread(() -> {
			try {
				// 保证t2先执行
				latch.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			synchronized (o) {
				for (char c : aI) {
					System.out.print(c);
					
					o.notify();
					try {
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t2 = new Thread(() -> {
			boolean isfirstLoop = true;
			
			synchronized (o) {
				for (char c : aC) {
					System.out.print(c);
					if (isfirstLoop) {
						latch.countDown();
						isfirstLoop = false;
					}
					
					o.notify();
					try {
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t1.start();
		t2.start();
		
		System.out.println("");
	}
}
