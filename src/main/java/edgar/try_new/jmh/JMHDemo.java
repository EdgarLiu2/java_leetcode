package edgar.try_new.jmh;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JMHDemo {
	
	static List<Integer> nums;
	static {
		Random r = new Random();
		nums = Stream.generate(() -> r.nextInt(100)).limit(1_000_000).collect(Collectors.toList());
	}
	
	public static void stream() {
		nums.forEach(JMHDemo::isPrime);
	}
	
	public static void parallelStream() {
		nums.parallelStream().forEach(JMHDemo::isPrime);
	}

	public static boolean isPrime(int num) {
		for (int i = 2; i < num/2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
