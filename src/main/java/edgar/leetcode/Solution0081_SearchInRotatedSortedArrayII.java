package edgar.leetcode;

/**
 * 81. 搜索旋转排序数组 II
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * 
 * @author liuzhao
 *
 */
public class Solution0081_SearchInRotatedSortedArrayII {
	
	public static boolean search(int[] nums, int target) {
		
		// 空数组
		if (nums == null || nums.length == 0) {
			return false;
		}
		
		// 只有一个元素
		if (nums.length == 1) {
			return target == nums[0];
		}
		
		int start = 0;
		int end = nums.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			if (nums[mid] == target) {
				return true;
			}
			
			
			if (nums[start] < nums[mid]) {
				// 左侧是有序的
				
				if (target >= nums[start] && target <= nums[mid]) {
					// 在左侧找
					end = mid - 1;
				} else {
					// 在右侧找
					start = mid + 1;
				}
			} else if (nums[mid] < nums[end]) {
				// 右侧是有序的
				
				if (target >= nums[mid] && target <= nums[end]) {
					// 在右侧找
					start = mid + 1;
				} else {
					// 在左侧找
					end = mid - 1;
				}
			} else {
				// 无法判断哪边有序
				if (nums[start] == target) {
					return true;
				} else {
					start++;
				}
			}
		}

		return false;
    }

	public static void main(String[] args) {
		int[] nums;
		
		nums = new int[] {};
		assert !search(nums, 0);
		nums = new int[] {3};
		assert search(nums, 3);

		/*
		 * 输入：nums = [2,5,6,0,0,1,2], target = 0
		 * 输出：true
		 */
		nums = new int[] {2, 5, 6, 0, 0, 1, 2};
		assert search(nums, 0);
		
		/*
		 * 输入：nums = [2,5,6,0,0,1,2], target = 3
		 * 输出：false
		 */
		nums = new int[] {2, 5, 6, 0, 0, 1, 2};
		assert !search(nums, 3);
		
		/*
		 * 输入：nums = [1,0,1,1,1], target = 0
		 * 输出：true
		 */
		nums = new int[] {1, 0, 1, 1, 1};
		assert search(nums, 0);
	}

}
