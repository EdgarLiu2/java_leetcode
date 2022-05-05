package edgar.interview.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import edgar.util.SleepUtil;

public class SemaphoreDemo {
	
	static void usingSemaphore() {
		final int WORKLOAD = 100;
		
		// 允许同时5个运行，使用非公平锁
		Semaphore semaphore = new Semaphore(5);
//		Semaphore semaphore = new Semaphore(5, true);
		AtomicInteger running = new AtomicInteger(0);
		AtomicInteger waiting = new AtomicInteger(0);
		
		
		for (int i = 0; i < WORKLOAD; i++) {
			new Workload(i, semaphore, running, waiting).start();
		}
	}

	public static void main(String[] args) {
		usingSemaphore();
	}

}

class Workload extends Thread {
	
	private int threadId;
	private Semaphore semaphore;
	private AtomicInteger running;
	private AtomicInteger waiting;
	
	
	Workload(int id, Semaphore semaphore, AtomicInteger running, AtomicInteger waiting) {
		this.threadId = id;
		this.semaphore = semaphore;
		this.running = running;
		this.waiting = waiting;
		
		waiting.incrementAndGet();
	}

	@Override
	public void run() {

		try {
			this.semaphore.acquire();
			
			this.running.incrementAndGet();
			this.waiting.decrementAndGet();
			System.out.printf("Thread-%d is running, running=%d waiting=%d\n", this.threadId, this.running.get(), this.waiting.get());
			SleepUtil.milliSleep(30);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.running.decrementAndGet();
			this.semaphore.release();
		}
	}
	
}
