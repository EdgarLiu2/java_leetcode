package edgar.leetcode;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * https://leetcode-cn.com/problems/3sum-closest/
 * 
 * @author Administrator
 *
 */
public class Solution0016_3SumClosest {
	
	public static int threeSumClosest(int[] nums, int target) {
		// 先对输入数组进行排序
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[2];
		
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i-1] == nums[i]) {
				continue;
			}
			
			int L = i + 1;
			int R = nums.length - 1;
			
			while (L < R) {
				int sum = nums[i] + nums[L] + nums[R];
				if (sum == target) {
					return sum;
				}
				
				if (Math.abs(target - sum) < Math.abs(target - result)) {
					result = sum;
				}
				
				if (sum > target) {
					R--;
					
					while (nums[R] == nums[R + 1] && R > L) {
						R--;
					}
				} else if (sum < target) {
					L++;
					
					while (nums[L] == nums[L - 1] && R > L) {
						L++;
					}
				}
			}
		}
		
		return result;
    }

	public static void main(String[] args) {
		int[] nums;
		int r;
		
		// 输入：nums = [-1，2，1，-4], 和 target = 1
		// 输出：2. (-1 + 2 + 1 = 2).
		nums = new int[] {-1, 2, 1, -4};
		r = threeSumClosest(nums, 1);
		assert 2 == r;
		
		// 输入：nums = [0,0,0], 和 target = 1
		// 输出：0
		nums = new int[] {0,0,0};
		r = threeSumClosest(nums, 1);
		assert 0 == r;
	}

}
