package edgar.leetcode;

/*
 * 91. 解码方法
 * https://leetcode-cn.com/problems/decode-ways/
 * 
 */
public class Solution0091_DecodeWays {
	
	public static int numDecodings(String s) {
		
		if (s == null || s.isBlank() || s.startsWith("0")) {
			return 0;
		}
		
		int[] result = new int[s.length()];
		result[0] = 1;
		
		for (int i = 1; i < s.length(); i++) {
			// 第i个字符在1-9之间
			if (s.charAt(i) >= '1' && s.charAt(i) <= '9') {
				result[i] += result[i-1];
			}
			
			// 第i-1和i，两个字符组成的数字j是否在10-26之间
			int j = (s.charAt(i - 1) - '0') * 10 + s.charAt(i) - '0';
			if (j >= 10 && j <= 26) {
				if (i > 1) {
					result[i] += result[i-2];
				} else {
					result[i] += 1;
				}
			}
			
		}

		return result[s.length() - 1];
    }

	public static void main(String[] args) {
		
		/*
		 * 输入：s = "12"
		 * 输出：2
		 */
		assert 2 == numDecodings("12");

		/*
		 * 输入：s = "226"
		 * 输出：3
		 */
		assert 3 == numDecodings("226");
		
		/*
		 * 输入：s = "0"
		 * 输出：0
		 */
		assert 0 == numDecodings("0");
		
		/*
		 * 输入：s = "06"
		 * 输出：0
		 */
		assert 0 == numDecodings("06");
	}

}
