package edgar.try_new.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.datafaker.Faker;

public class HelloFunctionalInterface {
	
	public static final Faker faker = new Faker(new Locale("zh-CN"));
	
	/*
	 * 消费型函数接口
	 */
	static void consumerDemo() {
		happyTime(500, m -> System.out.println("消费：" + m));
	}
	
	private static void happyTime(double money, Consumer<Double> consumer) {
		consumer.accept(money);
	}
	
	/*
	 * 断言型函数接口
	 */
	static void predicateDemo() {
		List<String> stringLists = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			stringLists.add(faker.internet().emailAddress());
		}
		
		List<String> filtered = filterString(stringLists, s -> s.contains("yahoo"));
		System.out.println(filtered);
	}
	
	private static List<String> filterString(List<String> list, Predicate<String> predicate) {
		List<String> result = new ArrayList<>();
		
		list.forEach(s -> {
			if (predicate.test(s)) {
				result.add(s);
			}
		});
		
		return result;
	}
	
	static void biPredicateDemo() {
		BiPredicate<String, String> biPredicate = (s1, s2) -> s1.contains(s2);
		System.out.println("BiPredicate Demo1: " + biPredicate.test("china", "ch"));
	}

	public static void main(String[] args) {
		consumerDemo();
		predicateDemo();
		biPredicateDemo();
	}

}
