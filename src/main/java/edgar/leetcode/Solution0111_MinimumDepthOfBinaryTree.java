package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/">111. 二叉树的最小深度</a>
 * Created by liuzhao on 2022/8/5
 */
public class Solution0111_MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        if (leftDepth == 0) {
            return 1 + rightDepth;
        }
        if (rightDepth == 0) {
            return 1 + leftDepth;
        }

        return 1 + Math.min(leftDepth, rightDepth);
    }
}
