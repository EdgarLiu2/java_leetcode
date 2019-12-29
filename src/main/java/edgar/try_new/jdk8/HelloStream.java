package edgar.try_new.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java 8 新特性-Stream
 * https://www.toutiao.com/a6770603570233344524/
 * 
 * @author Administrator
 *
 */
public class HelloStream {
	@SuppressWarnings("unused")
	public static void create_test1() {
		// 方式一：通过一个集合创建Stream
		List<Student> students = Student.getStudents();
		// 顺序流
		Stream<Student> stream1 = students.stream();
		// 并行流
		Stream<Student> stream2 = students.parallelStream();
	}
	
	@SuppressWarnings("unused")
	public static void create_test2() {
		// 方式二：通过一个数组创建Stream
		int[] i_array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		IntStream intStream = Arrays.stream(i_array);
	}
	
	@SuppressWarnings("unused")
	public static void create_test3() {
		// 方式三：通过Stream.of
		Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
	}
	
	public static void create_test4() {
		// 每隔5个数取一个，从0开始，无限循环
		// Stream.iterate(0,  t -> t+5).forEach(System.out::println);
		// 每隔5个数取一个，从0开始，取前10个
		Stream.iterate(0,  t -> t+5).limit(10).forEach(System.out::println);
		// 取随机数
		Stream.generate(Math::random).limit(5).forEach(System.out::println);
	}
	
	public static void filter_test5() {
		List<Student> students = Student.getStudents();
		
		// 1. 过滤年龄大于15
		students.stream().filter(item -> item.getAge() > 15).forEach(System.out::println);
		// 2. 前3条
		students.stream().limit(3).forEach(System.out::println);
		// 3. 跳过前5条
		students.stream().skip(5).forEach(System.out::println);
		// 4. 过滤重复数据
		students.stream().distinct().forEach(System.out::println);
	}
	
	public static void map_test6() {
		// 小写字母转大写
		List<String> strings = Arrays.asList("java", "python", "go");
		strings.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
		
		// 筛选出年龄，再过滤出大于15
		List<Student> students = Student.getStudents();
		Stream<Integer> stream1 = students.stream().map(Student::getAge);
		stream1.filter(age -> age > 15).forEach(System.out::println);
	}
	
	public static void sort_test7() {
		// 直接排序，如果对象实现了Comparable接口
		List<Integer> intList = Arrays.asList(56, 4, 56, 7, 8, 4, 4, 5, 654, 346, 3453, 4634, 5);
		intList.stream().sorted().forEach(System.out::println);
		
		// 直接指定comparable
		List<Student> students = Student.getStudents();
		students.stream()
			.sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()))
			.forEach(System.out::println);
	}
	
	@SuppressWarnings("unused")
	public static void match_test8() {
		List<Student> students = Student.getStudents();
		
		// 是否年龄都大于20
		boolean allMatch = students.stream().allMatch(item -> item.getAge() > 20);
		// 是否有年龄大于20
		boolean anyMatch = students.stream().anyMatch(item -> item.getAge() > 20);
		// 没有人叫Student10
		boolean noneMatch = students.stream().noneMatch(item -> item.getName().equals("Student10"));
		// 第一个学生
		Optional<Student> first = students.stream().findFirst();
		first.ifPresent(System.out::println);
		// 学生数量
		long count1 = students.stream().count();
		long count2 = students.stream().filter(item -> item.getAge() > 14).count();
		// 查找最高分
		Stream<Double> scores = students.stream().map(Student::getScore);
		scores.max(Double::compare).ifPresent(System.out::println);
	}
	
	public static void reduce_test9() {
		// 求和
		List<Integer> intList = Arrays.asList(56, 4, 56, 7, 8, 4, 4, 5, 654, 346, 3453, 4634, 5);
		int sum = intList.stream().reduce(0, Integer::sum);
		System.out.println(sum);
		
		// 学生总分
		List<Student> students = Student.getStudents();
		students.stream().map(Student::getScore).reduce(Double::sum).ifPresent(System.out::println);
	}
	
	@SuppressWarnings("unused")
	public static void collect_test10() {
		List<Student> students = Student.getStudents();
		
		// 返回一个List
		List<Student> studentsAge14 = students.stream().filter(e -> e.getAge() > 14).collect(Collectors.toList());
		// 返回一个Set
		Set<Student> studentsAge16 = students.stream().filter(e -> e.getAge() > 16).collect(Collectors.toSet());
	}
	
	public static void main(String[] args) {
		create_test4();
		
		// 使用Stream操作数据
		filter_test5();
		map_test6();
		sort_test7();
		match_test8();
		reduce_test9();
		collect_test10();
	}
}

class Student {
	private Integer id;
	private String name;
	private Integer age;
	private Double score;
	
	public Student(Integer id, String name, Integer age, Double score) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.score = score;
	}
	
	public static List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "Student1", 11, 91.0));
		students.add(new Student(2, "Student2", 12, 92.0));
		students.add(new Student(3, "Student3", 13, 93.0));
		students.add(new Student(4, "Student4", 14, 94.0));
		students.add(new Student(5, "Student5", 15, 95.0));
		students.add(new Student(6, "Student6", 16, 96.0));
		students.add(new Student(7, "Student7", 17, 97.0));
		students.add(new Student(8, "Student8", 18, 98.0));
		students.add(new Student(9, "Student9", 19, 99.0));
		
		return students;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", score=" + score + "]";
	}
	
}
