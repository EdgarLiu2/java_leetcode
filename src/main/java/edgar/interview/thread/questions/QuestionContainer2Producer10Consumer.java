package edgar.interview.thread.questions;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edgar.util.SleepUtil;

/**
 * 一个固定容量的同步容器，有put和get方法，getCount()，支持2个生产者和10个消费者线程的阻塞调用。
 * 
 * @author liuzhao
 *
 */
public class QuestionContainer2Producer10Consumer {
	
	private static void testContainerWaitNotify() {
		ContainerWaitNotify<Integer> container = new ContainerWaitNotify<>();
		
		// 启动2个生产者线程
		Random r = new Random();
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {
					int value = r.nextInt();
					container.put(value);
					System.out.printf("[Producer Thread] container.put(%d) size=%d\n", value, container.getCount());
				}
			}, "Thread-Consumer" + i).start();
		}
		
		SleepUtil.milliSleep(r.nextInt(1000));
		
		// 启动10个消费线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.printf("[Consumer Thread] container.get()=%d size=%d\n", container.get().intValue(), container.getCount());
				}
			}, "Thread-Consumer" + i).start();
		}
		
		
	}
	
	private static void testContainerCondition() {
		ContainerCondition<Integer> container = new ContainerCondition<>();
		
		// 启动2个生产者线程
		Random r = new Random();
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {
					int value = r.nextInt();
					container.put(value);
					System.out.printf("[Producer Thread] container.put(%d) size=%d\n", value, container.getCount());
				}
			}, "Thread-Consumer" + i).start();
		}
		
		SleepUtil.milliSleep(r.nextInt(1000));
		
		// 启动10个消费线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.printf("[Consumer Thread] container.get()=%d size=%d\n", container.get().intValue(), container.getCount());
				}
			}, "Thread-Consumer" + i).start();
		}
		
	}

	public static void main(String[] args) {
		testContainerWaitNotify();
		testContainerCondition();
	}
}

class ContainerWaitNotify<T> {
	private LinkedList<T> list = new LinkedList<>();
	public static final int MAX = 10;
	
	
	public synchronized void put(T t) {
		while(list.size() == MAX) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(t);
		
		// 通知所有消费者线程进行消费
		this.notifyAll();
	}
	
	public synchronized T get() {
		T t = null;
		
		while(list.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = list.removeFirst();
		
		// 通知所有生产者线程继续生产
		this.notifyAll();
		
		return t;
	}
	
	public synchronized int getCount() {
		return list.size();
	}
}

class ContainerCondition<T> {
	private LinkedList<T> list = new LinkedList<>();
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();
	public static final int MAX = 10;
	
	
	public void put(T t) {
		lock.lock();
		while(list.size() == MAX) {
			try {
				// 队列满，生产者暂停
				producer.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(t);
		
		// 通知所有消费者线程进行消费
		consumer.signalAll();
		lock.unlock();
	}
	
	public T get() {
		T t = null;
		
		lock.lock();
		while(list.isEmpty()) {
			try {
				// 队列空，消费者暂停
				consumer.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = list.removeFirst();
		
		// 通知所有生产者线程继续生产
		producer.signalAll();
		lock.unlock();
		
		return t;
	}
	
	public int getCount() {
		lock.lock();
		int size = list.size();
		lock.unlock();
		
		return size;
	}
}