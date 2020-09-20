package edgar.leetcode1_100;

/**
 * 55. 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 * 
 * @author liuzhao
 *
 */
public class Solution0055_JumpGame {
	public static boolean canJump(int[] nums) {
		if (nums.length == 0) {
			return false;
		}
		
		int canReach = nums[0];
		
		for (int i = 0; i < nums.length && i <= canReach; i++) {
			canReach = Math.max(canReach, i + nums[i]);
			
			if (canReach >= nums.length - 1) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int[] nums;
		
		/*
		 * 输入: [2,3,1,1,4]
		 * 输出: true
		 */
		nums = new int[] {2,3,1,1,4};
		assert canJump(nums);

		/*
		 * 输入: [3,2,1,0,4]
		 * 输出: false
		 */
		nums = new int[] {3,2,1,0,4};
		assert !canJump(nums);
		
		/*
		 * 输入: [0,1]
		 * 输出: false
		 */
		nums = new int[] {0,1};
		assert !canJump(nums);
	}

}
