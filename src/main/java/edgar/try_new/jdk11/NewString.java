package edgar.try_new.jdk11;

public class NewString {
	
	static void newStringMethod() {
		// 空格，制表符合换行符都去掉
		String blankStr = " \t \n ";
		assert blankStr.isBlank();
		
		// strip()和trim()基本一致
		assert "abc".equals("  abc   ".strip());
		
		// repeat()复制
		assert "aaa".equals("a".repeat(3));
		
		// 统计行数
		String str = "a\nb\nc";
		assert 3 == str.lines().count();
	}

	public static void main(String[] args) {
		newStringMethod();
	}

}
