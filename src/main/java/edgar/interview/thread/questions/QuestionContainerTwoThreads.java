package edgar.interview.thread.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

import edgar.util.SleepUtil;

/**
 * 实现一个容器，提供两个方法add()和size()，写两个线程：
 * 线程1：添加10个元素到容器中
 * 线程2：实时监控元素个数，当个数到5个时，线程2给出提示并退出
 * 
 * @author liuzhao
 *
 */
public class QuestionContainerTwoThreads {

	public static void main(String[] args) {
//		new Solution1_VolatileNotify().solve();
//		new Solution2_CountDownLatch().solve();
		new Solution3_LockSupport().solve();
	}

}

/**
 * volatile + notify/wait
 * 
 * @author liuzhao
 *
 */
class Solution1_VolatileNotify {
	
	private volatile List<Integer> list = new ArrayList<>();
	
	public void add(Integer i) {
		list.add(i);
	}
	
	public int size() {
		return list.size();
	}
	
	public void solve() {
		final Object lock = new Object();
		
		Thread t1 = new Thread(() -> {
			System.out.println("T1 启动");
			Random r = new Random();
			
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					System.out.println("T1: list.add()");
					list.add(0);
					SleepUtil.milliSleep(r.nextInt(50));
					
					if (list.size() == 5) {
						try {
							lock.notify();
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			System.out.println("T1 结束");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.println("T2 启动");
			
			synchronized (lock) {
				if (list.size() != 5) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// t2被t1唤醒，输出，并唤醒t1
				System.out.println("T2: list.size() = " + list.size());
				lock.notify();
			}
			
			System.out.println("T2 结束");
		});
		
		// 先启动t2，再启动t1
		t2.start();
		t1.start();
	}
}

class Solution2_CountDownLatch {
	
	private volatile List<Integer> list = new ArrayList<>();
	
	public void add(Integer i) {
		list.add(i);
	}
	
	public int size() {
		return list.size();
	}
	
	public void solve() {
		final CountDownLatch latch = new CountDownLatch(1);
		
		Thread t1 = new Thread(() -> {
			System.out.println("T1 启动");
			Random r = new Random();
			
			for (int i = 0; i < 10; i++) {
				System.out.println("T1: list.add()");
				list.add(0);
				SleepUtil.milliSleep(r.nextInt(50));
				
				if (list.size() == 5) {
					// 打开门栓，让t2运行
					latch.countDown();
					try {
						// 给t1上门栓，给t2运行的机会
						latch.await();
						// 上面代码没有用，加sleep
						SleepUtil.milliSleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			System.out.println("T1 结束");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.println("T2 启动");
			
			if (list.size() != 5) {
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// t2被t1唤醒，输出，并唤醒t1
			System.out.println("T2: list.size() = " + list.size());

			System.out.println("T2 结束");
		});
		
		// 先启动t2，再启动t1
		t2.start();
		t1.start();
	}
}

class Solution3_LockSupport {
	
	private volatile List<Integer> list = new ArrayList<>();
	private Thread t1, t2;
	
	public void add(Integer i) {
		list.add(i);
	}
	
	public int size() {
		return list.size();
	}
	
	public void solve() {
		
		t1 = new Thread(() -> {
			System.out.println("T1 启动");
			Random r = new Random();
			
			for (int i = 0; i < 10; i++) {
				System.out.println("T1: list.add()");
				list.add(0);
				SleepUtil.milliSleep(r.nextInt(50));
				
				if (list.size() == 5) {
					// 让t2运行
					LockSupport.unpark(t2);
					// t1停止运行，等t2唤醒
					LockSupport.park();
				}
			}
			
			System.out.println("T1 结束");
		});
		
		t2 = new Thread(() -> {
			System.out.println("T2 启动");
			
			if (list.size() != 5) {
				// t2停止运行，等t1唤醒
				LockSupport.park();
			}
			
			// t2被t1唤醒，输出，并唤醒t1
			System.out.println("T2: list.size() = " + list.size());
			// 唤醒t1
			LockSupport.unpark(t1);

			System.out.println("T2 结束");
		});
		
		// 先启动t2，再启动t1
		t2.start();
		t1.start();
	}
}