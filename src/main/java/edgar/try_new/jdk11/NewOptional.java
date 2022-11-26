package edgar.try_new.jdk11;

import java.util.Optional;

public class NewOptional {

	static void optionalDemo() {
		Optional<Integer> o = Optional.empty();
		
		// Java 11: 判断Optional内部是否为空
		assert o.isEmpty();
		// Java 8: 判断Optional内部是否有值
		assert !o.isPresent();

		// of()方法不能传入null
		o = Optional.of(1);

		// ofNullable()方法可以传入null
		o = Optional.ofNullable(null);
		assert o.isEmpty();

		System.out.println(o.orElse(10));
	}
	
	public static void main(String[] args) {
		optionalDemo();
	}

}
