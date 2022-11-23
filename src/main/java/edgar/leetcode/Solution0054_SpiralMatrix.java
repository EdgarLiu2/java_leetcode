package edgar.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://leetcode-cn.com/problems/spiral-matrix/">54. 螺旋矩阵</a>
 *
 * @author liuzhao
 *
 */
public class Solution0054_SpiralMatrix {
	
	public static List<Integer> spiralOrder(int[][] matrix) {
		
		if (matrix.length == 0) {
			return Collections.emptyList();
		}
		
		int restNum = matrix.length * matrix[0].length;
		int totalNum = restNum;
		Integer[] arrays = new Integer[totalNum];
		int top = 0;
		int right = matrix[0].length - 1;
		int bottom = matrix.length - 1;
		int left = 0;
		
		while (restNum > 0) {
			// 左上向右上
			for (int i = left; i <= right && restNum > 0; i++) {
				arrays[totalNum - restNum] = matrix[top][i];
				restNum--;
			}
			top++;
			
			// 右上到右下
			for (int i = top; i <= bottom && restNum > 0; i++) {
				arrays[totalNum - restNum] = matrix[i][right];
				restNum--;
			}
			right--;
			
			// 右下到左下
			for (int i = right; i >= left && restNum > 0; i--) {
				arrays[totalNum - restNum] = matrix[bottom][i];
				restNum--;
			}
			bottom--;
			
			// 左下到左上
			for (int i = bottom; i >= top && restNum > 0; i--) {
				arrays[totalNum - restNum] = matrix[i][left];
				restNum--;
			}
			left++;
		}

		return List.of(arrays);
	}
	
	public static List<Integer> spiralOrder2(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		
		if (matrix.length == 0) {
			return result;
		}
		
		int total = matrix.length * matrix[0].length;
		int top = 0;
		int right = matrix[0].length - 1;
		int bottom = matrix.length - 1;
		int left = 0;
		
		while (total > 0) {
			// 左上向右上
			for (int i = left; i <= right && total > 0; i++) {
				result.add(matrix[top][i]);
				total--;
			}
			top++;
			
			// 右上到右下
			for (int i = top; i <= bottom && total > 0; i++) {
				result.add(matrix[i][right]);
				total--;
			}
			right--;
			
			// 右下到左下
			for (int i = right; i >= left && total > 0; i--) {
				result.add(matrix[bottom][i]);
				total--;
			}
			bottom--;
			
			// 左下到左上
			for (int i = bottom; i >= top && total > 0; i--) {
				result.add(matrix[i][left]);
				total--;
			}
			left++;
		}

		return result;
	}

	public static void main(String[] args) {
		int[][] matrix;
		List<Integer> result;
		
		/*
		 * 输入:
		 * [
		 *  [ 1, 2, 3 ],
		 *  [ 4, 5, 6 ],
		 *  [ 7, 8, 9 ]
		 * ]
		 * 输出: [1,2,3,6,9,8,7,4,5]
		 */
		matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		result = spiralOrder(matrix);
		System.out.println(result);
		
		/*
		 * 输入:
		 * [
		 *  [ 1, 2, 3, 4 ],
		 *  [ 5, 6, 7, 8 ],
		 *  [ 9,10,11,12 ]
		 * ]
		 * 输出: [1,2,3,6,9,8,7,4,5]
		 */
		matrix = new int[][] {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
		result = spiralOrder(matrix);
		System.out.println(result);

	}

}
