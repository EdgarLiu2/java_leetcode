package edgar.leetcode;

import java.util.Arrays;

/**
 * 66. 加一
 * https://leetcode-cn.com/problems/plus-one/
 * 
 * @author Administrator
 *
 */
public class Solution0066_PlusOne {
	
    public static int[] plusOne(int[] digits) {
    	
    	for(int i = digits.length - 1; i >= 0; i --) {
    		if (digits[i] != 9) {
    			// 第i位不是9，直接+1
    			digits[i]++;
    			return digits;
    		} else {
    			// 设置i位为0
    			digits[i] = 0;
    		}
    	}

    	// 所有位都是9
    	digits = new int[digits.length + 1];
    	// 第一位应为1
    	digits[0] = 1;
    	return digits;
    }

	public static void main(String[] args) {

		int[] input;
		
		/*
		 * 输入：digits = [1,2,3]
		 * 输出：[1,2,4]
		 */
		input = new int[]{1,2,3};
		assert Arrays.equals(plusOne(input), new int[]{1, 2, 4});

		/*
		 * 输入：digits = [4,3,2,1]
		 * 输出：[4,3,2,2]
		 */
		input = new int[]{4,3,2,1};
		assert Arrays.equals(plusOne(input), new int[]{4,3,2,2});
		
		/*
		 * 输入：digits = [0]
		 * 输出：[1]
		 */
		input = new int[]{0};
		assert Arrays.equals(plusOne(input), new int[]{1});
		
		/*
		 * 输入：digits = [9]
		 * 输出：[1, 0]
		 */
		input = new int[]{9};
		assert Arrays.equals(plusOne(input), new int[]{1, 0});
	}

}
