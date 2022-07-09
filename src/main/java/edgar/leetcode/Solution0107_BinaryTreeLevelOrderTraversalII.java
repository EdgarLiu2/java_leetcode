package edgar.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 07. 二叉树的层序遍历 II
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 *
 * Created by liuzhao on 2022/7/9
 */
public class Solution0107_BinaryTreeLevelOrderTraversalII {

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 保存结果
        LinkedList<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        // 保存未遍历完的树节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            // 第n层的所有节点
            List<Integer> nLevel = new ArrayList<>();

            // 当前queue有len个节点，所以循环len次
            for (int i = 0; i < len; i++) {
                TreeNode n = queue.poll();

                nLevel.add(n.val);

                // 左子树非空
                if (n.left != null) {
                    queue.offer(n.left);
                }

                // 右子树非空
                if (n.right != null) {
                    queue.offer(n.right);
                }
            }

            // 当前层的遍历结果放到第一个
            result.addFirst(nLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root;
        List<List<Integer>> result;

        /*
         * 输入：root = [3,9,20,null,null,15,7]
         * 输出：[[15,7],[9,20],[3]]
         */
        root = new TreeNode(1,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        result = levelOrderBottom(root);
        System.out.println(result);

        /*
         * 输入：root = [1]
         * 输出：[[1]]
         */
        root = new TreeNode(1);
        result = levelOrderBottom(root);
        System.out.println(result);

        /*
         * 输入：root = []
         * 输出：[]
         */
        root = null;
        result = levelOrderBottom(root);
        System.out.println(result);
    }
}
