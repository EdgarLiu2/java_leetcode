package edgar.leetcode;


/**
 * 62. 不同路径
 * https://leetcode-cn.com/problems/unique-paths/
 * 
 * @author Administrator
 *
 */
public class Solution0062_UniquePaths {
	
	public static int uniquePaths(int m, int n) {
		// 初始化M*N的矩阵
		int[][] array = new int[m][n];
		
		// 第0行都为1
		for(int j = 0; j < array[0].length; j++) {
			array[0][j] = 1;
		}
		
		// 第0列都为1
		for(int i = 0; i < array.length; i++) {
			array[i][0] = 1;
		}
		
		for(int i = 1; i < array.length; i++) {
			// 从第1行开始
			for(int j = 1; j < array[0].length; j++) {
				// 从第1列开始
				array[i][j] = array[i-1][j] + array[i][j-1];
			}
		}
		
		return array[m-1][n-1];
    }

	public static void main(String[] args) {
		/*
		 * 输入：m = 3, n = 7
		 * 输出：28
		 */
		assert uniquePaths(3, 7) == 28;
		
		/*
		 * 输入：m = 3, n = 2
		 * 输出：3
		 */
		assert uniquePaths(3, 2) == 3;
		
		/*
		 * 输入：m = 7, n = 3
		 * 输出：28
		 */
		assert uniquePaths(3, 2) == 3;
		
		/*
		 * 输入：m = 3, n = 3
		 * 输出：6
		 */
		assert uniquePaths(3, 3) == 6;
	}

}
