package edgar.try_new.jdk11;

import java.util.Optional;

public class NewOptional {

	static void optionalDemo() {
		Optional<Integer> o = Optional.empty();
		
		// Java 11: 判断Optional内部是否为空
		assert o.isEmpty();
		// Java 8: 判断Optional内部是否有值
		assert !o.isPresent();
	}
	
	public static void main(String[] args) {
		optionalDemo();
	}

}
