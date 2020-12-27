package edgar.leetcode;

/**
 * 9. 回文数
 * https://leetcode-cn.com/problems/palindrome-number/comments/
 * 
 * @author Administrator
 *
 */
public class Solution0009_IsPalindrome {
	
	public static boolean isPalindrome(int x) {
		if(x ==0) {
			return true;
		} else if (x < 0 || x % 10 == 0) {
			return false;
		}
		
		long y = 0;
		int x1 = x;
		while (x1 > 0) {
			y = y * 10 + x1%10;
			x1 /=10;
		}
		
		if (y == x) {
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 输入: 121，输出: true
		assert(isPalindrome(121));

		// 输入: -121，输出: false
		assert(!isPalindrome(-121));
		
		// 输入: 10，输出: false
		assert(!isPalindrome(10));
	}

}
