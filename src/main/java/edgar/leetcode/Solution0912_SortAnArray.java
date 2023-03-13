package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/sort-an-array/">912. 排序数组</a>
 * Created by Edgar.Liu on 2023/3/13
 */
public class Solution0912_SortAnArray {
    static int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        qsort(nums, 0, nums.length - 1);
        return nums;
    }

    static void qsort(int[] nums, int start, int end) {
        // 终止条件：start和end之间已经没有元素
        if (end - start < 1) {
            return;
        }

        // 第一个元素作为pivot
        int pivot = nums[start];
        int left = start;
        int right = end;

        while (left < right) {
            // right向左走，直到找到一个比pivot小的
            while (left < right && nums[right] >= pivot) {
                right--;
            }

            // 将右侧right位置比pivot小的元素放到left
            nums[left] = nums[right];

            // left向右走，直到找到一个比pivot大的
            while(left < right && nums[left] <= pivot) {
                left++;
            }

            // 将左侧left位置比pivot大的元素放到right
            nums[right] = nums[left];
        }

        // left和right重合的地方，放pivot
        nums[left] = pivot;

        qsort(nums, start, left - 1);
        qsort(nums, right + 1, end);
    }

    public static void main(String[] args) {
        int[] input;
        int[] result;

        /*
         * 输入：nums = [3,-1]
         * 输出：[-1,3]
         */
        input = new int[]{3, -1};
        result = sortArray(input);
        assert Arrays.equals(new int[]{-1, 3}, result);

        /*
         * 输入：nums = [5,2,3,1]
         * 输出：[1,2,3,5]
         */
        input = new int[]{5, 2, 3, 1};
        result = sortArray(input);
        assert Arrays.equals(new int[]{1, 2, 3, 5}, result);

        /*
         * 输入：nums = [5,1,1,2,0,0]
         * 输出：[0,0,1,1,2,5]
         */
        input = new int[]{5, 1, 1, 2, 0, 0};
        result = sortArray(input);
        assert Arrays.equals(new int[]{0, 0, 1, 1, 2, 5}, result);
    }
}
