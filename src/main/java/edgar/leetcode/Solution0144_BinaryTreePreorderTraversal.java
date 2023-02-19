package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/">144. 二叉树的前序遍历</a>
 * Created by Edgar.Liu on 2023/2/19
 */
public class Solution0144_BinaryTreePreorderTraversal {
    static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // 空树
        if (root == null) {
            return result;
        }

        visit(root, result);

        return result;
    }

    private static void visit(TreeNode node, List<Integer> result) {

        result.add(node.val);

        // 遍历左子树
        if (node.left != null) {
            visit(node.left, result);
        }

        // 遍历右子树
        if (node.right != null) {
            visit(node.right, result);
        }
    }
}
