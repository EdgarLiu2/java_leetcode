package edgar.try_new.jdk8;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloOptional {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloOptional.class);
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
		Boy b = boyOptional.orElse(new Boy());
		return b.getName();
	}
	
	static void optionalDemo() {
		HelloOptional hello = new HelloOptional();
		logger.info("hello.getBoyName() = {}", hello.getBoyName());
		logger.info("hello.getBoyName2() = {}", hello.getBoyName2());
	}

	public static void main(String[] args) {
		optionalDemo();
	}

}

class Boy {
	private String name = "EMPTY USER";

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Boy(String name) {
		this.name = name;
	}

	public Boy() {
	}
	
	
}