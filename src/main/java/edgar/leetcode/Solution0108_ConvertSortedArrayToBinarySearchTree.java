package edgar.leetcode;

/**
 * 108. 将有序数组转换为二叉搜索树
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 *
 * Created by liuzhao on 2022/7/15
 */
public class Solution0108_ConvertSortedArrayToBinarySearchTree {
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return doConvert(nums, 0, nums.length - 1);
    }

    public static TreeNode doConvert(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return new TreeNode(nums[start]);
        }

        // 中间元素作为root
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = doConvert(nums, start, mid - 1);
        root.right = doConvert(nums, mid + 1, end);

        return root;
    }

    public static void main(String[] args) {
        int[] input;

        /**
         * 输入：nums = [-10,-3,0,5,9]
         * 输出：[0,-3,9,-10,null,5]
         */
        input = new int[]{-10,-3,0,5,9};
        sortedArrayToBST(input);

        /**
         * 输入：nums = [1,3]
         * 输出：[3,1]
         */
        input = new int[]{1,3};
        sortedArrayToBST(input);
    }
}
