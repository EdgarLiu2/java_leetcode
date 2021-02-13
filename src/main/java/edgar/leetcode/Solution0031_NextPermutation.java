package edgar.leetcode;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * https://leetcode-cn.com/problems/next-permutation/
 * 
 * @author Administrator
 *
 */
public class Solution0031_NextPermutation {
	
	public static void nextPermutation(int[] nums) {
		int n = nums.length - 1;
		for (int i = n - 1; i >=0; i--) {
			if (nums[i] < nums[i+1]) {
				/* 
				 * 从int i = nums.length - 2开始，如果nums[i] >= nums[i + 1]，说明其前一个元素更大，不做任何处理。
				 * 这样递推只要nums[i] >= nums[i + 1]成立，说明nums[i]是目前最大的数字，与其右边的数字交换得到的都是更小的排列。
				 */
				int j = nums.length - 1;
				while (j > i && nums[j] <= nums[i]) {
					/*
					 * 当nums[i] < nums[i + 1]时，nums[i]右边的数字有比它大的了，但是为了保证找到下一个更大的排列而不是任意更大排列，
					 * 还需要通过一个循环去找到其右边比nums[i]大的最小的那个数。从上一条中可以知道，nums[i]右边的元素是递减排列的，
					 * 所以也只需要从右往左，找到第一个大于nums[i]的元素。for(; j > i && nums[j] <= nums[i]; j --);内循环结束，j就是要用于交换的元素。
					 */
					j--;
				}
				swap(nums, i, j);
				/*
				 * 交换完后，只保证了i这一位及其高位排列正确了，但是i的右边还是递减的数字，不是下一个最大的排列。
				 * 例如上面的例子123654，交换后为124653，还需要将i右边变为升序（124356）。这里就是[i + 1, nums.length - 1]的子数组翻转，
				 * 双指针可以完成，但是为了方便，直接排序吧Arrays.sort(nums, i + 1, nums.length);
				 */
				reverse(nums, i + 1, n);
				return;
			}
		}
		reverse(nums, 0, n);
    }
	
	private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
	
	private static void reverse(int[] nums, int lo, int hi){
        while(lo < hi){
        	swap(nums, lo++, hi--);
        }
    }

	public static void main(String[] args) {
		int[] nums;
		
		/*
		 * 输入：1,2,3
		 * 返回：1,3,2
		 */
		nums= new int[] {1,2,3};
		nextPermutation(nums);
		Util.printOneDimIntArray(nums, nums.length);
		assert Arrays.equals(nums, new int[] {1,3,2});
		
		/*
		 * 输入：3,2,1
		 * 返回：1,2,3
		 */
		nums= new int[] {3,2,1};
		nextPermutation(nums);
		Util.printOneDimIntArray(nums, nums.length);
		assert Arrays.equals(nums, new int[] {1,2,3});
		
		/*
		 * 输入：1,1,5
		 * 返回：1,5,1
		 */
		nums= new int[] {1,1,5};
		nextPermutation(nums);
		Util.printOneDimIntArray(nums, nums.length);
		assert Arrays.equals(nums, new int[] {1,2,3});
	}

}
