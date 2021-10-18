package edgar.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/**
 * 77. 组合
 * https://leetcode-cn.com/problems/combinations/
 * 
 * @author liuzhao
 *
 */
public class Solution0077_Combinations {

	public static List<List<Integer>> combine(int n, int k) {
		
		List<List<Integer>> result = new ArrayList<>();
		
		if (k <=0 || n < k) {
			return result;
		}
		
		Deque<Integer> path = new ArrayDeque<>();
		dfs(result, path, n, k, 1);

		return result;
    }
	
	private static void dfs(List<List<Integer>> result, Deque<Integer> path, int n, int k, int current) {
		
		// 递归终止条件，当前路径中已经有了k个元素
		if (path.size() == k) {
			result.add(new ArrayList(path));
			return;
		}
		
		// 变量从current到n的元素
		for (int i = current; i <= n; i++) {
			
			// 向当前路径增加一个i
			path.addLast(i);
//			System.out.println("递归之前 => " + path);
			// 从[i+1, n]再继续选择剩余的可能节点
			if (path.size() + (n - i) >= k) {
				// 剩余节点数还够凑到k个，不够时就可以剪枝
				dfs(result, path, n, k, i + 1);
			}
			// 从当前路径删除结尾i
			path.removeLast();
//			System.out.println("递归之前 => " + path);
		}
	}
	
	public static void main(String[] args) {
		List<List<Integer>> result;
		
		/*
		 *	输入：n = 4, k = 2
		 *	输出：
		 *	[
		 *	  [2,4],
		 *	  [3,4],
		 *	  [2,3],
		 *	  [1,2],
		 *	  [1,3],
		 *	  [1,4],
		 *	]
		 */
		result = combine(4, 2);
		assert result.size() == 6;
		
		/*
		 * 输入：n = 1, k = 1
		 * 输出：[[1]]
		 */
		result = combine(1, 1);
		assert result.size() == 1;
	}

}
