package edgar.leetcode;

/**
 * 50. Pow(x, n)
 * https://leetcode-cn.com/problems/powx-n/
 * 
 * @author liuzhao
 *
 */
public class Solution0050_PowxN {
	
	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return x;
		}
		
		double result = 1;
		boolean positive = true;
		
		long new_n = n;
		if (new_n < 0) {
			positive = false;
			new_n = -new_n;
		}
		
		result = powHelper(x, new_n);
		
		if (!positive) {
			result = 1 / result;
		}

		return result;
	}
	
	public static double powHelper(double x, long n) {
		if (n == 1) {
			return x;
		}
		
		double result = 0;
		double half = powHelper(x, n/2);
		if (n % 2 == 0) {
			result = half * half;
		} else {
			result = half * half * x;
		}
		
		return result;
	}
	
	public static double myPow2(double x, int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return x;
		}
		
		double result = 1;
		boolean positive = true;
		
		long new_n = n;
		if (new_n < 0) {
			positive = false;
			new_n = -new_n;
		}
		
		for (long i = 0; i < new_n; i++) {
			result *= x;
		}
		
		if (!positive) {
			result = 1 / result;
		}

		return result;
	}

	public static void main(String[] args) {
		
		/*
		 * 输入: 2.00000, 10
		 * 输出: 1024.00000
		 */
		System.out.println("case 1");
		assert 1024.0 == myPow(2.0, 10);
		
		
		/*
		 * 输入: 1.00000, 2147483647
		 * 输出: 1.00000
		 */
		System.out.println("case 2");
		assert 1.0 == myPow(1.0, 2147483647);
		
		/*
		 * 输入: 1.00000, -2147483648
		 * 输出: 1.00000
		 */
		System.out.println("case 3");
		assert 1.0 == myPow(1.0, -2147483648);
		
		/*
		 * 输入: 2.10000, 3
		 * 输出: 9.26100
		 */
		System.out.println("case 4");
		assert Math.abs(9.261 - myPow(2.1, 3)) < 0.00000000000001;
		
		/*
		 * 输入: 2.00000, -2
		 * 输出: 0.25000
		 */
		System.out.println("case 5");
		assert Math.abs(0.25 - myPow(2.0, -2)) < 0.00000000000001;
	}

}
