package edgar.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 56. 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/
 * 
 * @author liuzhao
 *
 */
public class Solution0056_MergeIntervals {
	
	public static int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return new int[][] {};
		}
		
		// 按照区间起点数进行排序
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		
		Deque<int[]> deque = new ArrayDeque<int[]>(); 
		
		for (int i = 0; i < intervals.length; i++) {
			int start = intervals[i][0];
			int end = intervals[i][1];
			
			if (deque.isEmpty()) {
				deque.addLast(new int[] {start, end});
			} else {
				int[] last = deque.getLast();
				
				if (start <= last[1]) {
					// 新元素跟最后一个元素有重叠
					deque.removeLast();
					deque.addLast(new int[] {last[0], Math.max(end, last[1])});
				} else {
					// 没有重叠
					deque.addLast(new int[] {start, end});
				}
			}
		}

		return deque.toArray(new int[0][]);
	}

	public static void main(String[] args) {
		int[][] nums;
		int[][] result;

		/*
		 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
		 * 输出: [[1,6],[8,10],[15,18]]
		 */
		nums = new int[][] {{1,3},{2,6},{8,10},{15,18}};
		result = merge(nums);
		Solution0048_RotateImage.printMatrix(result);
		
		/*
		 * 输入: intervals = [[1,4],[4,5]]
		 * 输出: [[1,5]]
		 */
		nums = new int[][] {{1,4},{4,5}};
		result = merge(nums);
		Solution0048_RotateImage.printMatrix(result);
		
		/*
		 * 输入: intervals = [[1,4],[2,3]]
		 * 输出: [[1,3]]
		 */
		nums = new int[][] {{1,4},{2,3}};
		result = merge(nums);
		Solution0048_RotateImage.printMatrix(result);
	}

}
