package edgar.leetcode;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. 二叉树的锯齿形层序遍历
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Created by liuzhao on 2022/6/13
 */
public class Solution0103_BinaryTreeZigzagLevelOrderTraversal {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 从第一层开始
        int level = 1;

        while (!queue.isEmpty()) {
            // 当前层有nLevelNumber个节点
            int nLevelNumber = queue.size();
            // 保存下一层的节点
            Queue<TreeNode> newQueue = new LinkedList<>();
            // 保存第level层的结果
            LinkedList<Integer> nLevelResult = new LinkedList<>();


            for (int i = 0; i < nLevelNumber; i++) {
                TreeNode node = queue.poll();

                if (level % 2 == 1) {
                    // 奇数行，从左往右
                    nLevelResult.addLast(node.val);
                } else {
                    // 偶数行，从右往左
                    nLevelResult.addFirst(node.val);
                }

                if (node.left != null) {
                    newQueue.add(node.left);
                }
                if (node.right != null) {
                    newQueue.add(node.right);
                }
            }

            queue.addAll(newQueue);

            // 保存当前level的结果
            result.add(nLevelResult);

            level++;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root;
        List<List<Integer>> result;

        /*
         * 输入：root = [3,9,20,null,null,15,7]
         * 输出：[[3],[20,9],[15,7]]
         */
        root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        result = zigzagLevelOrder(root);
        System.out.println(result);

        /*
         * 输入：root = [1]
         * 输出：[[1]]
         */
        root = new TreeNode(1);
        result = zigzagLevelOrder(root);
        System.out.println(result);

        /*
         * 输入：root = []
         * 输出：[]
         */
        root = null;
        result = zigzagLevelOrder(root);
        System.out.println(result);

        /*
         * 输入：[1,2,3,4,null,null,5]
         * 输出：[[1],[3,2],[4,5]]
         */
        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4), null),
                new TreeNode(3,
                        null, new TreeNode(5)));
        result = zigzagLevelOrder(root);
        System.out.println(result);
    }
}
