package edgar.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode-cn.com/problems/permutations-ii/">47. 全排列 II</a>
 *
 * @author liuzhao
 *
 */
public class Solution0047_PermutationsII {
	
	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length == 0) {
			return result;
		} else if (nums.length == 1) {
			List<Integer> r = List.of(nums[0]);
			result.add(r);
			return result;
		}
		
		// 先对数组进行排序
		Arrays.sort(nums);
		
		// path用做一个栈
		Deque<Integer> path = new ArrayDeque<>();
		// 记录n个数字是否已经在使用
		boolean[] used = new boolean[nums.length];
		dfs(nums, 0, path, used, result);
		
		return result;
	}
	
	public static void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
		
		if (nums.length == depth) {
			result.add(new ArrayList<>(path));
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			if (used[i]) {
				continue;
			}
			
			/*
			 * 剪支条件：
			 * - 当前要走的分支，跟之前分支完全一样
			 * - 且之前的分支是刚被撤销回退的分支
			 */
			if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
				continue;
			}
			
			path.addLast(nums[i]);
			used[i] = true;
			dfs(nums, depth + 1, path, used, result);
			used[i] = false;
			path.removeLast();
		}
	}

	public static List<List<Integer>> permuteUnique2(int[] nums) {
		Set<List<Integer>> result = new HashSet<>();
		if (nums.length == 0) {
			return new ArrayList<>(result);
		} else if (nums.length == 1) {
			List<Integer> r = List.of(nums[0]);
			result.add(r);
			return new ArrayList<>(result);
		}
		
		List<Integer> list = new ArrayList<>();
		for (int num : nums) {
			list.add(num);
		}
		
		dfs(list, new ArrayList<>(), result);
		
		return new ArrayList<>(result);
	}
	
	public static void dfs(List<Integer> unused_nums, List<Integer> tempResult, Set<List<Integer>> result) {
		if (unused_nums.isEmpty()) {
			result.add(tempResult);
			return;
		}
		
		for (Integer currunt_num : unused_nums) {
			List<Integer> new_unused_nums = new ArrayList<>(unused_nums);
			new_unused_nums.remove(currunt_num);
			
			List<Integer> newTempResult = new ArrayList<>(tempResult);
			newTempResult.add(currunt_num);
			
			dfs(new_unused_nums, newTempResult, result);
		}
	}
	
	public static void main(String[] args) {
		int[] nums;
		List<List<Integer>> result;
		
		/*
		 * 输入: [1,1,2]
		 * 输出:
		 * [
		 *   [1,1,2],
		 *   [1,2,1],
		 *   [2,1,1]
		 * ]
		 */
		nums= new int[] {1,1,2};
		result = permuteUnique(nums);
		System.out.println(result);

	}

}
