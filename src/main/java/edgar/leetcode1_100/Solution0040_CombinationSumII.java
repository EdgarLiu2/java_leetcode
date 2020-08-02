package edgar.leetcode1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 40. 组合总和 II
 * https://leetcode-cn.com/problems/combination-sum-ii/
 * 
 * @author Administrator
 *
 */
public class Solution0040_CombinationSumII {
	
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		if (candidates.length == 0) {
			return Collections.emptyList();
		}
		
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		
		if (candidates.length == 1) {
			if (candidates[0] == target) {
				result.add(Arrays.asList(new Integer[]{target}));
				return new ArrayList<>(result);
			} else {
				return Collections.emptyList();
			}
		}
		
		// 排序
		Arrays.sort(candidates);
		Stack<Integer> path = new Stack<Integer>();
		dfs(candidates, 0, target, path, result);
		
		return new ArrayList<>(result);
	}
	
	public static void dfs(int[] candidates, int start, int restValue, Stack<Integer> path, Set<List<Integer>> result) {
		if (restValue == 0) {
			// 剩余为0，找到一个结果
			List<Integer> answer = new ArrayList<>(path);
			result.add(answer);
		}
		
		if (start >= candidates.length) {
			return;
		}
		
		for (int i = start; i < candidates.length; i++) {
	
			if (restValue >= candidates[i]) {
				 // 小剪枝：与前一个分支在数值上相等，结果一定重复
				if (i > start && candidates[i] == candidates[i-1]) {
					continue;
				}
				
				path.push(candidates[i]);
				dfs(candidates, i+1, restValue - candidates[i], path, result);
				path.pop();
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums;
		List<List<Integer>> result;
		
		/*
		 * 输入：candidates = [10,1,2,7,6,1,5], target = 8,
		 * 所求解集为：
		 * [
		 * 		[1, 7],
		 * 		[1, 2, 5],
		 * 		[2, 6],
		 * 		[1, 1, 6]
		 * ]
		 */
		nums= new int[] {10,1,2,7,6,1,5};
		result = combinationSum2(nums, 8);
		for (List<Integer> item : result) {
			System.out.println(item);
		}
		System.out.println("\n\n");

		/*
		 * 输入：candidates = [2,5,2,1,2], target = 5,
		 * 所求解集为：
		 * [
		 *     [1,2,2],
		 *     [5]
		 */
		nums= new int[] {2,5,2,1,2};
		result = combinationSum2(nums, 5);
		for (List<Integer> item : result) {
			System.out.println(item);
		}

	}

}
