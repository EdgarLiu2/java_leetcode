package edgar.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 
 * @author liuzhao
 *
 */
public class Solution0102_BinaryTreeLevelOrderTraversal {
	
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		
		if (root == null) {
			return result;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			List<Integer> currentLevelValues = new ArrayList<>();
			int currentLevelNodeNumber = queue.size();
			
			for (int i = 0; i < currentLevelNodeNumber; i++) {
				TreeNode node = queue.poll();
				currentLevelValues.add(node.val);
				
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			
			result.add(currentLevelValues);
		}
		
		return result;
	}
	
	public static List<List<Integer>> levelOrder2(TreeNode root) {
		List<List<Integer>> result = new LinkedList<>();
		
		if (root == null) {
			return result;
		}
		
		// 下一层节点集合
		LinkedList<TreeNode> nextLevelNodes = new LinkedList<>();
		nextLevelNodes.add(root);
		
		// 循环遍历，直到nextLevelNodes为空
		while (!nextLevelNodes.isEmpty()) {
			LinkedList<TreeNode> nextLevelNodes2 = new LinkedList<>();
			List<Integer> nextLevelValues = new LinkedList<>();
			
			for (TreeNode node : nextLevelNodes) {
				nextLevelValues.add(node.val);
				
				if (node.left != null) {
					nextLevelNodes2.add(node.left);
				}
				if (node.right != null) {
					nextLevelNodes2.add(node.right);
				}
			}
			
			// nextLevel遍历完成，插入结果集
			result.add(nextLevelValues);
			// 准备循环下一层
			nextLevelNodes = nextLevelNodes2;
		}
		
		return result;
    }

	public static void main(String[] args) {
		TreeNode root;
		List<List<Integer>> result;

		/*
		 * 输入：root = [3,9,20,null,null,15,7]
		 * 输出：[[3],[9,20],[15,7]]
		 */
		root = new TreeNode(1,
				new TreeNode(9),
				new TreeNode(20,
						new TreeNode(15),
						new TreeNode(7)));
		result = levelOrder(root);
		System.out.println(result);
		
		/*
		 * 输入：root = [1]
		 * 输出：[[1]]
		 */
		root = new TreeNode(1);
		result = levelOrder(root);
		System.out.println(result);
		
		/*
		 * 输入：root = []
		 * 输出：[]
		 */
		root = null;
		result = levelOrder(root);
		System.out.println(result);
	}

}
