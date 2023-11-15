package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/balanced-binary-tree/">110. 平衡二叉树</a>
 * @author Edgar.Liu
 * @since 2022/8/5 - 09:18
 */
public class Solution0110_BalancedBinaryTree {
    static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 如果左、右子树不是平衡二叉树，则整体肯定不是
        if (!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        // 左右子树高度差一，是平衡二叉树
        return Math.abs(leftHeight - rightHeight) < 2;
    }

    static int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    static boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = checkSubTreeHeight(root.left);
        int rightHeight = checkSubTreeHeight(root.right);

        return leftHeight != -1 && rightHeight != -1 && Math.abs(leftHeight - rightHeight) <= 1;
    }

    /**
     * 检查子树是否为平衡二叉树
     * @param root 树的根节点
     * @return 如果是平衡二叉树返回树的高度，否则返回-1。
     */
    static int checkSubTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = checkSubTreeHeight(root.left);
        int rightHeight = checkSubTreeHeight(root.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    static boolean isBalanced3(TreeNode root) {
        return isBalancedBinaryTree(root).isBalanced;
    }

    private static BinaryTreeInfo isBalancedBinaryTree(TreeNode root) {
        // 空树是平衡的，且高度为0
        if (root == null) {
            return new BinaryTreeInfo(true, 0);
        }

        // 分别从左右两个子树获取信息
        BinaryTreeInfo leftInfo = isBalancedBinaryTree(root.left);
        BinaryTreeInfo rightInfo = isBalancedBinaryTree(root.right);

        // 包含root，节点是否为平衡二叉树的条件
        boolean isBalanced = false;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        if (leftInfo.isBalanced // 左子树是平衡的
                && rightInfo.isBalanced // 右子树是平衡的
                && Math.abs(leftInfo.height - rightInfo.height) < 2 // 左右子树高度小于2
        ) {
            isBalanced = true;
        }

        return new BinaryTreeInfo(isBalanced, height);
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
        assert isBalanced2(root);
        assert isBalanced3(root);


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
        assert !isBalanced2(root);
        assert !isBalanced3(root);

        /*
         * 输入：输入：root = []
         * 输出：true
         */
        root = null;
        assert isBalanced(root);
        assert isBalanced2(root);
        assert isBalanced3(root);
    }
}

class BinaryTreeInfo {
    public boolean isBalanced;
    public int height;

    public BinaryTreeInfo(boolean isBalanced, int height) {
        this.isBalanced = isBalanced;
        this.height = height;
    }
}