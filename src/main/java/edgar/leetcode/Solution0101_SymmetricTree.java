package edgar.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/symmetric-tree/">101. 对称二叉树101. 对称二叉树</a>
 * Created by Edgar.Liu on 2023/2/19
 */
public class Solution0101_SymmetricTree {
	
	/**
	 * 使用队列，进行广度优先遍历
	 */
	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(root);

		while (!queue.isEmpty()) {
			// 取出队列中前两个元素
			TreeNode t1 = queue.poll();
			TreeNode t2 = queue.poll();
			
			if (t1 == null && t2 == null) {
				// 两个空指针，对称
				continue;
			}
			
			if (t1 == null || t2 == null) {
				// 一个为空，另一个不为空，不对称，可返回
				return false;
			}
			
			if (t1.val != t2.val) {
				// 两个节点值不同，不对称，可返回
				return false;
			}

			// t1与t2相同，继续检查t1和t2的子树
			
			// t1的左子节点与t2的右子节点配对进行比较
			queue.add(t1.left);
			queue.add(t2.right);
			// t1的右子节点与t2的左子节点配对进行比较
			queue.add(t1.right);
			queue.add(t2.left);
		}
		
		return true;
	}

	/**
	 * 使用递归算法
	 */
	public static boolean isSymmetric2(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		return isMirror(root.left, root.right);
	}
	
	public static boolean isMirror(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			// 两边子树都为空，对称
			return true;
		}
		
		if (t1 == null || t2 == null) {
			// 仅一边为空，不对称
			return false;
		}
		
		// 如果t1和t2节点值相同，且t1与t2满足镜像对称，则返回true
		return (t1.val == t2.val && 
				isMirror(t1.left, t2.right) &&
				isMirror(t1.right, t2.left));
	}
	
	public static void main(String[] args) {
		TreeNode root;
		
		/*
		 * 输入：root = [1,2,2,3,4,4,3]
		 * 输出：true
		 */
		root = new TreeNode(1,
				new TreeNode(2,
					new TreeNode(3),
					new TreeNode(4)),
				new TreeNode(2,
					new TreeNode(4),
					new TreeNode(3))
				);
		assert isSymmetric(root);
		assert isSymmetric2(root);

		/*
		 * 输入：root = [1,2,2,null,3,null,3]
		 * 输出：false
		 */
		root = new TreeNode(1,
				new TreeNode(2,
					null,
					new TreeNode(3)),
				new TreeNode(2,
					null,
					new TreeNode(3))
				);
		assert !isSymmetric(root);
		assert !isSymmetric2(root);
	}

}
