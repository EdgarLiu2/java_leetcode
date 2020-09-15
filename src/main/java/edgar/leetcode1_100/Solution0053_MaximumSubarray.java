package edgar.leetcode1_100;

/**
 * 53. 最大子序和
 * https://leetcode-cn.com/problems/maximum-subarray/
 * 
 * @author liuzhao
 *
 */
public class Solution0053_MaximumSubarray {

	/**
	 * 贪心算法，若当前指针所指元素之前的和小于0，则丢弃当前元素之前的数列
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxSubArray(int[] nums) {
		
		if (nums == null || nums.length == 0)
			return 0;
		
		if (nums.length == 1) {
			return nums[0];
		}
		
		int preSum = -1;
		int currentMax = nums[0];
		
		
		for (int i = 0; i < nums.length; i++) {
			if (preSum < 0) {
				preSum = nums[i];
			} else {
				preSum += nums[i];
			}
			
			currentMax = Math.max(currentMax, preSum);
		}

		return currentMax;
	}
	
	public static void main(String[] args) {
		int[] nums;

		/*
		 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
		 * 输出: 6
		 */
		nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		assert 6 == maxSubArray(nums);
		
		/*
		 * 输入: [-1]
		 * 输出: -1
		 */
		nums = new int[] {-1};
		assert -1 == maxSubArray(nums);
		
		/*
		 * 输入: [-2, -1]
		 * 输出: -1
		 */
		nums = new int[] {-2, -1};
		assert -1 == maxSubArray(nums);
		
		/*
		 * 输入: [-2, 1]
		 * 输出: 1
		 */
		nums = new int[] {-2, 1};
		assert 1 == maxSubArray(nums);
	}

}
