package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/invert-binary-tree/">226. 翻转二叉树</a>
 * Created by Edgar.Liu on 2023/2/27
 */
public class Solution0226_InvertBinaryTree {
    static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 将左右子树，分别递归进行翻转
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        // 左右子树交换
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;
    }
}
