package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	public static TreeNode buildTree(int[] values) {
		return new TreeNode();
	}

	/**
	 * 中序遍历
	 * @return List<Integer>
	 */
	public List<Integer> inorderTraversal() {
		List<Integer> result = new ArrayList<>();
		doInorderTraversal(this, result);
		
		return result;
	}
	
	private static void doInorderTraversal(TreeNode root, List<Integer> list) {
		if (root == null) {
			return;
		}

		if (root.left != null) {
			doInorderTraversal(root.left, list);
		}

		list.add(root.val);
		
		if (root.right != null) {
			doInorderTraversal(root.right, list);
		}
	}

	/**
	 * 前序遍历
	 * @return List<Integer>
	 */
	public List<Integer> preorderTraversal() {
		List<Integer> result = new ArrayList<>();
		doPreorderTraversal(this, result);

		return result;
	}

	private static void doPreorderTraversal(TreeNode root, List<Integer> list) {
		if (root == null) {
			return;
		}

		list.add(root.val);

		if (root.left != null) {
			doInorderTraversal(root.left, list);
		}

		if (root.right != null) {
			doInorderTraversal(root.right, list);
		}
	}
}
