package edgar.try_new.uuid;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Sample1 {
	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static void main(String[] args) {
		//System.out.println(createUUID());
		
		ExecutorService service = new ThreadPoolExecutor(5, 10, 300, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
		
		// 用lambda表达式编写方法体中的逻辑
		Runnable run = () -> {
			long now = System.currentTimeMillis();
			int count = 0;
			
			while (System.currentTimeMillis() - now < 1000 * 10) {
				// only run 10s
				createUUID();
				count++;
			}
			
			System.out.printf("%s: generate %d UUID\n", Thread.currentThread().getName(), count);
 		};
		
		for (int i = 0; i < 5; i++) {
			service.execute(run);
		}
		
		service.shutdown();
	}

}
