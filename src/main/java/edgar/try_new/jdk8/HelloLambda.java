package edgar.try_new.jdk8;

import java.util.Comparator;
import java.util.function.Consumer;

public class HelloLambda {
	
	static void lambdaTest() {
		Comparator<Integer> comparator1 = new Comparator<>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
			
		};
		System.out.println(comparator1.compare(12, 21));
		
		// Lambda表达式
		Comparator<Integer> comparator2 = (o1, o2) -> {
			return Integer.compare(o1, o2);
		};
		System.out.println(comparator2.compare(12, 21));
		
		// Lambda表达式
		Comparator<Integer> comparator3 = (o1, o2) -> Integer.compare(o1, o2);
		System.out.println(comparator3.compare(12, 21));
		
		// 方法引用
		Comparator<Integer> comparator4 = Integer::compare;
		System.out.println(comparator4.compare(12, 21));
	}
	
	/**
	 * 语法格式一：无参，无返回值
	 */
	static void lambdaTest1() {
		Runnable r1 = () -> System.out.println("Type 1: Hello Lambda!");
		r1.run();
	}
	
	/**
	 * 语法格式二：一个参数，没有返回值
	 */
	static void lambdaTest2() {
		Consumer<String> consumer1 = new Consumer<>() {

			@Override
			public void accept(String t) {
				System.out.println("Type 2: " + t);
			}
			
		};
		consumer1.accept("Lambda 2a");
		
		Consumer<String> consumer2 = (String s) -> System.out.println("Type 2: " + s);
		consumer2.accept("Lambda 2b");
		
		// 类型推断
		Consumer<String> consumer3 = (s) -> System.out.println("Type 2: " + s);
		consumer3.accept("Lambda 2c");
		
		// 只有一个参数，小括号可以省略
		Consumer<String> consumer4 = s -> System.out.println("Type 2: " + s);
		consumer4.accept("Lambda 2d");
	}
	
	/**
	 * 语法格式三：多个参数，有返回值
	 */
	static void lambdaTest3() {
		Comparator<Integer> comparator1 = (o1, o2) -> {
			return Integer.compare(o1, o2);
		};
		System.out.println("Type 3: Lambda a " + comparator1.compare(12, 21));
		
		// 只有一条语句，return和{}都可以省略
		Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
		System.out.println("Type 3: Lambda b " + comparator2.compare(12, 21));
	}

	public static void main(String[] args) {
		lambdaTest();
		lambdaTest1();
		lambdaTest2();
		lambdaTest3();
	}
}
