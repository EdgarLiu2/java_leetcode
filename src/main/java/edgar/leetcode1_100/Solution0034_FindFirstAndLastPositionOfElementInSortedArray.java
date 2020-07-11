package edgar.leetcode1_100;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 
 * @author Administrator
 *
 */
public class Solution0034_FindFirstAndLastPositionOfElementInSortedArray {

	public static int[] searchRange(int[] nums, int target) {
		int[] result = {-1, -1};
		if (nums.length == 0) {
			return result;
		}
		
		int index = searchHelper(nums, target, 0, nums.length);
		if (index == -1) {
			return result;
		}
		
		int start = index;
		int end = index;
		
		while ( start >= 0 && nums[start] == target) {
			start--;
		}
		while ( end < nums.length && nums[end] == target) {
			end++;
		}
		
		result[0] = start + 1;
		result[1] = end - 1;

		return result;
    }
	
	public static int searchHelper(int[] nums, int target, int start, int end) {
		if (start > end) {
			// 没有找到
			return -1;
		}
		
		int mid = (start + end) / 2;
		if (mid >= nums.length) {
			return -1;
		}
		
		if (target == nums[mid]) {
			return mid;
		} else if (target < nums[mid]) {
			return searchHelper(nums, target, start, mid - 1);
		} else {
			return searchHelper(nums, target, mid + 1, end);
		}
	}
	
	public static void main(String[] args) {
		int[] nums;
		int[] expected;

		/**
		 * 输入: nums = [5,7,7,8,8,10], target = 8
		 * 输出: [3,4]
		 */
		nums= new int[] {5,7,7,8,8,10};
		expected = new int[] {3,4};
		assert Arrays.equals(expected, searchRange(nums, 8));
		
		/**
		 * 输入: nums = [1], target = 1
		 * 输出: [0, 0]
		 */
		nums= new int[] {1};
		expected = new int[] {0, 0};
		assert Arrays.equals(expected, searchRange(nums, 1));
		
		/**
		 * 输入: nums = [5,7,7,8,8,10], target = 6
		 * 输出: [-1,-1]
		 */
		nums= new int[] {5,7,7,8,8,10};
		expected = new int[] {-1,-1};
		assert Arrays.equals(expected, searchRange(nums, 6));
		
		/**
		 * 输入: nums = [], target = 0
		 * 输出: [-1,-1]
		 */
		nums= new int[] {};
		expected = new int[] {-1,-1};
		assert Arrays.equals(expected, searchRange(nums, 0));
		
		/**
		 * 输入: nums = [2,2], target = 3
		 * 输出: [-1,-1]
		 */
		nums= new int[] {2, 2};
		expected = new int[] {-1,-1};
		assert Arrays.equals(expected, searchRange(nums, 3));
	}

}
