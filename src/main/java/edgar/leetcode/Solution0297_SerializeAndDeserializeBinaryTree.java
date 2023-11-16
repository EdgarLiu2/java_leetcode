package edgar.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/">297. 二叉树的序列化与反序列化</a>
 *
 * @author Edgar.Liu
 * @since 2023/11/16 - 07:57
 */
public class Solution0297_SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    static String serialize(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        doSerialize(root, list);

        return list.stream()
                .map(n -> n == null ? "null" : n.val)
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    static void doSerialize(TreeNode root, LinkedList<TreeNode> list) {
        // 先序遍历的方式，遍历整个树
        list.add(root);

        if (root != null) {
            // 递归遍历左右子树
            doSerialize(root.left, list);
            doSerialize(root.right, list);
        }
    }

    // Decodes your encoded data to tree.
    static TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return doDeserialize(queue);
    }

    static TreeNode doDeserialize(Queue<String> queue) {
        // 先序遍历的方式，还原整个树
        String item = queue.poll();
        if (item == null || "null".equals(item)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(item));
        node.left = doDeserialize(queue);
        node.right = doDeserialize(queue);

        return node;
    }

    public static void main(String[] args) {
        TreeNode root;
        TreeNode newRoot;
        // binary tree data
        String treeString;
        String serializedTree;


        /*
         * 输入：root = [1,2,3,null,null,4,5]
         * 输出：[1,2,3,null,null,4,5]
         */
        root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3,
                        new TreeNode(4),
                        new TreeNode(5))
        );
        treeString = "1,2,null,null,3,4,null,null,5,null,null";
        serializedTree = serialize(root);
        assert treeString.equals(serializedTree);
        newRoot = deserialize(serializedTree);
        assert root.val == newRoot.val;

        /*
         * 输入：root = []
         * 输出：[]
         */
        root = null;
        treeString = "null";
        serializedTree = serialize(root);
        assert treeString.equals(serializedTree);
        newRoot = deserialize(serializedTree);
        assert root == newRoot;

        /*
         * 输入：root = [1]
         * 输出：[1]
         */
        root = new TreeNode(1);
        treeString = "1,null,null";
        serializedTree = serialize(root);
        assert treeString.equals(serializedTree);
        newRoot = deserialize(serializedTree);
        assert root.val == newRoot.val;

        /*
         * 输入：root = [1,2]
         * 输出：[1,2]
         */
        root = new TreeNode(1,
                new TreeNode(2),
                null);
        treeString = "1,2,null,null,null";
        serializedTree = serialize(root);
        assert treeString.equals(serializedTree);
        newRoot = deserialize(serializedTree);
        assert root.val == newRoot.val;

    }
}
