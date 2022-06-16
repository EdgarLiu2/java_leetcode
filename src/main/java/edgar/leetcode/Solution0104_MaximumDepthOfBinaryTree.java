package edgar.leetcode;

/**
 * 104. 二叉树的最大深度
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * Created by liuzhao on 2022/6/16
 */
public class Solution0104_MaximumDepthOfBinaryTree {
    public static int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    public static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfs(root, 1);
    }

    private static int dfs(TreeNode root, int level) {
        if (root == null) {
            return level - 1;
        }

        int leftMaxDepth = dfs(root.left, level + 1);
        int rightMaxDepth = dfs(root.right, level + 1);

        return leftMaxDepth > rightMaxDepth ? leftMaxDepth : rightMaxDepth;
    }

    public static void main(String[] args) {
        TreeNode root;

        /*
         * 输入：root = [3,9,20,null,null,15,7]
         * 输出：3
         */
        root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        assert 3 == maxDepth(root);
    }
}
