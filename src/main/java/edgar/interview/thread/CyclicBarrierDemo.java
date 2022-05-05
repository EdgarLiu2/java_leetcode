package edgar.interview.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：循环栅栏
 * 
 * @author liuzhao
 *
 */
public class CyclicBarrierDemo {
	
	private static void usingCyclicBarrier() {
//		CyclicBarrier barrier = new CyclicBarrier(20, () ->
//			System.out.println("满人，发车"));
				
		CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
			@Override
			public void run() {
				System.out.println("满人，发车");
			}
			
		});
		
		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

	public static void main(String[] args) {
		usingCyclicBarrier();
	}

}
