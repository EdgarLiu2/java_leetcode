package edgar.leetcode;


/**
 * 64. 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 
 * @author Administrator
 *
 */
public class Solution0064_MinimumPathSum {
	
	public static int minPathSum(int[][] grid) {

		int m = grid.length;
		int n = grid[0].length;
		
		// 初始化M*N的矩阵
		int[][] result = new int[m][n];
		
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (i == m - 1 && j== n - 1) {
					// 左下角起点
					result[i][j] = grid[i][j];
				} else if (i == m - 1) {
					// 最下方一行
					result[i][j] = result[i][j + 1] + grid[i][j];
				} else if (j == n -1) {
					// 最右边一列
					result[i][j] = result[i + 1][j] + grid[i][j];
				} else {
					// 取右侧和下方较小的
					result[i][j] = Math.min(result[i][j + 1], result[i + 1][j]) + grid[i][j];
				}
			}
		}
		
//		Util.printTwoDimIntArray(result);
				
		return result[0][0];
    }

	public static void main(String[] args) {
		int[][] grid;

		/*
		 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
		 * 输出：7
		 */
		grid =  new int[][]{
			{1,3,1},
			{1,5,1},
			{4,2,1}
		};
		assert minPathSum(grid) == 7;
		
		/*
		 * 输入：grid = [[1,2,3],[4,5,6]]
		 * 输出：12
		 */
		grid =  new int[][]{
			{1,2,3},
			{4,5,6}
		};
		assert minPathSum(grid) == 12;
	}

}
