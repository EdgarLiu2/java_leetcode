package edgar.leetcode1_100;

/**
 * 33. 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * 
 * @author Administrator
 *
 */
public class Solution0033_SearchIRotatedSortedArray {
	
	public static int search(int[] nums, int target) {
		return searchHelper(nums, target, 0, nums.length - 1);
    }
	
	public static int searchHelper(int[] nums, int target, int start, int end) {
		
		if (start > end) {
			return -1;
		}
		
		int mid = (start + end) / 2;
		int startValue = nums[start];
		int midValue = nums[mid];
		int endValue = nums[end];
		
		if (startValue == target) {
			return start;
		}
		if (midValue == target) {
			return mid;
		}
		if (endValue == target) {
			return end;
		}
		
		int result = -1;
		
		if (startValue <= midValue) {
			// 前一半是有序的
			if (startValue < target && target < midValue) {
				// 目标值小于中间元素
				result = searchHelper(nums, target, start + 1, mid - 1);
			} else {
				// 目标值大于中间元素
				result = searchHelper(nums, target, mid + 1, end - 1);
			}
		} else {
			// 后一半是有序的
			if (target > midValue && target < endValue) {
				// 目标值大于中间元素
				result = searchHelper(nums, target, mid + 1, end - 1);
			} else {
				// 目标值小于中间元素
				result = searchHelper(nums, target, start + 1, mid - 1);
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		int[] nums;

		/*
		 * 输入：nums = [4,5,6,7,0,1,2], target = 0
		 * 返回：4
		 */
		nums= new int[] {4,5,6,7,0,1,2};
		assert 4 == search(nums, 0);
		
		/*
		 * 输入：nums = [4,5,6,7,0,1,2], target = 3
		 * 返回：-1
		 */
		nums= new int[] {4,5,6,7,0,1,2};
		assert -1 == search(nums, 3);
	}

}
