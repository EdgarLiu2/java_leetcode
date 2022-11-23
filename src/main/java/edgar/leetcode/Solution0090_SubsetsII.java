package edgar.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode-cn.com/problems/subsets-ii/">90. 子集 II</a>
 *
 * @author liuzhao
 *
 */
public class Solution0090_SubsetsII {
	
	public static void dfs(int[] nums, int index, List<List<Integer>> result, LinkedList<Integer> tmpPath, boolean[] used) {
		
		// 先加入当前path
		if (tmpPath.size() > 0) {
			result.add(new ArrayList<>(tmpPath));
		}
		
		if (index >= nums.length) {
			return;
		}
		
		/*
		 * 都知道组合问题可以抽象为树形结构，那么“使用过”在这个树形结构上是有两个维度的，一个维度是同一树枝上使用过，一个维度是同一树层上使用过。没有理解这两个层面上的“使用过” 是造成大家没有彻底理解去重的根本原因。
		 * 那么问题来了，我们是要同一树层上使用过，还是统一树枝上使用过呢？回看一下题目，元素在同一个组合内是可以重复的，怎么重复都没事，但两个组合不能相同。
		 * 所以我们要去重的是同一树层上的“使用过”，同一树枝上的都是一个组合里的元素，不用去重。
		 */
		for(int i = index; i < nums.length; i++) {	
			if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
				// 如果当前i位置和i-1位置一样，且i-1的数没有使用
				continue;
			}
			
			tmpPath.add(nums[i]);
			used[i] = true;
			dfs(nums, i + 1, result, tmpPath, used);
			used[i] = false;
			tmpPath.removeLast();
		}
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		if (nums.length == 0) {
			return result;
		} else if (nums.length == 1) {
			result.add(List.of(nums[0]));
			return result;
		}
		
		// 对nums排序
		Arrays.sort(nums);
		// 定义对应数字是否已经使用过的标志位
		boolean[] used = new boolean[nums.length];
		LinkedList<Integer> tmpPath = new LinkedList<>();
		
		dfs(nums, 0, result, tmpPath, used);

		return result;
    }
	
	public static void main(String[] args) {
		
		int[] nums;
		List<List<Integer>> result;
		
		/*
		 * 输入：nums = [1,2,2]
		 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
		 */
		nums = new int[] {1, 2, 2};
		result = subsetsWithDup(nums);
		System.out.println(result);
		
		/*
		 * 输入：nums = [0]
		 * 输出：[[],[0]]
		 */
		nums = new int[] {0};
		result = subsetsWithDup(nums);
		System.out.println(result);

	}

}
