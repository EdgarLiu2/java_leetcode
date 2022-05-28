package edgar.interview.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import edgar.util.SleepUtil;
import edgar.util.TimeUtil;


public class ExecutorServiceDemo {
	
	static void fixedThreadPoolDemo() {
		final int cpuCoreNum = Runtime.getRuntime().availableProcessors();
		final int batchSize = 80000;
		
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
		List<PrimeTask> taskList = new ArrayList<>();
		List<Future<List<Integer>>> futureList = new ArrayList<>();
		
		for (int i = 0; i < cpuCoreNum; i++) {
			int start = i * batchSize + 1;
			int end = (i + 1) * batchSize;
			
			
			PrimeTask task = new PrimeTask(start, end);
			taskList.add(task);
			
			Future<List<Integer>> future = service.submit(task);
			futureList.add(future);
		}
		
		long startTime = System.currentTimeMillis();
		futureList.forEach((f) -> {
			try {
				List<Integer> result = f.get();
				System.out.println("One task done, result.siz()=" + result.size());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		long endTime = System.currentTimeMillis();
		System.out.println("All tasks done: " + (endTime - startTime) + "ms");
	}
	
	static void scheduledThreadPoolDemo() {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		
		/**
		 * 第一个参数delay：第一个任务间隔多久运行
		 * 第二个参数period：每个任务之间间隔多长时间
		 */
		service.scheduleAtFixedRate(() -> {
			System.out.println(TimeUtil.getDefaultTimeString() + " " + Thread.currentThread().getName());
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	static void workStealingPoolDemo() {
		ExecutorService service = Executors.newWorkStealingPool();
		
		service.execute(new WorkStealingPoolTask(1000));
		service.execute(new WorkStealingPoolTask(2000));
		service.execute(new WorkStealingPoolTask(3000));
		service.execute(new WorkStealingPoolTask(1000));
		service.execute(new WorkStealingPoolTask(2000));
		service.execute(new WorkStealingPoolTask(3000));
		
		SleepUtil.secondSleep(4);
	}
	
	static void forkJoinPoolWithoutResult() {
		int[] nums = new int[1_000_000];
		Random r = new Random();
		for (int i = 0; i < nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		ForkJoinPool fjp = new ForkJoinPool();
		ForkJoinPoolWithoutResult task = new ForkJoinPoolWithoutResult(0, nums.length, nums);
		fjp.execute(task);
		
		SleepUtil.secondSleep(1);
	}
	
	static void forkJoinPoolWithResult() {
		int[] nums = new int[1_000_000];
		Random r = new Random();
		for (int i = 0; i < nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		ForkJoinPool fjp = new ForkJoinPool();
		ForkJoinPoolWithResult task = new ForkJoinPoolWithResult(0, nums.length, nums);
		fjp.execute(task);
		// 统计最后的结果
		long result = task.join();
		System.out.println("ForkJoinPoolWithResult = " + result);
		
		SleepUtil.secondSleep(1);
	}

	public static void main(String[] args) {
		fixedThreadPoolDemo();
		scheduledThreadPoolDemo();
		workStealingPoolDemo();
		forkJoinPoolWithoutResult();
		forkJoinPoolWithResult();
	}

}

class PrimeTask implements Callable<List<Integer>> {
	
	private int startPosition;
	private int endPosition;
	
	public PrimeTask(int startPosition, int endPosition) {
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

	@Override
	public List<Integer> call() throws Exception {
		return getPrime();
	}
	
	public boolean isPrime(int num) {
		for (int i = 2; i < num/2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public List<Integer> getPrime() {
		List<Integer> result = new ArrayList<>();
		
		for (int i = startPosition; i <= endPosition; i++) {
			if (isPrime(i)) {
				result.add(i);
			}
		}
		
		return result;
	}
}

class WorkStealingPoolTask implements Runnable {
	
	private int time;
	
	public WorkStealingPoolTask(int time) {
		this.time = time;
	}

	@Override
	public void run() {
		SleepUtil.milliSleep(time);
		System.out.println(Thread.currentThread().getName() + " " + time);
	}
	
}

class MyRejectHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// 1. 重试
		if (executor.getQueue().size() < 1000) {
			// put again
		}
		// 2. 记录日志: log.warn()
		// 3. 保存后续处理：kafka/mysql/redis
	}
	
}

class ForkJoinPoolWithoutResult extends RecursiveAction {
	
	private static final long serialVersionUID = -3505545998313757800L;

	public final static int MAX_NUM = 50000;
	
	private int start;
	private int end;
	private int[] nums;
	
	
	public ForkJoinPoolWithoutResult(int start, int end, int[] nums) {
		this.start = start;
		this.end = end;
		this.nums = nums;
	}

	@Override
	protected void compute() {
		// 当计算量小于MAX_NUM时计算，否则进行拆分
		if (end - start <= MAX_NUM) {
			
			long sum = 0L;
			for (int i = start; i < end; i++) {
				sum += nums[i];
			}
			// Java8
			int newSum = IntStream.range(start, end).map(i -> nums[i]).sum();
			
			System.out.printf("[%s] %s From=%d To=%d Sum=%d StreamSum=%d\n", 
					TimeUtil.getDefaultTimeString(), Thread.currentThread().getName(),
					start, end, sum, newSum);
		} else {
			int middle = start + (end - start) / 2;
			
			ForkJoinPoolWithoutResult subTask1 = new ForkJoinPoolWithoutResult(start, middle, nums);
			ForkJoinPoolWithoutResult subTask2 = new ForkJoinPoolWithoutResult(middle, end, nums);
			subTask1.fork();
			subTask2.fork();
		}
	}
	
}

class ForkJoinPoolWithResult extends RecursiveTask<Long> {

	private static final long serialVersionUID = 5810232474884299784L;

	public final static int MAX_NUM = 50000;
	
	private int start;
	private int end;
	private int[] nums;
	
	public ForkJoinPoolWithResult(int start, int end, int[] nums) {
		this.start = start;
		this.end = end;
		this.nums = nums;
	}
	
	@Override
	protected Long compute() {
		// 当计算量小于MAX_NUM时计算，否则进行拆分
		if (end - start <= MAX_NUM) {
			
			long sum = 0L;
			for (int i = start; i < end; i++) {
				sum += nums[i];
			}
			// Java8
			int newSum = IntStream.range(start, end).map(i -> nums[i]).sum();
			
			System.out.printf("[%s] %s From=%d To=%d Sum=%d StreamSum=%d\n", 
					TimeUtil.getDefaultTimeString(), Thread.currentThread().getName(),
					start, end, sum, newSum);
			
			return sum;
		} else {
			int middle = start + (end - start) / 2;
			
			ForkJoinPoolWithResult subTask1 = new ForkJoinPoolWithResult(start, middle, nums);
			ForkJoinPoolWithResult subTask2 = new ForkJoinPoolWithResult(middle, end, nums);
			subTask1.fork();
			subTask2.fork();
			
			return subTask1.join() + subTask2.join();
		}

	}
	
}