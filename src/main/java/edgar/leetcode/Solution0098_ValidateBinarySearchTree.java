package edgar.leetcode;

/**
 * 98. 验证二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 
 * @author liuzhao
 *
 */
public class Solution0098_ValidateBinarySearchTree {
	
	public static boolean isValidBST(TreeNode root) {
		return validTreeBetween(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
	
	public static boolean validTreeBetween(TreeNode root, long min, long max) {
		if (root == null) {
			return true;
		}
		
		if (root.val >= max || root.val <= min) {
			// 当前节点不在不在(min, max)范围内
			return false;
		}
		
		// 检测左子树
		if (!validTreeBetween(root.left, min, root.val)) {
			return false;
		}
		
		// 检测右子树
		if (!validTreeBetween(root.right, root.val, max)) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		
		TreeNode root;
		
		/*
		 * 输入：root = [2,1,3]
		 * 输出：true
		 */
		root = new TreeNode(2,
				new TreeNode(1),
				new TreeNode(3));
		assert isValidBST(root);
		
		/*
		 * 输入：root = [5,1,4,null,null,3,6]
		 * 输出：false
		 */
		root = new TreeNode(5,
				new TreeNode(1),
				new TreeNode(4, new TreeNode(3), new TreeNode(6)));
		assert !isValidBST	(root);
		
		/*
		 * 输入：root = [5,4,6,null,null,3,7]
		 * 输出：false
		 */
		root = new TreeNode(5,
				new TreeNode(4),
				new TreeNode(6, new TreeNode(3), new TreeNode(7)));
		assert !isValidBST	(root);
	}

}
