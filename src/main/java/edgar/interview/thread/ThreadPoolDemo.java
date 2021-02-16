package edgar.interview.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		/*
		 *     public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
		 */
		ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
		ExecutorService fixedExecutorService = Executors.newFixedThreadPool(10);
		ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
		
		/**
		 * 推荐使用自定义
		 * 
		 */
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
		
		for (int i = 1; i <= 100; i++) {
//			cachedExecutorService.execute(new MyTask(i));
//			fixedExecutorService.execute(new MyTask(i));
//			singleExecutorService.execute(new MyTask(i));
			
			threadPoolExecutor.execute(new MyTask(i));
		}
		
	}

}

class MyTask extends Thread {
	private int i;
	
	public MyTask(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " --- " + this.i);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}