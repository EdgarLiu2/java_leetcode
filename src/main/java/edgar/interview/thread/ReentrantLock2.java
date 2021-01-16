package edgar.interview.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 {
	
	Lock lock = new ReentrantLock();
	
	public void m1() {
		
		try {
			lock.lock();
			
			for (int i = 0; i < 5; i++) {
				TimeUnit.SECONDS.sleep(1);
				
				System.out.println(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) {

	}

}
