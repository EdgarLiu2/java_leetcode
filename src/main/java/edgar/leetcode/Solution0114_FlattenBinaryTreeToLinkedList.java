package edgar.leetcode;

import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/">114. 二叉树展开为链表</a>
 * Created by liuzhao on 2022/8/6
 */
public class Solution0114_FlattenBinaryTreeToLinkedList {
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // 将左子树打平
        TreeNode leftTree = root.left;
        if (leftTree != null) {
            root.left = null;
            flatten(leftTree);
        }

        // 将右子树打平
        TreeNode rightTree = root.right;
        if (rightTree != null) {
            root.right = null;
            flatten(rightTree);
        }

        // 把左子树放到根节点右侧
        if (leftTree != null) {
            root.right = leftTree;
            // 找到leftTree最后一个节点
            while (leftTree.right != null) {
                leftTree = leftTree.right;
            }
        } else {
            leftTree = root;
        }

        // 把右子树放到leftTree最后一个节点的右侧
        leftTree.right = rightTree;
    }

    public static void main(String[] args) {
        TreeNode root;
        List<Integer> integers;

        /*
         * 输入：root = [1,2,5,3,4,null,6]
         * 输出：[1,null,2,null,3,null,4,null,5,null,6]
         */
        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4)),
                new TreeNode(5,
                        null,
                        new TreeNode(6)));
        flatten(root);
        integers = root.preorderTraversal();
        integers.forEach(t -> System.out.print(t + " -> "));
        System.out.println();


        /*
         * 输入：root = []
         * 输出：[]
         */
        root = null;
        flatten(root);
        assert root == null;


        /*
         * 输入：root = [0]
         * 输出：[0]
         */
        root = new TreeNode(0);
        flatten(root);
        integers = root.preorderTraversal();
        integers.forEach(t -> System.out.print(t + " -> "));
        System.out.println();
    }
}
