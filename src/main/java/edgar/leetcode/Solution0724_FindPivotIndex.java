package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/find-pivot-index/">724. 寻找数组的中心下标</a>
 * Created by Edgar.Liu on 2023/4/11
 */
public class Solution0724_FindPivotIndex {
    static int pivotIndex(int[] nums) {
        // 计算数组综合
        int sum = Arrays.stream(nums).sum();
        // 记录i之前元素的总和
        int sumBeforeI = 0;

        for (int i = 0; i < nums.length; i++) {
            // 如果sumBeforeI * 2 + nums[i] == sum，那么i就是数组的中心下标
            if (sumBeforeI * 2 + nums[i] == sum) {
                return i;
            }

            // 移动i之前，将nums[i]加入sumBeforeI
            sumBeforeI += nums[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入：nums = [1, 7, 3, 6, 5, 6]
         * 输出：3
         */
        input = new int[]{1, 7, 3, 6, 5, 6};
        assert 3 == pivotIndex(input);

        /*
         * 输入：nums = [1, 2, 3]
         * 输出：-1
         */
        input = new int[]{1, 2, 3};
        assert -1 == pivotIndex(input);

        /*
         * 输入：nums = [2, 1, -1]
         * 输出：0
         */
        input = new int[]{2, 1, -1};
        assert 0 == pivotIndex(input);
    }
}
