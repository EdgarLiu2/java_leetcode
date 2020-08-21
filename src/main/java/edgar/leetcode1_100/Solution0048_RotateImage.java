package edgar.leetcode1_100;

/**
 * 48. 旋转图像
 * https://leetcode-cn.com/problems/rotate-image/
 * 
 * @author liuzhao
 *
 */
public class Solution0048_RotateImage {
	
	public static void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length != matrix.length) {
			return;
		}

		int num = matrix.length;
		// 先沿右上 - 左下的对角线翻转
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num - i; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[num - j - 1][num - i - 1];
				matrix[num - j - 1][num - i - 1] = tmp;
			}
		}
		
		// 再沿水平中线上下翻转
		for (int i = 0; i < num / 2; i++) {
			for (int j = 0; j < num; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[num - i - 1][j];
				matrix[num - i - 1][j] = tmp;
			}
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (j == 0) {
					builder.append(matrix[i][j]);
				} else {
					builder.append(", " + matrix[i][j]);
				}
			}
			
			builder.append("\n");
		}
		
		System.out.println(builder.toString());
	}

	public static void main(String[] args) {
		int[][] matrix;

		/**
		 * 给定 matrix = 
		 * [
		 *   [1,2,3],
		 *   [4,5,6],
		 *   [7,8,9]
		 * ]
		 * 
		 * 原地旋转输入矩阵，使其变为:
		 * [
		 *   [7,4,1],
		 *   [8,5,2],
		 *   [9,6,3]
		 * ]
		 */
		matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
		rotate(matrix);
		printMatrix(matrix);
		
		/**
		 * 给定 matrix =
		 * [
		 *   [ 5, 1, 9,11],
		 *   [ 2, 4, 8,10],
		 *   [13, 3, 6, 7],
		 *   [15,14,12,16]
		 * ]
		 * 
		 * 原地旋转输入矩阵，使其变为:
		 * [
		 *   [15,13, 2, 5],
		 *   [14, 3, 4, 1],
		 *   [12, 6, 8, 9],
		 *   [16, 7,10,11]
		 * ]
		 */
		matrix = new int[][]{{5, 1, 9,11}, {2, 4, 8,10}, {13, 3, 6, 7}, {15,14,12,16}};
		rotate(matrix);
		printMatrix(matrix);
	}

}
