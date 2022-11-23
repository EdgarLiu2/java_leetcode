package edgar.try_new.jdk9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadOnlyCollectionDemo {

	private final static Logger logger = LoggerFactory.getLogger(ReadOnlyCollectionDemo.class);

	static void readOnlyCollectionJava8() {
		List<String> list = new ArrayList<>();
		list.add("abc");
		list.add("edf");
		list.add("ghi");
		List<String> readOnlyList = Collections.unmodifiableList(list);
		logger.info("readOnlyList = {}", readOnlyList);
	}
	
	static void readOnlyCollection2Java8() {
		List<String> readOnlyList = Arrays.asList("abc", "edf", "ghi");
		logger.info("readOnlyList = {}", readOnlyList);
	}
	
	static void readOnlyCollectionJava9() {
		List<Integer> readOnlyList = List.of(1, 2, 3, 4, 5);
		logger.info("readOnlyList = {}", readOnlyList);
		
		Set<Integer> readOnlySet = Set.of(1, 2, 3, 4, 5);
		logger.info("readOnlySet = {}", readOnlySet);
		
		Map<String, Integer> readOnlyMap = Map.of("Tom", 1, "Jack", 2, "Jerry", 3);
		logger.info("readOnlyMap = {}", readOnlyMap);
		
		readOnlyMap = Map.ofEntries(
				Map.entry("Tom", 1),
				Map.entry("Jack", 2),
				Map.entry("Jerry", 3)
			);
		logger.info("readOnlyMap = {}", readOnlyMap);
	}
	
	public static void main(String[] args) {
		readOnlyCollectionJava8();
		readOnlyCollection2Java8();
		readOnlyCollectionJava9();
	}

}
