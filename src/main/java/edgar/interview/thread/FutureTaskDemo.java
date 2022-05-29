package edgar.interview.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Supplier;

import edgar.util.SleepUtil;

public class FutureTaskDemo {
	
	static void futureTaskDemo() {
		FutureTask<Integer> future = new FutureTask<>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Random r = new Random();
				SleepUtil.milliSleep(r.nextInt(500));
				return r.nextInt();
			}
			
		});
		
		new Thread(future).start();
		
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	static void completableFutureDemo() {
		// supplyAsync() 提交一个异步任务
		CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> {
			return priceTB();
		});
		CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(new Supplier<Double>() {
			@Override
			public Double get() {
				return priceTM();
			}
			
		});
		CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> {
			return priceJD();
		});
		
		// 所有任务完成后运行join
		CompletableFuture.allOf(futureTB, futureTM, futureJD).join();
		
		System.out.println("all CompletableFuture are done.");
		try {
			System.out.println(futureTB.get());
			System.out.println(futureTM.get());
			System.out.println(futureJD.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	static double priceTB() {
		SleepUtil.milliSleep(1000);
		System.out.println("priceTB() done");
		return 10;
	}
	
	static double priceTM() {
		SleepUtil.milliSleep(100);
		System.out.println("priceTM() done");
		return 20;
	}
	
	static double priceJD() {
		SleepUtil.milliSleep(150);
		System.out.println("priceJD() done");
		return 30;
	}


	public static void main(String[] args) {
		futureTaskDemo();
		completableFutureDemo();
	}

}
