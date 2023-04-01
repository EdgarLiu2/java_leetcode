package edgar.leetcode;

/**
 *
 * <a href="https://leetcode-cn.com/problems/maximum-subarray/">53. 最大子序和</a>
 * Created by Edgar.Liu on 2023/4/1
 *
 */
public class Solution0053_MaximumSubarray {

	/**
	 * 贪心算法，若当前指针所指元素之前的和小于0，则丢弃当前元素之前的数列
	 *
	 * @param nums
	 * @return
	 */
	static int maxSubArray(int[] nums) {

		if (nums == null || nums.length == 0)
			return 0;

		if (nums.length == 1) {
			return nums[0];
		}

		int preSum = -1;
		int currentMax = nums[0];


		for (int num : nums) {
			if (preSum < 0) {
				preSum = num;
			} else {
				preSum += num;
			}

			currentMax = Math.max(currentMax, preSum);
		}

		return currentMax;
	}

	/**
	 * 基于动态规划
	 * @param nums
	 * @return
	 */
	static int maxSubArray2(int[] nums) {
		// 先处理特殊情况
		if (nums == null || nums.length == 0)
			return 0;
		if (nums.length == 1) {
			return nums[0];
		}

		int result = nums[0];
		// 定义dp数组：dp[i]表示到第i个元素，最大子序列和
		int[] dp = new int[nums.length];
		dp[0] = nums[0];

		// 遍历nums
		for (int i = 1; i < nums.length; i++) {
			dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
			result = Math.max(result, dp[i]);
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums;

		/*
		 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
		 * 输出: 6
		 */
		nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		assert 6 == maxSubArray(nums);
		assert 6 == maxSubArray2(nums);

		/*
		 * 输入: [-1]
		 * 输出: -1
		 */
		nums = new int[] {-1};
		assert -1 == maxSubArray(nums);
		assert -1 == maxSubArray2(nums);

		/*
		 * 输入: [-2, -1]
		 * 输出: -1
		 */
		nums = new int[] {-2, -1};
		assert -1 == maxSubArray(nums);
		assert -1 == maxSubArray2(nums);

		/*
		 * 输入: [-2, 1]
		 * 输出: 1
		 */
		nums = new int[] {-2, 1};
		assert 1 == maxSubArray(nums);
		assert 1 == maxSubArray2(nums);
	}

}
