package edgar.leetcode;

/**
 * 38. 外观数列
 * https://leetcode-cn.com/problems/count-and-say/
 * 
 * @author Administrator
 *
 */
public class Solution0038_CountAndSay {
	
	 public static String countAndSay(int n) {
		 if (n == 1) {
			 return "1";
		 }
		 
		 String resultN1 = countAndSay(n - 1);
		 int len = resultN1.length();
		 if (len == 1) {
			 return "1" + resultN1;
		 }
		 
		 StringBuilder buf = new StringBuilder();
		 int count = 1;
		 for (int i = 1; i < len; i++) {
			 if (resultN1.charAt(i) == resultN1.charAt(i - 1)) {
				 // 第i个位置跟第i-1个位置的数字相同
				 count++;
			 } else {
				 // 第i个位置跟第i-1个位置的数字不相同
				 buf.append(String.valueOf(count) + resultN1.charAt(i - 1));
				 count = 1;
			 }
		 }
		 
		 // 加入最后一个数
		 buf.append(String.valueOf(count) + resultN1.charAt(len - 1));
		 
		 return buf.toString();
	}

	public static void main(String[] args) {

		/*
		 * 输入: 1
		 * 输出: "1"
		 */
		assert "1".equals(countAndSay(1));
		
		/*
		 * 输入: 2
		 * 输出: "11"
		 */
		assert "11".equals(countAndSay(2));

		/*
		 * 输入: 3
		 * 输出: "21"
		 */
		assert "21".equals(countAndSay(3));
		
		/*
		 * 输入: 4
		 * 输出: "1211"
		 */
		assert "1211".equals(countAndSay(4));
		
		/*
		 * 输入: 5
		 * 输出: "111221"
		 */
		assert "111221".equals(countAndSay(5));
	}

}
