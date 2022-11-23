package edgar.try_new.jdk9;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class NewStreamAPI {
	
	static void takeWhileDemo() {
		// takeWhile()：返回从开头开始，满足条件尽可能多的元素
		Stream.iterate(0, t -> t + 2).takeWhile(x -> x < 40).forEach(System.out::println);
	}
	
	static void dropWhileDemo() {
		// dropWhile()：从开头开始，扔掉满足条件尽可能多的元素，返回剩余元素
		Stream.iterate(0, t -> t + 2).limit(20).dropWhile(x -> x < 20).forEach(System.out::println);
	}

	static void ofNullableDemo() {
		// ofNullable()：允许创建一个单元素 Stream ，包含一个非空元素，或一个空 Stream

		// 单个元素不可以是null，否则会报 NullPointerException
//		Stream.of(null).forEach(System.out::println);

		// count == 0，空 stream
		long count = Stream.ofNullable(null).count();
		System.out.println(count);
	}
	
	static void iterateDemo() {
		// Stream.iterate(): Java 8需要借助limit()终止
		Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
		
		// Stream.iterate(): Java 9支持终止条件
		Stream.iterate(0, t -> t < 100, t -> t + 2).forEach(System.out::println);
	}

	static void optionalStreamDemo() {
		List<Integer> integerList = List.of(1, 2, 3, 4);
		Optional<List<Integer>> optionalList = Optional.of(integerList);
		optionalList.stream().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		takeWhileDemo();
		dropWhileDemo();
		ofNullableDemo();
		iterateDemo();
		optionalStreamDemo();
	}

}
