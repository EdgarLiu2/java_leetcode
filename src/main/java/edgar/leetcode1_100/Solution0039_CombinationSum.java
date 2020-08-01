package edgar.leetcode1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 39. 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 * 
 * @author liuzhao
 *
 */
public class Solution0039_CombinationSum {
	
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		// 结果去重
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		// 深度优先遍历的路径
		Stack<Integer> path = new Stack<Integer>();
		
		// 先排序数组
		Arrays.sort(candidates);
		
		dfs(candidates, 0, target, path, result);
		
		return new ArrayList<List<Integer>>(result);
    }
	
	public static void dfs(int[] candidates, int start, int restValue, Stack<Integer> path, Set<List<Integer>> result) {
		if (restValue == 0) {
			// 剩余为0，找到一个结果
			List<Integer> r = new ArrayList<>(path);
			Collections.sort(r);
			result.add(r);
			return;
		}
		
		for (int i = start; i < candidates.length; i++) {
			if (restValue >= candidates[i]) {
				path.push(candidates[i]);
				dfs(candidates, i, restValue - candidates[i], path, result);
				path.pop();
			}
		}
	}

	public static void main(String[] args) {
		int[] nums;
		List<List<Integer>> result;
		
		/*
		 * 输入：candidates = [2,3,6,7], target = 7,
		 * 所求解集为：
		 * [
		 *   [7],
		 *   [2,2,3]
		 * ]
		 */
		nums= new int[] {2,3,6,7};
		result = combinationSum(nums, 7);
		for (List<Integer> item : result) {
			System.out.println(item);
		}

		/*
		 * 输入：candidates = [2,3,5], target = 8,
		 * 所求解集为：
		 * [
		 *   [2,2,2,2],
		 *   [2,3,3],
		 *   [3,5]
		 * ]
		 */
		nums= new int[] {2,3,5};
		result = combinationSum(nums, 8);
		for (List<Integer> item : result) {
			System.out.println(item);
		}
	}

}
