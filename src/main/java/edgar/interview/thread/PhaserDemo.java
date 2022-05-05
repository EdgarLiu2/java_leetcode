package edgar.interview.thread;

import java.util.Random;
import java.util.concurrent.Phaser;

import edgar.util.SleepUtil;

public class PhaserDemo {

	public static void main(String[] args) {
		MarriagePhaser phaser = new MarriagePhaser();
		phaser.bulkRegister(10);

		// 8个嘉宾
		for (int i = 0; i < 8; i++) {
			new Thread(new MarriagePerson("thread-" + i, phaser)).start();
		}
		
		new Thread(new MarriagePerson("新郎", phaser)).start();
		new Thread(new MarriagePerson("新娘", phaser)).start();
	}

}

class MarriagePhaser extends Phaser {

	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch (phase) {
		case 0:
			System.out.printf("所有人都到齐了！ %d\n", registeredParties);
			return false;
		case 1:
			System.out.printf("所有人都吃完了！ %d\n", registeredParties);
			return false;
		case 2:
			System.out.printf("所有人都离开了！ %d\n", registeredParties);
			return false;
		case 3:
			System.out.printf("婚礼结束！新郎新娘入洞房 %d\n", registeredParties);
			return true;
		default:
			return true;
		}
	}
}

class MarriagePerson implements Runnable {
	private static Random rand = new Random();
	
	private Phaser phaser;
	private String name;
	
	public MarriagePerson(String name, Phaser phaser) {
		this.name = name;
		this.phaser = phaser;
	}
	
	public void arrive() {
		SleepUtil.milliSleep(rand.nextInt(1000));
		System.out.printf("%s 到达现场\n", this.name);
		phaser.arriveAndAwaitAdvance();
	}
	
	public void eat() {
		SleepUtil.milliSleep(rand.nextInt(1000));
		System.out.printf("%s 吃完\n", this.name);
		phaser.arriveAndAwaitAdvance();
	}
	
	public void leave() {
		SleepUtil.milliSleep(rand.nextInt(1000));
		System.out.printf("%s 离开\n", this.name);
		phaser.arriveAndAwaitAdvance();
	}
	
	public void hug() {
		if ("新郎".equals(name) || "新娘".equals(name)) {
			SleepUtil.milliSleep(rand.nextInt(1000));
			System.out.printf("%s 抱抱\n", this.name);
			phaser.arriveAndAwaitAdvance();
		} else {
			phaser.arriveAndDeregister();
		}
		
	}

	@Override
	public void run() {
		arrive();
		eat();
		leave();
		hug();
	}
	
}