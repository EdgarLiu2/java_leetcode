package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Edgar.Liu
 * @since 2023/11/15 - 10:09
 */
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TreeNode treeNode = (TreeNode) o;
		return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
	}

	@Override
	public int hashCode() {
		return Objects.hash(val, left, right);
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
