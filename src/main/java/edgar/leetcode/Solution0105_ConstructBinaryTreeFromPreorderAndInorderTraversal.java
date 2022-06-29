package edgar.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Created by liuzhao on 2022/6/24
 */
public class Solution0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            return null;
        }

        int[] empty = new int[]{-1};
        if (Arrays.equals(preorder, empty) && Arrays.equals(inorder, empty)) {
            return new TreeNode(-1);
        }

        // 基于中序变量构造一个快速查询表
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inMap, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTree(int[] preorder, int preLeft, int preRight, Map<Integer, Integer> inMap, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        int rootValue = preorder[preLeft];
        TreeNode root = new TreeNode(rootValue);
        int pIndex = inMap.get(rootValue);

        /*
            前序遍历：root, [preLeft + 1, pIndex + preLeft - inLeft], [pIndex + preLeft - inLeft + 1, preRight]
            中序遍历：[inLeft, pIndex - 1], pIndex, [pIndex + 1, inRight]
         */
        // 左子树
        root.left = buildTree(preorder, preLeft + 1,  pIndex + preLeft - inLeft, inMap, inorder, inLeft,  pIndex - 1);
        // 右子树
        root.right = buildTree(preorder,  pIndex + preLeft - inLeft + 1, preRight, inMap, inorder,  pIndex + 1, inRight);

        return root;
    }

    public static void main(String[] args) {
        int[] preorder;
        int[] inorder;

        /*
         * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
         * 输出: [3,9,20,null,null,15,7]
         */
        preorder = new int[]{3, 9, 20, 15, 7};
        inorder = new int[]{9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder).toString());

        /*
         * 输入: preorder = [-1], inorder = [-1]
         * 输出: [-1]
         */
        preorder = new int[]{-1};
        inorder = new int[]{-1};
        System.out.println(buildTree(preorder, inorder).toString());
    }
}
