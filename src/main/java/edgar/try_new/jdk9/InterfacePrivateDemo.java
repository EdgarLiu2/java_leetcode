package edgar.try_new.jdk9;

public class InterfacePrivateDemo {

	public static void main(String[] args) {
		MyInterfaceImpl object = new MyInterfaceImpl();
		object.methodPublic();
		object.methodDefault();
		// 接口的静态方法无法通过类对象调用
//		object.methodStatic();
		MyInterface.methodStatic();
	}

}

class MyInterfaceImpl implements MyInterface {

	@Override
	public void methodPublic() {
		System.out.println("接口中的公共方法");
	}
	
}