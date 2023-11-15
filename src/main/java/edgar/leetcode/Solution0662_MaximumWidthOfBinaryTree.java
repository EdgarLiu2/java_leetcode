package edgar.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/maximum-width-of-binary-tree/">662. 二叉树最大宽度</a>
 *
 * @author Edgar.Liu
 * @since 2023/11/15 - 10:09
 */
public class Solution0662_MaximumWidthOfBinaryTree {
    static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxWidth = 0;

        // 借助队列完成广度优先遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 放入头节点
        queue.offer(root);
        // 节点和index的映射关系，i节点的左节点是2i，右节点为2i+1
        Map<TreeNode, Integer> nodeIndexMap = new HashMap<>();
        nodeIndexMap.put(root, 1);

        while (!queue.isEmpty()) {
            // 保存queue队列中下一层节点
            Queue<TreeNode> newQueue = new LinkedList<>();

            // 当前层第一个节点
            TreeNode firstNodeCurrentLevel = null;
            int firstNodeIndex = 0;
            int currentNodeIndex = 0;

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                currentNodeIndex = nodeIndexMap.get(node);

                // 初始化当前层第一个节点
                if (firstNodeCurrentLevel == null) {
                    firstNodeCurrentLevel = node;
                    firstNodeIndex = nodeIndexMap.get(firstNodeCurrentLevel);
                }

                if (node.left != null) {
                    // 左子树不空
                    newQueue.offer(node.left);
                    // 在满二叉树中，左孩子的索引位置是 2*父节点索引
                    nodeIndexMap.put(node.left, currentNodeIndex * 2);
                }

                if (node.right != null) {
                    // 右子树不空
                    newQueue.offer(node.right);
                    // 在满二叉树中，右孩子的索引位置是 2*父节点索引+1
                    nodeIndexMap.put(node.right, currentNodeIndex * 2 + 1);
                }
            }

            // 当前层所有节点遍历完成后，计算本层的宽度
            maxWidth = Math.max(maxWidth, currentNodeIndex - firstNodeIndex + 1);

            queue = newQueue;
        }

        return maxWidth;
    }

    public static void main(String[] args) {
        TreeNode root;

        /*
         * 输入：root = [1,3,2,5,3,null,9]
         * 输出：4
         * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
         */
        root = new TreeNode(1,
                new TreeNode(3,
                        new TreeNode(5),
                        new TreeNode(3)),
                new TreeNode(2,
                        null,
                        new TreeNode(9)));
        assert widthOfBinaryTree(root) == 4;

        /*
         * 输入：root = [1,3,2,5,null,null,9,6,null,7]
         * 输出：7
         * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
         */
        root = new TreeNode(1,
                new TreeNode(3,
                        new TreeNode(5,
                                new TreeNode(6),
                                null),
                        null),
                new TreeNode(2,
                        null,
                        new TreeNode(9,
                                new TreeNode(7),
                                null)));
        assert widthOfBinaryTree(root) == 7;

        /*
         * 输入：root = [1,3,2,5]
         * 输出：2
         * 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
         */
        root = new TreeNode(1,
                new TreeNode(3,
                        new TreeNode(5),
                        null),
                new TreeNode(2));
        assert widthOfBinaryTree(root) == 2;
    }
}
