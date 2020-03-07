package edgar.leetcode1_100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 1. 两数之和
 * https://leetcode-cn.com/problems/two-sum
 * 
 * @author Administrator
 *
 */
public class Solution0001_TwoSum {
	
	public static int[] twoSum1(int[] nums, int target) {
        int numA = 0;
        int numB = 0;
        for(int i = 0; i < nums.length ; i++) {
            numA = i;
            int diff = target - nums[numA];
            for (int j = i+1; j < nums.length; j++) {
                if(nums[j] == diff) {
                    numB = j;
                    return new int[]{numA, numB};
                }
            }
        }

        return new int[]{-1, -1};
    }
	
	public static int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < nums.length ; i++) {
			int numA = nums[i];
			int diff = target - numA;
			if(cache.containsKey(diff)) {
				return new int[]{cache.get(diff), i};
			} else {
				cache.put(numA, i);
			}
		}
		
		return new int[]{-1, -1};
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] input1 = {2,7,11,15};
		int[] result;
		
		result = twoSum1(input1, 18);
		System.out.println(Arrays.toString(result));

		result = twoSum2(input1, 18);
		System.out.println(Arrays.toString(result));
	}

}
