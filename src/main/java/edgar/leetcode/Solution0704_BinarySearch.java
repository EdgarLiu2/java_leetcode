package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/binary-search/">704. 二分查找</a>
 * Created by Edgar.Liu on 2023/3/12
 */
public class Solution0704_BinarySearch {
    static int search(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
