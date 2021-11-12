package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * 78. 子集
 * https://leetcode-cn.com/problems/subsets/
 * 
 */
public class Solution0078_Subsets {
	
	public static List<List<Integer>> subsets(int[] nums) {
		// 回溯法
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> currentSet = new ArrayList<>();
		
		// 添加空集
		result.add(new ArrayList<Integer>());
		if (nums.length == 0) {
			return result;
		}
		
		for (int i = 1; i <= nums.length; i++) {
			// 找到所有长度为i的子集，放入result
			backtrace(0, i, nums, currentSet, result);
		}

		return result;
    }
	
	public static void backtrace(int start, int length, int[] nums, List<Integer> currentSet, List<List<Integer>> result) {
		
		if (length == 0) {
			// 无需放入新元素添加到结果集，复制一份
			result.add(new ArrayList<>(currentSet));
			return;
		}
		
		for (int i = start; i < nums.length; i++) {
			currentSet.add(nums[i]);
			backtrace(i + 1, length - 1, nums, currentSet, result);
			currentSet.remove(currentSet.size() - 1);
		}
	}
	
	public static List<List<Integer>> subsets2(int[] nums) {
		
		List<List<Integer>> result = new ArrayList<>();
		// 添加空集
		result.add(new ArrayList<Integer>());
		if (nums.length == 0) {
			return result;
		}
		
		for (int i : nums) {
			// 复制一份之前的结果
			List<List<Integer>> newResult = new ArrayList<>();
			
			// 将nums[i]依次加入到之前的每个子集中
			for (List<Integer> subSet : result) {
				List<Integer> newSubSet = new ArrayList<>(subSet);
				newSubSet.add(i);
				newResult.add(newSubSet);
			}
			
			// 将newResult合并到result中
			result.addAll(newResult);
		}

		return result;
    }

	public static void main(String[] args) {
		
		int[] nums;
		List<List<Integer>> result;
		
		/*
		 * 输入：nums = [0]
		 * 输出：[[],[0]]
		 */
		nums = new int[] {0};
		result = subsets(nums);
		System.out.println(result);
		
		/*
		 * 输入：nums = [1,2,3]
		 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
		 */
		nums = new int[] {1, 2, 3};
		result = subsets(nums);
		System.out.println(result);

	}

}
