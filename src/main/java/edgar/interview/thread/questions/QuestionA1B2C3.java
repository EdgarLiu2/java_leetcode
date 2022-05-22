package edgar.interview.thread.questions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

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
		solution3_condition();
		solution4_blockingQueue();
		solution5_transferQueue();
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
		
		try {
			t1.join();
			t2.join();
			System.out.println("");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	static void solution2_notifywait() {
		final Object o = new Object();
		final CountDownLatch latch = new CountDownLatch(1);
		
		t1 = new Thread(() -> {
			boolean isfirstLoop = true;
			
			synchronized (o) {
				for (char c : aI) {
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
				
				o.notify();
			}
			
			
		});
		
		t2 = new Thread(() -> {
			try {
				// 保证t1先执行
				latch.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			synchronized (o) {
				for (char c : aC) {
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
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			System.out.println("");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void solution3_condition() {
		Lock lock = new ReentrantLock();
		Condition conditionT1 = lock.newCondition();
		Condition conditionT2 = lock.newCondition();
		
		t1 = new Thread(() -> {
			
			try {
				lock.lock();
				
				for (char c : aI) {
					System.out.print(c);
					
					// resume t2
					conditionT2.signal();
					
					try {
						// suspend t1
						conditionT1.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				conditionT2.signal();
			} catch (Exception e) {
				
			} finally {
				lock.unlock();
			}

		}, "t1");
		
		t2 = new Thread(() -> {
			try {
				lock.lock();
				
				for (char c : aC) {
					try {
						// suspend t2
						conditionT2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.print(c);
					
					// resume t1
					conditionT1.signal();
				}
			} catch (Exception e) {
				
			} finally {
				lock.unlock();
			}
		}, "t2");
		
		t2.start();
		t1.start();
		
		
		try {
			t1.join();
			t2.join();
			System.out.println("");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void solution4_blockingQueue() {
		BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(1);
		BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(1);
		
		t1 = new Thread(() -> {
			for (char c : aI) {
				System.out.print(c);
				
				try {
					queue2.put("queue2 run");
					queue1.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");
		
		t2 = new Thread(() -> {
			for (char c : aC) {
				try {
					queue2.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.print(c);
				
				try {
					queue1.put("queue1 run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			System.out.println("");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void solution5_transferQueue() {
		TransferQueue<Character> transferQueue = new LinkedTransferQueue<>();
		
		t1 = new Thread(() -> {
			for (char c : aI) {
				try {
					transferQueue.transfer(c);
					System.out.print(transferQueue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");
		
		t2 = new Thread(() -> {
			for (char c : aC) {
				try {
					System.out.print(transferQueue.take());
					transferQueue.transfer(c);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			System.out.println("");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
