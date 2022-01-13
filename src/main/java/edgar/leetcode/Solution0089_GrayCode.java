package edgar.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 89. 格雷编码
 * https://leetcode-cn.com/problems/gray-code/
 * 
 * @author liuzhao
 *
 */
public class Solution0089_GrayCode {
	
	public static List<Integer> grayCode(int n) {

		List<Integer> result = new ArrayList<>();
		if (n == 0) {
			return result;
		}
		
		for (int i = 0; i < 1 << n; i++) {
			// i格雷编码，等于i右移一位然后异或i
			result.add(i >> 1 ^ i);
		}
		
		return result;
    }

	public static void main(String[] args) {
		List<Integer> result;
		
		/*
		 * 输入：n = 2
		 * 输出：[0,1,3,2]
		 */
		result = grayCode(2);
		assert Arrays.asList(0,1,3,2).equals(result);
		
		/*
		 * 输入：n = 1
		 * 输出：[0,1]
		 */
		result = grayCode(1);
		assert Arrays.asList(0,1).equals(result);
	}

}
