package edgar.leetcode;

import java.util.Arrays;

/**
 * Description: 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <a href="https://leetcode.cn/problems/maximum-product-of-three-numbers/">628. 三个数的最大乘积</a>
 *
 * @author Edgar.Liu
 * @since 2023/5/2 - 20:26
 */
public class Solution0628_MaximumProductOfThreeNumbers {
    static int maximumProduct1(int[] nums) {
        // 特殊情况
        if (nums == null || nums.length < 3) {
            return 0;
        }

        // 对数组排序
        Arrays.sort(nums);
        int n = nums.length;

        // 两种情况，取更大值
        return Math.max(
                // 情况1：3个数符号相同，取最大3个数字
                nums[n - 3] * nums[n - 2] * nums[n - 1],
                // 情况2：有正有负，取最小的2个负数和最大的1个正数
                nums[0] * nums[1] * nums[n - 1]
        );
    }

    static int maximumProduct2(int[] nums) {
        // 特殊情况
        if (nums == null || nums.length < 3) {
            return 0;
        }

        // 数组中最大的3个数
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        // 数组中最小的2个数
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        // 对数组nums进行线性扫描，找出数组中最大的3个数，和最小的2个数
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        return Math.max(
                // 情况1：3个数符号相同，取最大3个数字
                max1 * max2 * max3,
                // 情况2：有正有负，取最小的2个负数和最大的1个正数
                min1 * min2 * max1
        );
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入：nums = [1,2,3]
         * 输出：6
         */
        input = new int[] {1,2,3};
        assert 6 == maximumProduct1(input);
        assert 6 == maximumProduct2(input);

        /*
         * 输入：nums = [1,2,3,4]
         * 输出：24
         */
        input = new int[] {1,2,3,4};
        assert 24 == maximumProduct1(input);
        assert 24 == maximumProduct2(input);

        /*
         * 输入：nums = [-1,-2,-3]
         * 输出：-6
         */
        input = new int[] {-1,-2,-3};
        assert -6 == maximumProduct1(input);
        assert -6 == maximumProduct2(input);
    }
}
