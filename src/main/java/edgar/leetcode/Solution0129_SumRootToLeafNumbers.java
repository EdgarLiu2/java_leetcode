package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/sum-root-to-leaf-numbers/">129. 求根节点到叶节点数字之和</a>
 * Created by liuzhao on 2022/8/16
 */
public class Solution0129_SumRootToLeafNumbers {

    public static int sumNumbers(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }

        // 到目前为止的和
        preSum = preSum * 10 + root.val;

        // 到了叶子节点
        if (root.left == null && root.right == null) {
            return preSum;
        } else {
            return dfs(root.left, preSum) + dfs(root.right, preSum);
        }
    }

    public static void main(String[] args) {
        TreeNode root;

        /*
         * 输入：root = [1,2,3]
         * 输出：25
         * 解释：
         * 从根到叶子节点路径 1->2 代表数字 12
         * 从根到叶子节点路径 1->3 代表数字 13
         * 因此，数字总和 = 12 + 13 = 25
         */
        root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        assert 25 == sumNumbers(root);

        /*
         * 输入：root = [4,9,0,5,1]
         * 输出：1026
         * 解释：
         * 从根到叶子节点路径 4->9->5 代表数字 495
         * 从根到叶子节点路径 4->9->1 代表数字 491
         * 从根到叶子节点路径 4->0 代表数字 40
         * 因此，数字总和 = 495 + 491 + 40 = 1026
         */
        root = new TreeNode(4,
                new TreeNode(9,
                        new TreeNode(5),
                        new TreeNode(1)),
                new TreeNode(0));
        assert 1026 == sumNumbers(root);
    }
}
