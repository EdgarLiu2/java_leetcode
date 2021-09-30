package edgar.leetcode;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * https://leetcode-cn.com/problems/sort-colors/
 * 
 * @author liuzhao
 *
 */
public class Solution0075_SortColors {
	
	public static void sortColors(int[] nums) {
		if (nums.length <= 1) {
			return;
		}
		/*
		 * all in [0, p0) == 0
		 * all in [p0, i) == 1
		 * all in [p2, len - 1) == 2
		 */
		int p0 = 0;
		//while (nums[p0] != 0)
		int p2 = nums.length - 1;
		int i = p0;

		while(i <= p2) {
			switch (nums[i]) {
			case 0:
				// i的值放到p0
				swap(nums, i, p0);
				// p0和i向右移动
				p0++;
				i++;
				
				break;
			case 1:
				// 当前为1，i继续向右
				i++;
				break;
			case 2:
				// i的值放到p2
				swap(nums, i, p2);
				// p2向左移动
				p2--;
				break;
			default:
				break;
			}
		}
    }
	
	private static void swap(int[] nums, int index1, int index2) {
		int t = nums[index2];
		nums[index2] = nums[index1];
		nums[index1] = t;
	}
	
	public static void main(String[] args) {
		int[] input;

		/*
		 * 输入：nums = [2,0,2,1,1,0]
		 * 输出：[0,0,1,1,2,2]
		 */
		input = new int[]{2,0,2,1,1,0};
		sortColors(input);
		assert Arrays.equals(new int[]{0,0,1,1,2,2}, input);
		
		/*
		 * 输入：nums = [2,0,1]
		 * 输出：[0,1,2]
		 */
		input = new int[]{2,0,1};
		sortColors(input);
		assert Arrays.equals(new int[]{0,1,2}, input);
		
		/*
		 * 输入：nums = [0]
		 * 输出：[0]
		 */
		input = new int[]{0};
		sortColors(input);
		assert Arrays.equals(new int[]{0}, input);
		
		/*
		 * 输入：nums = [1]
		 * 输出：[1]
		 */
		input = new int[]{1};
		sortColors(input);
		assert Arrays.equals(new int[]{1}, input);
	}

}
