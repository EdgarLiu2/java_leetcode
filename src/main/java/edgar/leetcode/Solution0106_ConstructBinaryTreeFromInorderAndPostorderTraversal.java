package edgar.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * Created by liuzhao on 2022/6/29
 */
public class Solution0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length != inorder.length) {
            return null;
        }

        int[] empty = new int[]{-1};
        if (Arrays.equals(postorder, empty) && Arrays.equals(inorder, empty)) {
            return new TreeNode(-1);
        }

        // 基于中序变量构造一个快速查询表
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildTree(inorder, 0, inorder.length - 1, inMap, postorder, 0, postorder.length - 1);
    }

    private static TreeNode buildTree(int[] inorder, int inLeft, int inRight, Map<Integer, Integer> inMap, int[] postorder, int postLeft, int postRight) {

        if (inLeft > inRight || postLeft > postRight) {
            return null;
        }

        // 树的根节点
        int rootValue = postorder[postRight];
        TreeNode rootNode = new TreeNode(rootValue);
        int pIndex = inMap.get(rootValue);

        /*
            中序遍历：[inLeft, pIndex - 1], pIndex, [pIndex + 1, inRight]
            后序遍历：[postLeft, postLeft + pIndex - inLeft - 1], [postLeft + pIndex - inLeft, postRight - 1], postRight
         */
        // 左子树
        rootNode.left = buildTree(inorder, inLeft, pIndex - 1, inMap, postorder, postLeft, postLeft + pIndex - inLeft - 1);
        // 右子树
        rootNode.right = buildTree(inorder, pIndex + 1, inRight, inMap, postorder, postLeft + pIndex - inLeft, postRight - 1);

        return rootNode;
    }

    public static void main(String[] args) {
        int[] postorder;
        int[] inorder;

        /*
         * 输入: postorder = [9,15,7,20,3], inorder = [9,3,15,20,7]
         * 输出: [3,9,20,null,null,15,7]
         */
        postorder = new int[]{9, 15, 7, 20, 3};
        inorder = new int[]{9, 3, 15, 20, 7};
        System.out.println(buildTree(inorder, postorder).toString());

        /*
         * 输入: postorder = [-1], inorder = [-1]
         * 输出: [-1]
         */
        postorder = new int[]{-1};
        inorder = new int[]{-1};
        System.out.println(buildTree(inorder, postorder).toString());
    }
}
