package edgar.interview.collection;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import edgar.util.SleepUtil;

public class QueueDemo {
	
	private static void testConcurrentLinkedQueue() {
		Queue<String> queue = new ConcurrentLinkedQueue<>();
		
		// 添加元素，成功返回true，失败返回false
		queue.offer("abc");
		// 添加元素，成功返回true，失败抛异常
		queue.add("abc");
		// 取出元素，不remove。集合空时返回null。
		queue.peek();
		// 取出元素，并remove。集合空时返回null。
		queue.poll();
	}
	
	private static void testLinkedBlockingQueue() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<>();
		Random r = new Random();
		
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					// 往里面装，如果队列满了线程会阻塞
					queue.put("a" + r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				while(!queue.isEmpty()) {
					try {
						// 往外取，如果队列空了线程会阻塞
						System.out.printf("%s take %s\n", Thread.currentThread().getName(), queue.take());
						SleepUtil.milliSleep(r.nextInt(100));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "thread-" + i).start();
		}
	}
	
	private static void testArrayBlockingQueue() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
		Random r = new Random();
		
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					// 往里面装，如果队列满了线程会阻塞
//					queue.put("a" + r.nextInt(1000));
					// 往里面装，如果队列满了会抛异常
					queue.add("a" + r.nextInt(1000));
					// 往里面装，成功返回true，失败返回false
//					boolean isSuccess = queue.offer("a" + r.nextInt(1000));
					// 往里面装，成功返回true，失败返回false。加超时
//					boolean isSuccess = queue.offer("a" + r.nextInt(1000), 1, TimeUnit.SECONDS);
				} catch (IllegalStateException e) {
					System.out.println("BlockingQueue is full - " + e.getMessage());
				}
			}
		}).start();
		
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				while(!queue.isEmpty()) {
					try {
						// 往外取，如果队列空了线程会阻塞
						System.out.printf("%s take %s\n", Thread.currentThread().getName(), queue.take());
						SleepUtil.milliSleep(r.nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "thread-" + i).start();
		}
	}
	private static void testDelayQueue() {
		BlockingQueue<DelayQueueTask> queue = new PriorityBlockingQueue<>();
		long now = System.currentTimeMillis();
		Random r = new Random();
		
		for (int i = 0; i < 10; i++) {
			String taskName  = "task-" + i;
			long runningTime = r.nextInt(5000);
			
			DelayQueueTask task = new DelayQueueTask(taskName, runningTime);
			queue.offer(task);
		}

		System.out.println(queue);
		
		for (int i = 0; i < 10; i++) {
			System.out.println(queue.poll());
		}
	}
	
	private static void testPriorityQueue() {
		Queue<String> queue = new PriorityQueue<>();
		
		queue.add("c");
		queue.add("e");
		queue.add("a");
		queue.add("d");
		queue.add("z");
		
		for (int i = 0; i < 5; i++) {
			System.out.println(queue.poll());
		}
	}
	
	private static void testSynchronousQueue() {
		BlockingQueue<String> queue = new SynchronousQueue<>();
		
		new Thread(() -> {
			try {
				System.out.println("读线程 take() = " + queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		try {
			queue.put("aaa");
			System.out.println("写线程 put() = aaa");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void testTransferQueue() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		
		new Thread(() -> {
			System.out.println("Sleep 2s ...");
			SleepUtil.secondSleep(2);
			
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		System.out.println("start - transfer(aaa)");
		try {
			queue.transfer("aaa");
			System.out.println("end - transfer(aaa)");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		testConcurrentLinkedQueue();
//		testLinkedBlockingQueue();
//		testArrayBlockingQueue();
//		testDelayQueue();
//		testPriorityQueue();
//		testSynchronousQueue();
		testTransferQueue();
	}

}

class DelayQueueTask implements Delayed {
	
	private String name;
	private long runningTime;
	
	public DelayQueueTask(String name, long runningTime) {
		this.name = name;
		this.runningTime = runningTime;
	}

	@Override
	public int compareTo(Delayed o) {
		if (getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
			return -1;
		} else if (getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
			return 1;
		}
		
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		return "DelayQueueTask [name=" + name + ", runningTime=" + runningTime + "]";
	}
	
	
}