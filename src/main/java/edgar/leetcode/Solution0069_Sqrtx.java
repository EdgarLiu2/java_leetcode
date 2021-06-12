package edgar.leetcode;

/*
 * 69. x 的平方根
 * https://leetcode-cn.com/problems/sqrtx/
 * 
 */
public class Solution0069_Sqrtx {

    public static int mySqrt(int x) {
    	
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
    
	public static void main(String[] args) {
		/*
		 * 输入: 2147395599
		 * 输出: 46339
		 */
		assert 46339 == mySqrt(2147395599);
		
		/*
		 * 输入: 8
		 * 输出: 2
		 */
		assert 2 == mySqrt(8);
		
		/*
		 * 输入: 1
		 * 输出: 1
		 */
		assert 1 == mySqrt(1);
		
		/*
		 * 输入: 4
		 * 输出: 2
		 */
		assert 2 == mySqrt(4);

	}

}
