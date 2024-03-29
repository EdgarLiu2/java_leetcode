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

	/**
	 * 通过二分查找算法，寻找X的平方根的整数部分
	 * @param x 输入X
	 * @return 平方根的整数部分
	 */
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

	/**
	 * 通过牛顿迭代算法，寻找X的平方根的整数部分
	 * @param x 输入X
	 * @return 平方根的整数部分
	 */
	static int mySqrt3(int x) {
		if (x == 0 || x == 1) {
			return x;
		}

		return (int)newton(x, x);
	}

	static double newton(double n, int x) {
		// x/n和n的平均值，更接近x的平方根
		double result = (x / n + n) / 2;

		if (result == n) {
			return n;
		} else {
			return newton(result, x);
		}
	}
    
	public static void main(String[] args) {
		/*
		 * 输入: 2147395599
		 * 输出: 46339
		 */
		assert 46339 == mySqrt(2147395599);
		assert 46339 == mySqrt2(2147395599);
		assert 46339 == mySqrt3(2147395599);

		/*
		 * 输入: 8
		 * 输出: 2
		 */
		assert 2 == mySqrt(8);
		assert 2 == mySqrt2(8);
		assert 2 == mySqrt3(8);

		/*
		 * 输入: 1
		 * 输出: 1
		 */
		assert 1 == mySqrt(1);
		assert 1 == mySqrt2(1);
		assert 1 == mySqrt3(1);

		/*
		 * 输入: 4
		 * 输出: 2
		 */
		assert 2 == mySqrt(4);
		assert 2 == mySqrt2(4);
		assert 2 == mySqrt3(4);

	}

}
