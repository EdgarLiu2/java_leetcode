package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 99. 恢复二叉搜索树
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 * 
 * @author liuzhao
 *
 */
public class Solution0099_RecoverBinarySearchTree {
	private int x = Integer.MIN_VALUE;
	private int y = Integer.MIN_VALUE;
	
	private TreeNode xNode;
	private TreeNode yNode;
	private TreeNode predecessorNode;
	
	/*
	 * 中序变量一遍
	 */
	public void recoverTree(TreeNode root) {
		xNode = null;
		yNode = null;
		predecessorNode = null;
		inorderTraversal(root);
		
		// 交换x和y
		int tmp = xNode.val;
		xNode.val = yNode.val;
		yNode.val = tmp;
	}
	
	public void inorderTraversal(TreeNode root) {
		// 找到有问题的节点x和y
		
		if (root == null) {
			return;
		}
		
		inorderTraversal(root.left);
		if (predecessorNode != null && predecessorNode.val > root.val) {
			yNode = root;
			if (xNode == null) {
				xNode = predecessorNode;
				return;
			}
		}
		predecessorNode = root;
		inorderTraversal(root.right);
	}

	/*
	 * 中序变量两遍
	 */
	public void recoverTree1(TreeNode root) {

		// 对二叉搜索树进行中序变量
		ArrayList<Integer> inorderList = new ArrayList<>();
		inorderTraversal(root, inorderList);
		findTwoSwappedNums(inorderList);
		doRecover(root);
    }
	
	public void inorderTraversal(TreeNode root, List<Integer> list) {
		if (root == null) {
			return;
		}
		
		if (root.left != null) {
			inorderTraversal(root.left, list);
		}
		
		list.add(root.val);
		
		if (root.right != null) {
			inorderTraversal(root.right, list);
		}
	}
	
	public void findTwoSwappedNums(ArrayList<Integer> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i) > list.get(i + 1)) {
				// 找到顺序错误的位置
				y = list.get(i + 1);
				if (x == Integer.MIN_VALUE) {
					x= list.get(i);
				} else {
					break;
				}
			}
		}
	}
	
	public void doRecover(TreeNode root) {
		if (root == null) {
			return;
		}
		
		if (root.val == x) {
			root.val = y;
		} else if (root.val == y) {
			root.val = x;
		}
		
		doRecover(root.left);
		doRecover(root.right);
		
	}
	
	public static void main(String[] args) {
		TreeNode root;
		Solution0099_RecoverBinarySearchTree solution = new Solution0099_RecoverBinarySearchTree();

		/*
		 * 输入：root = [1,3,null,null,2]
		 * 输出：[3,1,null,null,2]
		 */
		root = new TreeNode(1,
				new TreeNode(3, null, new TreeNode(2)),
				null);
		solution.recoverTree(root);
		System.out.println(root.inorderTraversal());
		
		/*
		 * 输入：root = [3,1,4,null,null,2]
		 * 输出：[2,1,4,null,null,3]
		 */
		root = new TreeNode(3,
				new TreeNode(1),
				new TreeNode(4, new TreeNode(2), null));
		solution.recoverTree(root);
		System.out.println(root.inorderTraversal());
	}

}
