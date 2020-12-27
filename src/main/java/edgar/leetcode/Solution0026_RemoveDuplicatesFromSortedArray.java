package edgar.leetcode;

/**
 * 26. 删除排序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 * 
 * @author Administrator
 *
 */
public class Solution0026_RemoveDuplicatesFromSortedArray {
	
	public static int removeDuplicates(int[] nums) {
		if (nums.length < 2) {
			// 长度为0和1时，直接返回
			return nums.length;
		}
		
		int len = 1;
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[len - 1]) {
				nums[len] = nums[i];
				len++;
			}
		}

		return len;
    }
	
	public static int removeDuplicates2(int[] nums) {
		if (nums.length < 2) {
			// 长度为0和1时，直接返回
			return nums.length;
		}
		
		int len = 1;
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[len - 1]) {
				// 跟当前不重复数组的最后一个数组相等，继续向后
				continue;
			}
			
			if (len != i) {
				nums[len] = nums[i];
			}
			
			len++;
		}

		return len;
    }
	
	public static void print_array(int[] nums, int len) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < len; i++) {
			buf.append(nums[i] + " ");
		}
		
		System.out.println(buf.toString());
	}

	public static void main(String[] args) {
		int[] nums;
		int len;

		/*
		 * 输入：nums = [1,1,2]
		 * 返回：2，[1, 2]
		 */
		nums= new int[] {1, 1, 2};
		len = removeDuplicates(nums);
		print_array(nums, len);
		assert 2 == len;
		
		/*
		 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
		 * 返回：5，[0, 1, 2, 3, 4]
		 */
		nums= new int[] {0,0,1,1,1,2,2,3,3,4};
		len = removeDuplicates(nums);
		print_array(nums, len);
		assert 5 == len;
	}

}
