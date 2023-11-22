package edgar.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/check-completeness-of-a-binary-tree/">958. 二叉树的完全性检验</a>
 *
 * @author Edgar.Liu
 * @since 2023/11/20 - 10:11
 */
public class Solution0958_CheckCompletenessOfABinaryTree {
    static boolean isCompleteTree1(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean allLeafNodesFromNow = false;

        // 使用队列，完成广度优先遍历
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 条件一：如果节点有右子树但是没有左子树，一定不是完全二叉树
            if (node.left == null && node.right != null) {
                return false;
            }

            // 条件二：如果节点有左子树但没有右子树，则从此刻起，后续阶段都应该为叶子节点，即左右孩子为空
            if (allLeafNodesFromNow && (node.left != null || node.right != null)) {
                return false;
            }

            // 将左右孩子加入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (!allLeafNodesFromNow && (node.left == null || node.right == null)) {
                // 第一次遇到左或右节点缺失时，设置allLeafNodesFromNow
                allLeafNodesFromNow = true;
            }

        }

        return true;
    }

    static boolean isCompleteTree2(TreeNode root) {
        return doIsCompleteTree2(root).isCompleteBinaryTree;
    }

    private static IsCompleteTreeInfo doIsCompleteTree2(TreeNode root) {
        if (root == null) {
            // 空节点是完全二叉树和满二叉树
            return new IsCompleteTreeInfo(true, true, 0);
        }


        boolean isFullBinaryTree = false;
        boolean isCompleteBinaryTree = false;
        int height;

        // 从左右节点获取信息
        IsCompleteTreeInfo leftTreeInfo = doIsCompleteTree2(root.left);
        IsCompleteTreeInfo rightTreeInfo = doIsCompleteTree2(root.right);

        // 当前树节点的高度，是左右子树高度较大值+1
        height = Math.max(leftTreeInfo.height, rightTreeInfo.height) + 1;


        if (leftTreeInfo.isFullBinaryTree &&
                rightTreeInfo.isFullBinaryTree &&
                leftTreeInfo.height == rightTreeInfo.height) {
            // 情况1：左右子树都是满二叉树，且高度相同
            isFullBinaryTree = true;
            isCompleteBinaryTree = true;
        } else {
            // 在左右子树都是完全二叉树的基础上
            if (leftTreeInfo.isCompleteBinaryTree && rightTreeInfo.isCompleteBinaryTree) {

                if (leftTreeInfo.isCompleteBinaryTree &&
                        rightTreeInfo.isFullBinaryTree &&
                        leftTreeInfo.height == rightTreeInfo.height + 1) {
                    // 情况2：左子树为完全二叉树，右子树为满二叉树，且左子树比右子树高1
                    isCompleteBinaryTree = true;
                } else if (leftTreeInfo.isFullBinaryTree &&
                        rightTreeInfo.isFullBinaryTree &&
                        leftTreeInfo.height == rightTreeInfo.height + 1) {
                    // 情况3：左子树为满二叉树，右子树为满二叉树，且左子树比右子树高1
                    isCompleteBinaryTree = true;
                } else if (leftTreeInfo.isFullBinaryTree &&
                        rightTreeInfo.isCompleteBinaryTree &&
                        leftTreeInfo.height == rightTreeInfo.height) {
                    // 情况4：左子树为满二叉树，右子树为完全二叉树，且左子树和右子树高度相同
                    isCompleteBinaryTree = true;
                }

            }
        }

        return new IsCompleteTreeInfo(isFullBinaryTree, isCompleteBinaryTree, height);
    }

    public static void main(String[] args) {
        TreeNode root;

        /*
         * 输入：root = [1,2,3,4,5,6]
         * 输出：true
         * 解释：最后一层前的每一层都是满的（即，节点值为 {1} 和 {2,3} 的两层），且最后一层中的所有节点（{4,5,6}）尽可能靠左。
         */
        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                        new TreeNode(7),
                        null));
        assert isCompleteTree1(root);
        assert isCompleteTree2(root);

        /*
         * 输入：root = [1,2,3,4,5,null,7]
         * 输出：false
         * 解释：值为 7 的节点不满足条件「节点尽可能靠左」。
         */
        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                        null,
                        new TreeNode(7)));
        assert !isCompleteTree1(root);
        assert !isCompleteTree2(root);
    }
}

class IsCompleteTreeInfo {
    /**
     * 是否为满二叉树
     */
    public boolean isFullBinaryTree;
    /**
     * 是否为完全二叉树
     */
    public boolean isCompleteBinaryTree;
    /**
     * 树的高度
     */
    public int height;

    public IsCompleteTreeInfo(boolean isFullBinaryTree, boolean isCompleteBinaryTree, int height) {
        this.isFullBinaryTree = isFullBinaryTree;
        this.isCompleteBinaryTree = isCompleteBinaryTree;
        this.height = height;
    }
}