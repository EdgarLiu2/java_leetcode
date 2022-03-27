package edgar.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 96. 不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 * 
 * @author liuzhao
 *
 */
public class Solution0096_UniqueBinarySearchTrees {
	
	public Map<Integer, Integer> cache = new HashMap<>();

	public int numTrees(int n) {
		if (n <= 1) {
			return n;
		}
		
		cache.put(0, 0);
		cache.put(1, 1);
		
		return backtrace(1, n);
    }
	
	public int backtrace(int start, int end) {
		if (start > end) {
			return 1;
		}
		
		int sum = 0;
		
		if (cache.containsKey(end - start + 1)) {
			sum = cache.get(end - start + 1);
		} else {
			for (int i = start; i <= end; i++) {
				// 左子树个数
				int left = backtrace(start, i - 1);
				
				// 右子树个数
				int right = backtrace(i + 1, end);
				
				sum += left * right;
			}
			
			cache.put(end - start + 1, sum);
		}

		return sum;
	}
	
	public static void main(String[] args) {
		
		Solution0096_UniqueBinarySearchTrees s = new Solution0096_UniqueBinarySearchTrees();
		
		/*
		 * 输入：n = 3
		 * 输出：5
		 */
		assert 5 == s.numTrees(3);
				
		/*
		 * 输入：n = 1
		 * 输出：1
		 */
		assert 1 == s.numTrees(1);
		
		/*
		 * 输入：n = 18
		 * 输出：477638700
		 */
		assert 477638700 == s.numTrees(18);
	}
}
