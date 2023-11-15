package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/diameter-of-binary-tree/">543. 二叉树的直径</a>
 *
 * @author Edgar.Liu
 * @since 2023/11/13 - 18:39
 */
public class Solution0543_DiameterOfBinaryTree {
    static int diameterOfBinaryTree(TreeNode root) {
        DiameterOfBinaryTreeInfo diameterOfBinaryTreeInfo = doDiameterOfBinaryTree(root);
        return diameterOfBinaryTreeInfo.maxDiameterOfBinaryTree;
    }

    private static DiameterOfBinaryTreeInfo doDiameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return new DiameterOfBinaryTreeInfo(0, 0);
        }

        DiameterOfBinaryTreeInfo leftInfo = doDiameterOfBinaryTree(root.left);
        DiameterOfBinaryTreeInfo rightInfo = doDiameterOfBinaryTree(root.right);

        // 以root为根节点树的高度，是左、右子树高度最大值+1
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        // 左子树直径，右子树直径，和左右子树高度，三者最大值为最终的直径
        int maxDiameterOfBinaryTree = Math.max(leftInfo.maxDiameterOfBinaryTree, rightInfo.maxDiameterOfBinaryTree);
        maxDiameterOfBinaryTree = Math.max(maxDiameterOfBinaryTree, leftInfo.height + rightInfo.height);

        return new DiameterOfBinaryTreeInfo(maxDiameterOfBinaryTree, height);
    }

    public static void main(String[] args) {

        TreeNode root;

        /*
         * 输入：root = [1,2,3,4,5]
         * 输出：3
         * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
         */
        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3));
        assert diameterOfBinaryTree(root) == 3;

        /*
         * 输入：root = [1,2]
         * 输出：1
         */
        root = new TreeNode(1,
                new TreeNode(2),
                null);
        assert diameterOfBinaryTree(root) == 1;

    }
}

class DiameterOfBinaryTreeInfo {
    public int maxDiameterOfBinaryTree;
    public int height;

    public DiameterOfBinaryTreeInfo(int maxDiameterOfBinaryTree, int height) {
        this.maxDiameterOfBinaryTree = maxDiameterOfBinaryTree;
        this.height = height;
    }
}