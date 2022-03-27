package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 * 
 * @author liuzhao
 *
 */
public class Solution0095_UniqueBinarySearchTreesII {

	public static List<TreeNode> generateTrees(int n) {
		List<TreeNode> result = new ArrayList<>();
		
		if (n != 0) {
			result = walk(1, n);
		}
		
		return result;
	}
	
	public static List<TreeNode> walk(int start, int end) {
		List<TreeNode> result = new ArrayList<>();
		
		// 递归退出条件
		if (start > end) {
			result.add(null);
			return result;
		}
		
		for(int i = start; i <= end; i++) {
			
			// 生成左子树的所有可能：start to i -1
			List<TreeNode> leftTrees = walk(start, i - 1);
			
			// 生成右子树的所有可能：i + 1 to end
			List<TreeNode> rightTrees = walk(i + 1, end);
			
			// 将左右子树所有可能进行组合
			for (TreeNode left : leftTrees) {
				for (TreeNode right : rightTrees) {
					// 当前 root 节点
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					result.add(root);
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		List<TreeNode> root;

		/*
		 * 输入：n = 3
		 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
		 */
		root = generateTrees(3);
		assert root.size() == 5;
		
		/*
		 * 输入：n = 1
		 * 输出：[[1]]
		 */
		root = generateTrees(1);
		assert 1 == root.size();
	}

}
