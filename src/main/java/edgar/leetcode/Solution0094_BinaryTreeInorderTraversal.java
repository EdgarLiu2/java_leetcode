package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal/">94. 二叉树的中序遍历</a>
 * Created by Edgar.Liu on 2023/2/15
 *
 */
public class Solution0094_BinaryTreeInorderTraversal {

	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		
		// 空树
		if (root == null) {
			return result;
		}
		
		visit(root, result);

		
		return result;
    }
	
	private static void visit(TreeNode node, List<Integer> result) {
		// 左子节点
		if (node.left != null) {
			visit(node.left, result);
		}
		// 当前节点
		result.add(node.val);
		// 右子节点
		if (node.right != null) {
			visit(node.right, result);
		}
	}
	
	public static void main(String[] args) {
		int[] input;
		TreeNode root;
		List<Integer> result;
		
		/*
		 * 输入：root = [1,null,2,3]
		 * 输出：[1,3,2]
		 */
		input = new int[] {1,-1,2,3};
		root = TreeNode.buildTree(input);
		result = inorderTraversal(root);
		System.out.println(result);

	}

}
