package edgar.try_new.jdk10;

import java.util.ArrayList;

public class LocalVariableTypeInference {
	
	static void localVariableDemo() {
		// 类型推断使用var
		var num = 10;
		
		var list = new ArrayList<String>();
		list.add("abc");
		for (var i : list) {
			System.out.println(i);
		}
	}

	public static void main(String[] args) {
		localVariableDemo();
	}

}
