package edgar.interview;

import edgar.leetcode.TreeNode;

/**
 * Description: 二叉树中最大二叉搜索树的节点数
 *
 * @author Edgar.Liu
 * @since 2023/11/13 - 23:29
 */
public class MaxSubBSTSize {
    static int maxSubBSTSize(TreeNode root) {
        return doMaxSubBSTSize(root).maxSubBSTSize;
    }

    private static MaxSubBSTSizeInfo doMaxSubBSTSize(TreeNode root) {
        // 空树可以视为搜索二叉树
        if (root == null) {
            return new MaxSubBSTSizeInfo(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // 获取左右子树的信息
        MaxSubBSTSizeInfo leftInfo = doMaxSubBSTSize(root.left);
        MaxSubBSTSizeInfo rightInfo = doMaxSubBSTSize(root.right);

        // 处理当前root的信息
        // root节点，左子树和右子树，最小节点的值
        int min = Math.min(leftInfo.min, rightInfo.min);
        min = Math.min(min, root.val);
        // root节点，左子树和右子树，最大节点的值
        int max = Math.max(leftInfo.max, rightInfo.max);
        max = Math.max(max, root.val);

        // 以root为头节点的子树，是否为搜索二叉树
        // 首先左、右子树都需要时搜索二叉树
        boolean isBST = leftInfo.isBST && rightInfo.isBST;
        if (isBST) {
            // root节点的值，应比左子树的max大，且比右子树的min大
            isBST = leftInfo.max <= root.val && root.val <= rightInfo.min;
        }

        // 以root节点下最大搜索二叉树的节点数
        int maxSubBSTSize = Math.max(leftInfo.maxSubBSTSize, rightInfo.maxSubBSTSize);
        if (isBST) {
            // 因为左、右子树都是搜索二叉树，所以左、右子树的节点数就是响应的maxSubBSTSize
            maxSubBSTSize = leftInfo.maxSubBSTSize + rightInfo.maxSubBSTSize + 1;
        }

        return new MaxSubBSTSizeInfo(maxSubBSTSize, isBST, min, max);
    }
}

class MaxSubBSTSizeInfo {
    public int maxSubBSTSize;
    public boolean isBST;
    public int min;
    public int max;

    public MaxSubBSTSizeInfo(int maxSubBSTSize, boolean isBST, int min, int max) {
        this.maxSubBSTSize = maxSubBSTSize;
        this.isBST = isBST;
        this.min = min;
        this.max = max;
    }
}