package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/balanced-binary-tree/">110. 平衡二叉树</a>
 * Created by liuzhao on 2022/8/5
 */
public class Solution0110_BalancedBinaryTree {
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 如果左、右子树不是平衡二叉树，则整体肯定不是
        if (!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (Math.abs(leftHeight - rightHeight) < 2) {
            // 左右子树高度差一，是平衡二叉树
            return true;
        }

        return false;
    }

    static int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    public static void main(String[] args) {

        TreeNode root;

        /*
         * 输入：root = [3,9,20,null,null,15,7]
         * 输出：true
         */
        root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        assert isBalanced(root);


        /*
         * 输入：root = [1,2,2,3,3,null,null,4,4]
         * 输出：false
         */
        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4),
                                new TreeNode(4)),
                        new TreeNode(3)),
                new TreeNode(2));
        assert !isBalanced(root);

        /*
         * 输入：输入：root = []
         * 输出：true
         */
        root = null;
        assert isBalanced(root);
    }
}
