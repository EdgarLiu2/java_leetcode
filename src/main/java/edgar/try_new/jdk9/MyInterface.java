package edgar.try_new.jdk9;

public interface MyInterface {

	void methodPublic();
	
	/**
	 * Java 8
	 */
	static void methodStatic() {
		System.out.println("接口中的静态方法");
	}
	
	/**
	 * Java 8
	 */
	default void methodDefault() {
		System.out.println("接口中的默认方法");
		methodPrivate();
	}
	
	/**
	 * Java 9
	 */
	private void methodPrivate() {
		System.out.println("接口中的私有方法");
	}
}
