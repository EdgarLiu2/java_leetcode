package edgar.leetcode;

/**
 * 74. 搜索二维矩阵
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 * 
 * @author liuzhao
 *
 */
public class Solution0074_SearchA2DMatrix {

	public static boolean searchMatrix(int[][] matrix, int target) {
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		// 跟第一个数比较
		if (matrix[0][0] == target) {
			return true;
		} else if (matrix[0][0] > target) {
			return false;
		}
		
		// 跟最后一个数比较
		if (matrix[rows-1][cols-1] == target) {
			return true;
		} else if (matrix[rows-1][cols-1] < target) {
			return false;
		}
		
		// 初步判断在第几行
		int rowNum = rows - 1;
		while(rowNum > 0) {
			// 如果target比当前行第一个小，就继续向上
			if (matrix[rowNum][0] > target) {
				rowNum--;
			} else {
				// 就在当前行
				break;
			}
		}
		
		// 利用二分查找在第rowNum行寻找
		int left = 0;
		int right = cols - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (matrix[rowNum][mid] == target) {
				return true;
			} else if (matrix[rowNum][mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return false;
    }
	
	public static void main(String[] args) {
		int[][] matrix;

		/*
		 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
		 * 输出：true
		 */
		matrix = new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		assert searchMatrix(matrix, 3);
		
		/*
		 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
		 * 输出：false
		 */
		assert !searchMatrix(matrix, 13);
		
		/*
		 * 输入：matrix = [[1],[3]], target = 2
		 * 输出：false
		 */
		matrix = new int[][] {{1},{3}};
		assert !searchMatrix(matrix, 2);
	}

}
