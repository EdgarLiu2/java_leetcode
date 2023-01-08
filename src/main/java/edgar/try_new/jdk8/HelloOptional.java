package edgar.try_new.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloOptional {
	
	private Boy boy;
	
	public String getBoyName() {
		if (boy != null) {
			return boy.getName();
		}
		
		return "EMPTY USER";
	}
	
	public String getBoyName2() {
		Optional<Boy> boyOptional = Optional.ofNullable(this.boy);
		// 当为null时，返回orElse()
		Boy b = boyOptional.orElse(new Boy("abc", 123));
		return b.getName();
	}
	
	static void optionalDemo() {
		HelloOptional hello = new HelloOptional();
		log.info("hello.getBoyName() = {}", hello.getBoyName());
		log.info("hello.getBoyName2() = {}", hello.getBoyName2());
	}

	static void beforeJava8() {
		log.info("Before Java 8");

		List<Boy> boys = new ArrayList<>();
		boys.add(null);
		boys.add(new Boy("b0", 1));
		boys.add(new Boy("bb1", 10));
		boys.add(new Boy("bb2", 20));
		boys.add(new Boy("bbb3", 30));
		boys.add(new Boy("bbbb4", 40));
		boys.add(new Boy(null, 50));

		for (Boy boy : boys) {
			if (boy != null) {
				if (boy.getAge() >= 10) {
					String name = boy.getName();
					if (name != null && name.length() > 2) {
						System.out.println("Found right boy: " + boy);
					}
				}
			}
		}
	}

	/**
	 * 正确使用Optional，去除 if
	 */
	static void afterJava8() {
		log.info("After Java 8");

		List<Boy> boys = new ArrayList<>();
		boys.add(null);
		boys.add(new Boy("b0", 1));
		boys.add(new Boy("bb1", 10));
		boys.add(new Boy("bb2", 20));
		boys.add(new Boy("bbb3", 30));
		boys.add(new Boy("bbbb4", 40));
		boys.add(new Boy(null, 50));

		for (Boy boy : boys) {
			Optional<Boy> optionalBoy = Optional.ofNullable(boy);

			optionalBoy
					.filter(b -> b.getAge() >= 10)
					.map(Boy::getName).ifPresent(System.out::println);
		}
	}

	public static void main(String[] args) {
		optionalDemo();
		beforeJava8();
		afterJava8();
	}

}

@Data
@AllArgsConstructor
class Boy {
	private String name = "EMPTY USER";
	private int age;
	
}