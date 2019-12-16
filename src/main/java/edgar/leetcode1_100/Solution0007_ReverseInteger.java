package edgar.leetcode1_100;

/**
 * 7. 整数反转
 * https://leetcode-cn.com/problems/reverse-integer/
 * 
 * @author Administrator
 *
 */
public class Solution0007_ReverseInteger {
	
	public static int reverse1(int x) {

		boolean isPositive;
		if (x > 0) {
			isPositive = true;
		} else {
			isPositive = false;
			x = -x;
		}
		int y = 0;
		int MAX_Y_1 = (int)Math.pow(2, 31) / 10;
		
		while (x > 0) {
			if (y > MAX_Y_1) {
				return 0;
			}
			
			int pop = x % 10;
			x /= 10;
			
			y = y * 10 + pop;
		}

		return isPositive ? y : -y;
	}
	
	public static int reverse(int x) {
		
		long y = 0;
		while (x != 0) {
			int pop = x % 10;
			x /= 10;
			
			y = y * 10 + pop;
			if (y > Integer.MAX_VALUE || y < Integer.MIN_VALUE) {
				y = 0;
				break;
			}
		}

		return (int)y;
	}

	public static void main(String[] args) {
		// 输入: 123，输出: 321
		assert reverse(123) == 321;
		
		// 输入: -123，输出: -321
		assert reverse(-123) == -321;
		
		// 输入: 120，输出: 21
		assert reverse(120) == 21;
		
		// 输入: 1534236469，输出: 0
		assert reverse(1534236469) == 0;
	}

}
