package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/move-zeroes/">283. 移动零</a>
 * Created by Edgar.Liu on 2023/2/1
 */
public class Solution0283_MoveZeroes {
    static void moveZeroes(int[] nums) {
        // 空数组，或者数组长度为1，直接返回
        if (nums == null || nums.length == 1) {
            return;
        }

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // i位置的数不为0时，移动到j的位置
            if (nums[i] != 0) {
                nums[j] = nums[i];
                // j向后移动
                j++;
            }
        }

        // 将位置j到数组尾的所有元素置为0
        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
    }

    public static void main(String[] args) {

        int[] input;

        /*
         * 输入: nums = [0]
         * 输出: [0]
         */
        input = new int[]{0};
        moveZeroes(input);
        assert Arrays.equals(input, new int[]{0});

        /*
         * 输入: nums = [0,1,0,3,12]
         * 输出: [1,3,12,0,0]
         */
        input = new int[]{0, 1, 0, 3, 12};
        moveZeroes(input);
        assert Arrays.equals(input, new int[]{1, 3, 12, 0, 0});
    }
}
