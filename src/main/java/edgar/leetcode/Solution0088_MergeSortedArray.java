package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/merge-sorted-array/">88. 合并两个有序数组</a>
 * Created by Edgar.Liu on 2023/1/26
 */
public class Solution0088_MergeSortedArray {

	public static void merge1(int[] nums1, int m, int[] nums2, int n) {

		// 将nums2复制到nums1的尾部
		System.arraycopy(nums2, 0, nums1, m, n);
		// 对nums1进行快速排序
		Arrays.sort(nums1);

	}

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		if (n == 0) {
			// 第二个数组长度为0，直接返回
			return;
		}
		
		if (m == 0) {
			// 第一个数组长度为0，直接将第二个数组的值复制到第一个数组
//			for (int i = 0; i < n; i++) {
//				nums1[i] = nums2[i];
//			}
			System.arraycopy(nums2, 0, nums1, 0, n);
			return;
		}
		
		// 数组一尾部指针
		int p1 = m - 1;
		// 数组二尾部指针
		int p2 = n - 1;
		
		// 结果尾部指针
		for (int p = m + n - 1; p >=0; p--) {
			if (p1 >= 0 && p2 >= 0) {
				if (nums1[p1] >= nums2[p2]) {
					// 数组一尾部节点更大
					nums1[p] = nums1[p1];
					p1--;
				} else {
					nums1[p] = nums2[p2];
					p2--;
				}
			} else if (p1 == -1 && p2 >= 0) {
				// 剩下从p2复制
				nums1[p] = nums2[p2];
				p2--;
			} else if (p2 == -1 && p1 >= 0){
				// 剩下从p1复制
				nums1[p] = nums1[p1];
				p1--;
			}
		}
    }
	
	public static void main(String[] args) {
		int[] inputs1;
		int[] inputs2;
		
		/*
		 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
		 * 输出：[1,2,2,3,5,6]
		 */
		inputs1 = new int[]{1, 2, 3, 0, 0, 0};
		inputs2 = new int[]{2, 5, 6};
		merge(inputs1, 3, inputs2, 3);
		assert Arrays.equals(new int[] {1, 2, 2, 3, 5, 6}, inputs1);
		
		/*
		 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
		 * 输出：[1]
		 */
		inputs1 = new int[]{1};
		inputs2 = new int[]{};
		merge(inputs1, 1, inputs2, 0);
		assert Arrays.equals(new int[] {1}, inputs1);
		
		/*
		 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
		 * 输出：[1]
		 */
		inputs1 = new int[]{0};
		inputs2 = new int[]{1};
		merge(inputs1, 0, inputs2, 1);
		assert Arrays.equals(new int[] {1}, inputs1);

	}

}
