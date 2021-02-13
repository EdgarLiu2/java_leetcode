package edgar.leetcode;


/**
 * 59. 螺旋矩阵 II
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 * 
 * @author Administrator
 *
 */
public class Solution0059_SpiralMatrixII {
	
	public static int[][] generateMatrix(int n) {
		int[][] result = new int[n][n];
		
		int left = 0;
		int right = n - 1;
		int top = 0;
		int bottom = n - 1;
		int max = n * n;
		int num = 1;
		
		while (num <= max) {
			// 从左到右填充
			for (int i = left; i <= right; i++) {
				result[top][i] = num++;
			}
			// top向下移动一
			top++;
			
			// 从上到下填充
			for (int i = top; i <= bottom; i++) {
				result[i][right] = num++;
			}
			// right向左移动一
			right--;
			
			// 从右到左填充
			for (int i = right; i >= left; i--) {
				result[bottom][i] = num++;
			}
			// bottom向上移动一
			bottom--;
			
			// 从下到上填充
			for (int i = bottom; i >= top; i--) {
				result[i][left] = num++;
			}
			// left向右移动一
			left++;
		}
		
		return result;
	}
	
	public static int[][] generateMatrix1(int n) {

		int max = n * n;
		int[][] result = new int[n][n];
		
		int i = 0;	// 第i行
		int iDelta = 1;
		int j = 0;   // 第j列
		int jDelta = 1;
		int k = 0;  // mod4 ==0 向右， mod4 ==1 向下，mod4 ==2 向左，mod4 ==3 向上
		
		for (int a = 1; a <= max; a++) {
			result[i][j] = a;
			
			switch (k % 4) {
			case 0:
				// 向右
				if (j + jDelta < n && result[i][j + jDelta] == 0) {
					// 右一位置可用
					j += jDelta;
				} else {
					// 改向下
					k++;
					iDelta = 1;
					i += iDelta;
				}
				break;
			case 1:
				// 向下
				if (i + iDelta < n && result[i + iDelta][j] == 0) {
					// 下一位置可用
					i += iDelta;
				} else {
					// 改向左
					k++;
					jDelta = -1;
					j += jDelta;
				}
				break;
			case 2:
				// 向左
				if (j + jDelta >= 0 && result[i][j + jDelta] == 0) {
					// 左一位置可用
					j += jDelta;
				} else {
					// 改向上
					k++;
					iDelta = -1;
					i += iDelta;
				}
				break;
			case 3:
				// 向上
				if (i + iDelta >= 0 && result[i + iDelta][j] == 0) {
					// 上一位置可用
					i += iDelta;
				} else {
					// 改向右
					k++;
					jDelta = 1;
					j += jDelta;
				}
				break;
			}
				
		}
		
		return result;
    }

	public static void main(String[] args) {
		int[][] result = generateMatrix(20);
		Util.printTwoDimIntArray(result);
	}

}
