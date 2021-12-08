package edgar.leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * 
 * @author liuzhao
 *
 */
public class Solution0004_MedianOfTwoSortedArrays {
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		// 如果nums1的长度长，交互nums1和nums2
		if (nums1.length > nums2.length) {
			int[] tmp = nums1;
			nums1 = nums2;
			nums2 = tmp;
		}
		
		// 如果nums1的长度为0，则中位数只跟nums2有关
		if (nums1.length == 0) {
			if (nums2.length % 2 == 1) {
				// nums2奇数个元素
				return nums2[nums2.length/2];
			} else {
				// nums2偶数个元素
				return (nums2[nums2.length/2-1] + nums2[nums2.length/2]) / 2;
			}
		}
		
		// nums1和nums2长度都不为0
		int nums1Left = 0;
		int nums2Left = 0;
		int totalNum = nums1.length + nums2.length;
		
		// 中位数元素在合并后新数组的位置
		// 奇数元素时，中位数是medianNum
		// 偶数元素时，中位数是medianNum-1和medianNum的平均
		int medianNum = totalNum / 2;
		
		while (nums1Left + nums2Left + 2 < medianNum) {
			if (nums1Left == nums1.length - 1) {
				// 第一个数组已经遍历完了
				nums2Left++;
			} else if (nums2Left == nums2.length - 1) {
				// 第二个数组已经遍历完了
				nums1Left++;
			} else if (nums1[nums1Left] <= nums2[nums2Left]) {
				// 第一个数组的nums1Left元素更小
				nums1Left++;
			} else {
				// 第二个数组的nums1Left元素更小
				nums2Left++;
			}
		}
		
		// 如果总数为奇数个元素
		if (totalNum % 2 == 1) {
			return Math.max(nums1[nums1Left], nums2[nums2Left]);
		}

		return 0;
    }

	public static void main(String[] args) {
		int[] nums1;
		int[] nums2;
		
		/*
		 * 输入：nums1 = [1,3], nums2 = [2]
		 * 输出：2.00000
		 * 解释：合并数组 = [1,2,3] ，中位数 2
		 */
		nums1 = new int[] {1, 3};
		nums2 = new int[] {2};
		assert 2d == findMedianSortedArrays(nums1, nums2);

		/*
		 * 输入：nums1 = [1,2], nums2 = [3,4]
		 * 输出：2.50000
		 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
		 */
		nums1 = new int[] {1, 2};
		nums2 = new int[] {3, 4};
		assert 2.5d == findMedianSortedArrays(nums1, nums2);
		
		/*
		 * 输入：nums1 = [0,0], nums2 = [0,0]
		 * 输出：0.00000
		 */
		nums1 = new int[] {0, 0};
		nums2 = new int[] {0, 0};
		assert 0.0d == findMedianSortedArrays(nums1, nums2);
		
		/*
		 * 输入：nums1 = [], nums2 = [1]
		 * 输出：1.00000
		 */
		nums1 = new int[] {};
		nums2 = new int[] {1};
		assert 1.0d == findMedianSortedArrays(nums1, nums2);
		
		/*
		 * 输入：nums1 = [2], nums2 = []
		 * 输出：2.00000
		 */
		nums1 = new int[] {2};
		nums2 = new int[] {};
		assert 2.0d == findMedianSortedArrays(nums1, nums2);
	}

}
