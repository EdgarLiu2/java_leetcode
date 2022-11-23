package edgar.leetcode;

import java.util.*;

/**
 * <a href="https://leetcode-cn.com/problems/permutations/">46. 全排列</a>
 *
 * @author liuzhao
 *
 */
public class Solution0046_Permutations {
	
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length == 0) {
			return result;
		} else if (nums.length == 1) {
			List<Integer> r = List.of(nums[0]);
			result.add(r);
			return result;
		}
		// path用做一个栈
		Deque<Integer> path = new ArrayDeque<>();
		// 记录n个数字是否已经在使用
		boolean[] used = new boolean[nums.length];
		dfs(nums, nums.length, 0, path, used, result);
		
		return result;
	}
	
	private static void dfs(int[] nums, int length, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
		
		if (length == depth) {
			result.add(new ArrayList<>(path));
			return;
		}
		
		for (int i = 0; i < length; i++) {
			if (used[i]) {
				continue;
			}
			
			path.addLast(nums[i]);
			used[i] = true;
			dfs(nums, length, depth + 1, path, used, result);
			used[i] = false;
			path.removeLast();
		}
	}

	public static List<List<Integer>> permute2(int[] nums) {
		
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length == 0) {
			return result;
		} else if (nums.length == 1) {
			List<Integer> r = List.of(nums[0]);
			result.add(r);
			return result;
		}
		
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		
		dfs(set, new ArrayList<>(), result);
		
		return result;
	}
	
	public static void dfs(Set<Integer> unused_nums, List<Integer> tempResult, List<List<Integer>> result) {
		if (unused_nums.isEmpty()) {
			result.add(tempResult);
			return;
		}
		
		for (Integer currunt_num : unused_nums) {
			Set<Integer> new_unused_nums = new HashSet<>(unused_nums);
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
		 * 输入: [1,2,3]
		 * 输出:
		 * [
		 *   [1,2,3],
		 *   [1,3,2],
		 *   [2,1,3],
		 *   [2,3,1],
		 *   [3,1,2],
		 *   [3,2,1]
		 * ]
		 */
		nums= new int[] {1,2,3};
		result = permute(nums);
		System.out.println(result);
	}

}
