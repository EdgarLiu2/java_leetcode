package edgar.leetcode;

/**
 * 27. 移除元素
 * https://leetcode-cn.com/problems/remove-element/
 * 
 * @author Administrator
 *
 */
public class Solution0027_RemoveElement {
	public static int removeElement(int[] nums, int val) {
		int len = nums.length;
		
		if (len == 0) {
			return 0;
		}
		
		int i = 0, j = nums.length - 1;
		while (i <= j) {
			if (nums[i] == val) {
				/*
				 * 当我们遇到 nums[i] = valnums[i]=val 时，我们可以将当前元素与最后一个不等于val元素进行交换，
				 * 并释放最后一个元素。这实际上使数组的大小减少了 1。
				 */
				len--;
				while (i < j) {
					if (nums[j] != val) {
						nums[i] = nums[j];
						j--;
						
						break;
					}
					
					j--;
					len--;
				}
			} 
			
			i++;
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
		 * 输入：nums = [3,2,2,3], 3
		 * 返回：2，[1, 2]
		 */
		nums= new int[] {3,2,2,3};
		len = removeElement(nums, 3);
		print_array(nums, len);
		assert 2 == len;
		
		/*
		 * 输入：nums = [0,1,2,2,3,0,4,2], 2
		 * 返回：2，[1, 2]
		 */
		nums= new int[] {0,1,2,2,3,0,4,2};
		len = removeElement(nums, 2);
		print_array(nums, len);
		assert 5 == len;
	}

}
