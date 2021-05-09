package edgar.leetcode;

/**
 * 63. 不同路径 II
 * https://leetcode-cn.com/problems/unique-paths-ii/
 * 
 * @author Administrator
 *
 */
public class Solution0063_UniquePathsII {
	
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		
		// 初始化M*N的矩阵
		int[][] array = new int[m][n];
		
		if (obstacleGrid[0][0] != 1) {
			array[0][0] = 1;
		}
		
		// 第0行都为1，如果没有障碍
		for(int j = 1; j < array[0].length; j++) {
			if (obstacleGrid[0][j] == 1) {
				// 此处有障碍
				array[0][j] = 0;
			} else {
				array[0][j] = array[0][j-1];
			}
		}
		
		// 第0列都为1，如果没有障碍
		for(int i = 1; i < array.length; i++) {
			array[i][0] = 1;
			if (obstacleGrid[i][0] == 1) {
				// 此处有障碍
				array[i][0] = 0;
			} else {
				array[i][0] = array[i-1][0];
			}
		}
		
		for(int i = 1; i < array.length; i++) {
			// 从第1行开始
			for(int j = 1; j < array[0].length; j++) {
				// 从第1列开始
				if (obstacleGrid[i][j] == 1) {
					array[i][j] = 0;
				} else {
					array[i][j] = array[i-1][j] + array[i][j-1];
				}
			}
		}
		
//		Util.printTwoDimIntArray(array);

		return array[m-1][n-1];
    }

	public static void main(String[] args) {
		int[][] obstacleGrid;
		
		/*
		 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
		 * 输出：2
		 */
		obstacleGrid = new int[][]{
			{0,0,0},
			{0,1,0},
			{0,0,0}
		};
		assert uniquePathsWithObstacles(obstacleGrid) == 2;
		
		/*
		 * 输入：obstacleGrid = [[0,1],[0,0]]
		 * 输出：1
		 */
		obstacleGrid = new int[][]{
			{0,1},
			{0,0},
		};
		assert uniquePathsWithObstacles(obstacleGrid) == 1;
		
		/*
		 * 输入：obstacleGrid = [[1]]
		 * 输出：0
		 */
		obstacleGrid = new int[][]{
			{1},
		};
		assert uniquePathsWithObstacles(obstacleGrid) == 0;
	}

}
