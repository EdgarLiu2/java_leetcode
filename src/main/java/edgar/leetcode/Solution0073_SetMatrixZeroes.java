package edgar.leetcode;

import java.util.HashSet;
import java.util.Set;

/*
 * 73. 矩阵置零
 * https://leetcode-cn.com/problems/set-matrix-zeroes/
 */
public class Solution0073_SetMatrixZeroes {
	
	public static void setZeroes(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		// 保存应全为0的行号
		Set<Integer> zeroRows = new HashSet<>();
		// 保存应全为0的列号
		Set<Integer> zeroColumns = new HashSet<>();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 0) {
					// 找到一个0
					zeroRows.add(i);
					zeroColumns.add(j);
				}
			}
		}
		
		// 第二次遍历
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (zeroRows.contains(i) || zeroColumns.contains(j)) {
					// 当前行或者列应全为0
					matrix[i][j] = 0;
				}
			}
		}
    }

	public static void main(String[] args) {
		int[][] matrix;

		/*
		 * 输入:
		 * [
		 *  [1,1,1],
		 *  [1,0,1],
		 *  [1,1,1]
		 * ]
		 * 输出: [[1,0,1],[0,0,0],[1,0,1]]
		 */
		matrix = new int[][] {{1,1,1},{1,0,1},{1,1,1}};
		setZeroes(matrix);
		Util.printTwoDimIntArray(matrix);
		
		/*
		 * 输入:
		 * [
		 *  [0,1,2,0],
		 *  [3,4,5,2],
		 *  [1,3,1,5]
		 * ]
		 * 输出: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
		 */
		matrix = new int[][] {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
		setZeroes(matrix);
		Util.printTwoDimIntArray(matrix);
	}

}
