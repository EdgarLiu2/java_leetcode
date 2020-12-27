package edgar.leetcode;

/**
 * 43. 字符串相乘
 * https://leetcode-cn.com/problems/multiply-strings/
 * 
 * @author liuzhao
 *
 */
public class Solution0043_MultiplyStrings {
	
	/**
	 * 方法二：优化竖式
	 * 该算法是通过两数相乘时，乘数某位与被乘数某位相乘，与产生结果的位置的规律来完成。具体规律如下：
	 * 		乘数 num1 位数为 MMM，被乘数 num2 位数为 NNN， num1 x num2 结果 res 最大总位数为 M+N
	 * 		num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，其第一位位于 res[i+j]，第二位位于 res[i+j+1]。
	 * @param num1
	 * @param num2
	 * @return
	 */
    public static String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }

	public static String multiply(String num1, String num2) {
		if (num1 == null || num1.isEmpty() || num1.equals("0")) {
			return "0";
		}
		if (num2 == null || num2.isEmpty() || num2.equals("0")) {
			return "0";
		}
		
		int carry = 0;
		String result = "0";
		int k = num1.length() + num2.length() - 2;
		for (int j = num2.length() - 1; j >= 0; j--) {
			int j_num = num2.charAt(j) - '0';
			
			for (int i = num1.length() - 1; i >= 0 || carry != 0; i--) {
				int i_num = i < 0 ? 0 : num1.charAt(i) - '0';
				int sum = (i_num * j_num + carry) % 10;
				carry = (i_num * j_num + carry) / 10;
				
				StringBuilder builder = new StringBuilder();
				builder.append(sum);
				for (int a = 0; a < k - i - j; a++) {
					builder.append("0");
				}
				result = addStrings(result, builder.toString());
			}
		}
		
		return result;
    }
	
	public static String addStrings(String num1, String num2) {
		if (num1 == null || num1.isEmpty() || num1.equals("0")) {
			return num2;
		}
		if (num2 == null || num2.isEmpty() || num2.equals("0")) {
			return num1;
		}
		
		StringBuilder builder = new StringBuilder();
        int carry = 0;
        
        for (int i = num1.length() - 1, j = num2.length() - 1; i>=0 || j>=0 || carry !=0 ; i--, j--) {
        	int n1 = i < 0 ? 0 : num1.charAt(i) - '0';
        	int n2 = j < 0 ? 0 : num2.charAt(j) - '0';
        	int sum = (n1 + n2 + carry) % 10;
        	builder.append(sum);
        	carry = (n1 + n2 + carry) / 10;
        }
		
		return builder.reverse().toString();
    }

	public static void main(String[] args) {
		
		/*
		 * 输入: num1 = "0", num2 = "9"
		 * 输出: "0"
		 */
		assert "9".equals(addStrings("0", "9"));
		assert "0".equals(multiply("0", "9"));
		
		/*
		 * 输入: num1 = "2", num2 = "3"
		 * 输出: "6"
		 */
		assert "5".equals(addStrings("2", "3"));
		assert "6".equals(multiply("2", "3"));

		/*
		 * 输入: num1 = "123", num2 = "456"
		 * 输出: "56088"
		 */
		assert "579".equals(addStrings("123", "456"));
		assert "56088".equals(multiply("123", "456"));
	}

}
