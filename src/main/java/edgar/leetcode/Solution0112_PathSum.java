package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/path-sum/">112. 路径总和</a>
 * Created by liuzhao on 2022/8/5
 */
public class Solution0112_PathSum {
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // root为空，肯定不存在
        if (root == null) {
            return false;
        }

        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null) {
            // 走到当前节点，targetSum=0，且左右子树都为空
            return true;
        }

        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

    public static void main(String[] args) {
        TreeNode root;

        /*
         * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
         * 输出：true
         * 解释：等于目标和的根节点到叶节点路径如上图所示。
         */
        root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11,
                                new TreeNode(7),
                                new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13),
                        new TreeNode(4,
                                null,
                                new TreeNode(1)))
                );
        assert hasPathSum(root, 22);

        /*
         * 输入：root = [1,2,3], targetSum = 5
         * 输出：false
         * 解释：树中存在两条根节点到叶子节点的路径：
         * (1 --> 2): 和为 3
         * (1 --> 3): 和为 4
         * 不存在 sum = 5 的根节点到叶子节点的路径。
         */
        root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3)
                );
        assert !hasPathSum(root, 5);

        /*
         * 输入：root = [], targetSum = 0
         * 输出：false
         * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
         */
        root = null;
        assert !hasPathSum(root, 5);
    }
}
