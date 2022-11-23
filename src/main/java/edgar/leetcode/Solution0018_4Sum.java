package edgar.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode-cn.com/problems/4sum/">18. 四数之和</a>
 *
 * @author Administrator
 *
 */
public class Solution0018_4Sum {
	
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Set<List<Integer>> result = new HashSet<>();
		
		// 先对输入数组进行排序
		Arrays.sort(nums);
		
		for (int i = 0; i < nums.length - 3; i++) {
			if (i > 0 && nums[i] == nums[i-1]) {
				 // 去重
				continue;
			}
			
			if (nums[i] > 0 && nums[i] > target) {
				continue;
			}
			
			for (int j = i +1; j < nums.length - 2; j++) {
				if (nums[i] > 0 && nums[i] + nums[j] > target) {
					continue;
				}
				
				int L = j + 1;
				int R = nums.length - 1;
				
				while(L < R) {
				
					int cur_sum = nums[i] + nums[j] + nums[L] + nums[R];
					
					if (cur_sum == target) {
						Integer[] match = new Integer[] {nums[i], nums[j], nums[L], nums[R]};
						result.add(List.of(match));
						L++;
					} else if (cur_sum > target) {
						R--;
						
						while (nums[R] == nums[R+1] && R > L) {
							R--;
						}
					} else if (cur_sum < target) {
						L++;
						
						while (nums[L] == nums[L-1] && L < R) {
							L++;
						}
					}
				
				}
			}
		}
		
		// Set to List
		return new ArrayList<>(result);
    }

	public static void main(String[] args) {
		int[] nums;
		List<List<Integer>> result;
		
		// 输入：nums = [1,-2,-5,-4,-3,3,3,5]  和 target = -11
		/*
		 * 输出：
		 * [[-5,-4,-3,1]]
		 */
		nums = new int[] {1,-2,-5,-4,-3,3,3,5};
		result = fourSum(nums, -11);
		System.out.println(result);
		
		// 输入：nums = [0,0,0,0]  和 target = 0
		/*
		 * 输出：
		 * [[0,0,0,0]]
		 */
		nums = new int[] {0,0,0,0};
		result = fourSum(nums, 0);
		System.out.println(result);

		// 输入：nums = [1, 0, -1, 0, -2, 2]  和 target = 0
		/*
		 * 输出：
		 * [
			  [-1,  0, 0, 1],
			  [-2, -1, 1, 2],
			  [-2,  0, 0, 2]
			]
		 */
		nums = new int[] {1, 0, -1, 0, -2, 2};
		result = fourSum(nums, 0);
		System.out.println(result);
	}

}
