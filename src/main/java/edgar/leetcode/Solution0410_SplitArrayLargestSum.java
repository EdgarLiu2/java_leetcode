package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/split-array-largest-sum/">410. 分割数组的最大值</a>
 * Created by Edgar.Liu on 2023/1/10
 */
public class Solution0410_SplitArrayLargestSum {
    static int splitArray(int[] nums, int k) {

        // 如果只分1个，连续子数组最大值的最小值是数组所有元素和
        int max = Arrays.stream(nums).sum();
        // 如果分成nums.length个，连续子数组最大值的最小值就是所有元素中的最大值
        int min = Arrays.stream(nums).max().orElse(1);

        int answer = 0;

        while (min <= max) {
            // 如果和最大值为mid，可以分成几个连续子数组
            int mid = min + (max - min) / 2;

            int current = need(nums, mid);
            if (current <= k) {
                // 达标往左找
                // 找到一个解，即：每个连续子数组的和都不超过mid，且总共小于等于k个
                answer = mid;

                // 试试更小是否也可以找到
                max = mid - 1;
            } else {
                // 不达标往右找
                // 拆分的子数组多于k、
                min = mid + 1;
            }
        }

        return answer;
    }

    /**
     * 如果连续子数组的和最大是aim，可以最少拆分成几个
     * @param nums 输入数组
     * @param aim 目标每个子数组的和
     * @return 子数组的数目
     */
    static int need(int[] nums, int aim) {
        // 如果数组中有数已经超过aim，无法拆分，返回MAX
        for (int num : nums) {
            if (num > aim) {
                return Integer.MAX_VALUE;
            }
        }

        int part = 1;
        // 当前连续子数组的和
        int all = 0;

        // 依次遍历整个数组
        for (int num : nums) {
            if ((all + num) <= aim) {
                // 可以继续放入当前子数组
                all += num;
            } else {
                // 当前连续子数组已经放不下了
                all = num;
                part++;
            }
        }

        return part;
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入：nums = [7,2,5,10,8], m = 2
         * 输出：18
         */
        input = new int[]{7, 2, 5, 10, 8};
        assert 18 == splitArray(input, 2);

        /*
         * 输入：nums = [1,2,3,4,5], m = 2
         * 输出：9
         */
        input = new int[]{1, 2, 3, 4, 5};
        assert 9 == splitArray(input, 2);

        /*
         * 输入：nums = [1,4,4], m = 3
         * 输出：4
         */
        input = new int[]{1, 4, 4};
        assert 4 == splitArray(input, 3);

        /*
         * 输入：nums = [2,3,1,1,1,1,1], m = 5
         * 输出：3
         */
        input = new int[]{2, 3, 1, 1, 1, 1, 1};
        assert 3 == splitArray(input, 5);
    }
}
