package edgar.leetcode1_100;

/**
 * 35. 搜索插入位置
 * https://leetcode-cn.com/problems/search-insert-position/
 * 
 * @author Administrator
 *
 */
public class Solution0035_SearchInsertPosition {
	public static int searchInsert(int[] nums, int target) {
		if (nums.length == 0) {
			return 0;
		}
		
		int start = 0;
		int end = nums.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		return start;
    }

	public static void main(String[] args) {
		int[] nums;

		/**
		 * 输入: [1,3,5,6], 5
		 * 输出: 2
		 */
		nums= new int[] {1,3,5,6};
		assert 2 == searchInsert(nums, 5);
		
		/**
		 * 输入: [1,3,5,6], 2
		 * 输出: 1
		 */
		nums= new int[] {1,3,5,6};
		assert 1 == searchInsert(nums, 2);
		
		/**
		 * 输入: [1,3,5,6], 7
		 * 输出: 4
		 */
		nums= new int[] {1,3,5,6};
		assert 4 == searchInsert(nums, 7);
		
		/**
		 * 输入: [1,3,5,6], 0
		 * 输出: 0
		 */
		nums= new int[] {1,3,5,6};
		assert 0 == searchInsert(nums, 0);
	}

}
