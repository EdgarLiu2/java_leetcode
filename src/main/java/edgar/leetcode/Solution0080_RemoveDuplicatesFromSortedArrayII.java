package edgar.leetcode;

/**
 * 80. 删除有序数组中的重复项 II
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 * 
 * @author liuzhao
 *
 */
public class Solution0080_RemoveDuplicatesFromSortedArrayII {
	
	public static int removeDuplicates(int[] nums) {
		
		if (nums.length <= 2) {
			// 因为允许最多出现2次，所以长度为2以下的无需操作
			return nums.length;
		}
		
		int slow = 1;
		int fast = 2;
		
		// fast需要遍历所有元素
		while (fast < nums.length) {
			if (nums[fast] != nums[slow-1]) {
				// fast所在元素与slow-1不同，移动到slow+1位置
				slow++;
				nums[slow] = nums[fast];
			}
			
			fast++;
		}

		return slow + 1;
    }

	public static void main(String[] args) {
		int[] nums;
		int len;

		/*
		 * 输入：nums = [1,1,1,2,2,3]
		 * 输出：5, nums = [1,1,2,2,3]
		 */
		nums = new int[] {1, 1, 1, 2, 2, 3};
		len = removeDuplicates(nums);
		Util.printOneDimIntArray(nums, len);
		assert 5 == len;

		/*
		 * 输入：nums = [0,0,1,1,1,1,2,3,3]
		 * 输出：7, nums = [0,0,1,1,2,3,3]
		 */
		nums = new int[] {0, 0, 1, 1, 1, 1, 2, 3, 3};
		len = removeDuplicates(nums);
		Util.printOneDimIntArray(nums, len);
		assert 7 == len;
	}

}
