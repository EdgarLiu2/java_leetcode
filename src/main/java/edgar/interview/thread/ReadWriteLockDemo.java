package edgar.interview.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import edgar.util.SleepUtil;

public class ReadWriteLockDemo {
	
	static int sharedValue = 0;
	
	static void usingReadWriteLock() {
		Random rand = new Random();
		final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		
		
		
		// 启动2个线程写数据
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					Lock writeLock = readWriteLock.writeLock();
					
					for (int j = 0; j < 10; j++) {
						try {
							writeLock.lock();
							sharedValue = rand.nextInt(1000);
							System.out.println("write over! sharedValue = " + sharedValue);
						} finally {
							writeLock.unlock();
						}
					
						SleepUtil.milliSleep(rand.nextInt(1000));
					}
				}
				
			}, "Writer-" + i).start();
		}
		
		// 启动18个线程读数据
		for (int i = 0; i < 18; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					Lock readLock = readWriteLock.readLock();
					
					for (int j = 0; j < 10; j++) {
						try {
							readLock.lock();
							System.out.println("read over! sharedValue=" + sharedValue);
						} finally {
							readLock.unlock();
						}
						
						SleepUtil.milliSleep(rand.nextInt(1000));
					}
				}
				
			}, "Reader-" + i).start();
		}
	}

	public static void main(String[] args) {
		usingReadWriteLock();
	}

}
