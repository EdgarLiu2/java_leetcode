package edgar.leetcode;

/**
 * 100. 相同的树
 * https://leetcode.cn/problems/same-tree/
 * 
 * @author liuzhao
 *
 */
public class Solution0100_SameTree {
	
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (q == null || p == null) {
			return false;
		}
		
		if (p.val != q.val) {
			return false;
		}

		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

	public static void main(String[] args) {
		TreeNode p, q;

		/*
		 * 输入：p = [1,2,3], q = [1,2,3]
		 * 输出：true
		 */
		p = new TreeNode(1,
				new TreeNode(2),
				new TreeNode(3));
		q = new TreeNode(1,
				new TreeNode(2),
				new TreeNode(3));
		assert isSameTree(p, q);
		
		/*
		 * 输入：p = [1,2], q = [1,null,2]
		 * 输出：false
		 */
		p = new TreeNode(1,
				new TreeNode(2),
				null);
		q = new TreeNode(1,
				null,
				new TreeNode(2));
		assert !isSameTree(p, q);
	}

}
