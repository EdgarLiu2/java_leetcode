package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/">145. 二叉树的后序遍历</a>
 * Created by Edgar.Liu on 2023/2/19
 */
public class Solution0145_BinaryTreePostorderTraversal {
    static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // 空树
        if (root == null) {
            return result;
        }

        visit(root, result);

        return result;
    }

    private static void visit(TreeNode node, List<Integer> result) {

        // 遍历左子树
        if (node.left != null) {
            visit(node.left, result);
        }

        // 遍历右子树
        if (node.right != null) {
            visit(node.right, result);
        }

        result.add(node.val);
    }
}
