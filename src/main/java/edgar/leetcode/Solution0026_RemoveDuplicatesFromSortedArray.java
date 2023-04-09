package edgar.leetcode;

/**
 * <a href="https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/">26. 删除排序数组中的重复项</a>
 * Created by Edgar.Liu on 2023/4/9
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

	/**
	 * 使用双指针算法，删除重复元素后的数组长度
	 * @param nums 输入数组
	 * @return 数组长度
	 */
	public static int removeDuplicates3(int[] nums) {
		if (nums == null) {
			return 0;
		}

		if (nums.length < 2) {
			// 长度为0和1时，直接返回
			return nums.length;
		}

		// 慢指针
		int slow = 0;

		for (int fast = 1; fast < nums.length; fast++) {
			if (nums[fast] != nums[slow]) {
				// 快指针和慢指针数字不同
				// 慢指针向前移动
				slow++;

				// 快指针的值赋给慢指针
				nums[slow] = nums[fast];
			}

			// else
			// 快指针和慢指针数字相同，快指针继续向后
		}

		// 慢指针 + 1，为数组实际长度
		return slow + 1;
	}
	
	public static void print_array(int[] nums, int len) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < len; i++) {
			buf.append(nums[i])
					.append(" ");
		}
		
		System.out.println(buf);
	}

	public static void main(String[] args) {
		int[] nums;
		int len;

		/*
		 * 输入：nums = [1,1,2]
		 * 返回：2，[1, 2]
		 */
		nums = new int[] {1, 1, 2};
		len = removeDuplicates(nums);
		assert 2 == len;
		len = removeDuplicates2(nums);
		assert 2 == len;
		len = removeDuplicates3(nums);
		assert 2 == len;
		print_array(nums, len);

		/*
		 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
		 * 返回：5，[0, 1, 2, 3, 4]
		 */
		nums = new int[] {0,0,1,1,1,2,2,3,3,4};
		len = removeDuplicates(nums);
		assert 5 == len;
		len = removeDuplicates2(nums);
		assert 5 == len;
		len = removeDuplicates3(nums);
		assert 5 == len;
		print_array(nums, len);

	}

}
