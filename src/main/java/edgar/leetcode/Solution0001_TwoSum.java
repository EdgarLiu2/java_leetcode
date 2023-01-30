package edgar.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode-cn.com/problems/two-sum">1. 两数之和</a>
 * Created by Edgar.Liu on 2023/1/30
 */
public class Solution0001_TwoSum {
	
	public static int[] twoSum1(int[] nums, int target) {
        int numA;
        int numB;
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
		Map<Integer, Integer> cache = new HashMap<>(nums.length);
		
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

	public static void main(String[] args) {
		int[] input1 = {2,7,11,15};
		int[] result;
		
		result = twoSum1(input1, 18);
		System.out.println(Arrays.toString(result));

		result = twoSum2(input1, 18);
		System.out.println(Arrays.toString(result));
	}

}
