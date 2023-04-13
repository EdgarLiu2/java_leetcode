package edgar.leetcode;

/**
 * <a href="https://leetcode-cn.com/problems/sqrtx/">69. x 的平方根</a>
 * Created by Edgar.Liu on 2023/4/13
 */
public class Solution0069_Sqrtx {

	static int mySqrt(int x) {
    	
    	long left = 1;
    	long right = x;
    	int result = 0;
    	
    	// 二分查找
    	while (left <= right) {
    		// 中间值
    		long mid = (left + right) / 2;
    		
    		if (mid * mid == x) {
    			return (int)mid;
    		} else if (mid * mid > x) {
    			// 结果应该在左边
    			right = mid - 1;
    		} else {
    			// 结果应该在右边
    			left = mid + 1;
    			result = (int)mid;
    		}
    	}

    	return result;
    }

	static int mySqrt2(int x) {

		if (x <= 1) {
			return x;
		}

		long result = -1;
		long left = 1;
		long right = x;

		while (left <= right) {
			long mid = left + (right - left) / 2;

			if (mid * mid <= x) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return (int)result;
	}
    
	public static void main(String[] args) {
		/*
		 * 输入: 2147395599
		 * 输出: 46339
		 */
		assert 46339 == mySqrt(2147395599);
		assert 46339 == mySqrt2(2147395599);

		/*
		 * 输入: 8
		 * 输出: 2
		 */
		assert 2 == mySqrt(8);
		assert 2 == mySqrt2(8);

		/*
		 * 输入: 1
		 * 输出: 1
		 */
		assert 1 == mySqrt(1);
		assert 1 == mySqrt2(1);

		/*
		 * 输入: 4
		 * 输出: 2
		 */
		assert 2 == mySqrt(4);
		assert 2 == mySqrt2(4);

	}

}
