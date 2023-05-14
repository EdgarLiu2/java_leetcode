package edgar.leetcode;

import java.util.Arrays;

/**
 * Description: <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/">167. 两数之和 II - 输入有序数组</a>
 *
 * @author Edgar.Liu
 * @since 2023/5/14 - 17:50
 */
public class Solution0167_TwoSum2InputArrayIsSorted {
    /**
     * 使用二分查找算法
     */
    static int[] twoSum1(int[] numbers, int target) {
        final int[] notFound = new int[]{-1, -1};

        if (numbers == null || numbers.length < 2) {
            return notFound;
        }

        for (int i = 0; i < numbers.length; i++) {
            // 第一个数下标为i

            // 第二个数，从i+1到numbers.length进行二分查找
            int left = i + 1;
            int right = numbers.length - 1;
            int expected = target - numbers[i];

            // 开始二分查找
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] == expected) {
                    // 找到了
                    return new int[]{i + 1, mid + 1};
                }

                // 没有找到，根据mid判断往走还是往右找
                if (numbers[mid] > expected) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return notFound;
    }

    /**
     * 使用双指针算法
     */
    static int[] twoSum2(int[] numbers, int target) {
        final int[] notFound = new int[]{-1, -1};

        if (numbers == null || numbers.length < 2) {
            return notFound;
        }

        // 定义左右指针
        int left = 0;
        int right = numbers.length - 1;

        while (left <= right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // 找到解
                return new int[]{left + 1, right + 1};
            }

            if (sum > target) {
                // 结果大了，right左移
                right--;
            } else {
                left++;
            }
        }

        return notFound;
    }

    public static void main(String[] args) {

        int[] input;
        int[] result;

        /*
         * 输入：numbers = [2,7,11,15], target = 9
         * 输出：[1,2]
         * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
         */
        input = new int[]{2,7,11,15};
        result = twoSum1(input, 9);
        assert Arrays.equals(new int[]{1,2}, result);
        result = twoSum2(input, 9);
        assert Arrays.equals(new int[]{1,2}, result);

        /*
         * 输入：numbers = [2,3,4], target = 6
         * 输出：[1,3]
         * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
         */
        input = new int[]{2,3,4};
        result = twoSum1(input, 6);
        assert Arrays.equals(new int[]{1,3}, result);
        result = twoSum2(input, 6);
        assert Arrays.equals(new int[]{1,3}, result);

        /*
         * 输入：numbers = [-1,0], target = -1
         * 输出：[1,2]
         * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
         */
        input = new int[]{-1,0};
        result = twoSum1(input, -1);
        assert Arrays.equals(new int[]{1,2}, result);
        result = twoSum2(input, -1);
        assert Arrays.equals(new int[]{1,2}, result);

        /*
         * 输入：numbers = [1,2,3,4,4,9,56,90], target = 8
         * 输出：[4,5]
         */
        input = new int[]{1,2,3,4,4,9,56,90};
        result = twoSum1(input, 8);
        assert Arrays.equals(new int[]{4,5}, result);
        result = twoSum2(input, 8);
        assert Arrays.equals(new int[]{4,5}, result);
    }
}
