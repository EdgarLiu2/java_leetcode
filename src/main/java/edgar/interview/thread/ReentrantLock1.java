package edgar.interview.thread;

import java.util.concurrent.TimeUnit;

public class ReentrantLock1 {
	
	synchronized void m1() {
		for (int i = 0; i < 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(i);
			if (i== 2) {
				m2();
			}
		}
	}
	
	synchronized void m2() {
		System.out.println("m2 ...");
	}

	public static void main(String[] args) {
		ReentrantLock1 r1 = new ReentrantLock1();
		new Thread(r1::m1).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		r1.m2();
	}

}
