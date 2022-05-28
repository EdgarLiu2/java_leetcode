package edgar.try_new.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datafaker.Faker;

public class HelloStream2 {
	private static final Faker faker = new Faker();
	private static final Logger logger = LoggerFactory.getLogger(HelloStream2.class);
	
	 
	/*
	 * 创建Stream
	 * 1. 通过集合：new ArrayList<String>().stream()
	 * 2. 通过数组：Arrays.stream(new int[]{1, 2, 3, 4})
	 * 3. Stream.of(1, 2, 3, 4)
	 * 4. 无限流：Stream.iterate(0, t -> t + 2).limit(10)  Stream.generate(Math::random).limit(10)
	 */

	/*
	 * 筛选与切片
	 * 1. filter(Predicate p)
	 * 2. distinct()
	 * 3. limit(long maxSize)
	 * 4. skip(long n)
	 */
	static void streamFilter() {
		
		// filter: 从流中排除某些元素
		logger.info("filter: 从流中排除某些元素");
		Stream.iterate(0, t -> t + 1).limit(20)
			.filter(n -> n % 2 == 0).forEach(System.out::println);
		
		// skip: 跳过前n个元素
		logger.info("skip: 跳过前n个元素");
		Stream.iterate(0, t -> t + 1).limit(10)
			.skip(3).forEach(System.out::println);
		
		// distinct: 去重
		logger.info("distinct: 去重");
		Stream.of(1, 2, 3, 4, 1, 2, 3, 4)
			.distinct().forEach(System.out::println);
		
	}
	
	/*
	 * 映射：
	 * 1. map(Function f)
	 * 2. flatMap(Function f)
	 * 3. mapToDouble(ToDoubleFunction f)
	 * 4. mapToInt(ToIntFunction f)
	 * 5. maptoLong(ToLongFunction f)
	 */
	static void streamMap() {
		// map(Function f): 对元素执行一个映射函数
		Stream.generate(Math::random).limit(10)
			.map(d -> String.format("%.2f", d)).forEach(System.out::println);
		
		// flatMap(Function f): 将流中每个元素都转成一个流
		Stream.generate(() -> faker.address().cityName()).limit(10)
			.flatMap(HelloStream2::stringToStream).forEach(System.out::println);
	}
	
	private static Stream<Character> stringToStream(String str) {
		List<Character> list = new ArrayList<>();
		for (char c : str.toCharArray()) {
			list.add(c);
		}
		return list.stream();
	}
	
	/*
	 * 排序
	 * 1. sorted()
	 * 2. sorted(Comparator c)
	 */
	static void streamSort() {
		// sorted()
		Stream.generate(Math::random).limit(10)
			.sorted().forEach(System.out::println);
		
		// sorted(Comparator c)
		Stream.generate(() -> faker.address().cityName()).limit(10)
			.sorted((c1, c2) -> Integer.compare(c1.length(), c2.length())).forEach(System.out::println);
	}
	
	/*
	 * 匹配与查找
	 * allMatch(Predicate p)
	 * anyMatch(Predicate p)
	 * noneMatch(Predicate p)
	 * findFirst()
	 * findAny()
	 * count()
	 * max(Comparator c)
	 * min(Comparator c)
	 * forEach(Consumer c)
	 */
	static void streamMatchFind() {
		// allMatch(Predicate p): 检查是否匹配所有元素
		boolean allMatch = Stream.generate(Math::random).limit(10).allMatch(n -> n > 0.5);
		assert !allMatch;
		
		// anyMatch(Predicate p): 检查是否至少匹配一个元素
		boolean anyMatch = Stream.generate(Math::random).limit(10).anyMatch(n -> n > 0.5);
		assert anyMatch;
		
		// noneMatch(Predicate p): 检查是否没有元素匹配
		boolean noneMatch = Stream.generate(Math::random).limit(10).noneMatch(n -> n > 0.99);
		assert noneMatch;
		
		// findFirst(): 返回第一个元素
		Optional<Double> firstNumber = Stream.generate(Math::random).limit(10).findFirst();
		logger.info("findFirst={}", firstNumber.orElse(0.0));
		
		// findAny(): 返回任意元素
		Optional<Double> findAny = Stream.generate(Math::random).limit(10).findAny();
		logger.info("findAny={}", findAny.orElse(0.0));
		
		// count()
		long number = Stream.generate(Math::random).limit(10).filter(n -> n > 0.5).count();
		logger.info("count={}", number);
		
		// max(Comparator c)
		Optional<Double> max = Stream.generate(Math::random).limit(10).max(Double::compare);
		logger.info("max={}", max);
		
		// min(Comparator c)
		Optional<Double> min = Stream.generate(Math::random).limit(10).min(Double::compare);
		logger.info("min={}", min);
	}
	
	/*
	 * 归约
	 * reduce(T iden, BinaryOperator b)
	 * reduce(BinaryOperator b)
	 */
	static void streamReduce() {
		// reduce(T iden, BinaryOperator b): 将流中元素反复结合，最后得到一个值
		// 计算1-10自然数的和
		int sum1 = Stream.iterate(1, t -> t + 1).limit(10).reduce(0, Integer::sum);
		logger.info("sum(1-10)={} by reduce", sum1);
		
		// reduce(BinaryOperator b)
		Optional<Integer> sum2 = Stream.iterate(1, t -> t + 1).limit(10).reduce(Integer::sum);
		logger.info("sum(1-10)={} by reduce", sum2.orElse(0));
	}
	
	/*
	 * 收集
	 * collect(Collector c)
	 */
	static void streamCollect() {
		// collect(Collector c): 将流转换成其它形式，例如收集到List，Set或Map中
		// collect(Collectors.toList())
		List<Double> l1 = Stream.generate(Math::random).limit(10).filter(n -> n > 0.5).toList();
		logger.info("List<Double> = {}", l1);
		
		// collect(Collectors.toSet())
		Set<Double> s1 = Stream.generate(Math::random).limit(10).filter(n -> n > 0.5).collect(Collectors.toSet());
		logger.info("Set<Double> = {}", s1);
	}
	
	public static void main(String[] args) {
		streamFilter();
		streamMap();
		streamSort();
		streamMatchFind();
		streamReduce();
		streamCollect();
	}

}
