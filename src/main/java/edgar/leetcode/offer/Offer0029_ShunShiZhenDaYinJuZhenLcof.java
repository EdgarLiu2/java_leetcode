package edgar.leetcode.offer;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 *
 * Created by liuzhao on 2022/7/27
 */
public class Offer0029_ShunShiZhenDaYinJuZhenLcof {
    public static int[] spiralOrder(int[][] matrix) {

        if (matrix.length == 0) {
            return new int[0];
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int size = matrix.length * matrix[0].length;
        int[] result = new int[size];
        int idx = 0;

        while (idx < size) {
            // 左到右
            for (int i = left; i <= right && idx < size; i++) {
                result[idx++] = matrix[top][i];
            }
            top++;

            // 从上到下
            for (int j = top; j <= bottom && idx < size; j++) {
                result[idx++] = matrix[j][right];
            }
            right--;

            // 从右到左
            for (int i = right; i >= left && idx < size; i--) {
                result[idx++] = matrix[bottom][i];
            }
            bottom--;

            // 从下到上
            for (int j = bottom; j >= top && idx < size; j--) {
                result[idx++] = matrix[j][left];
            }
            left++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix;
        int[] result;

        /**
         * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
         * 输出：[1,2,3,6,9,8,7,4,5]
         */
        matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        result = spiralOrder(matrix);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();


        /**
         * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
         * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
         */
        matrix = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        result = spiralOrder(matrix);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();

        /**
         * 输入：matrix = [[],[],[]]
         * 输出：[]
         */
        matrix = new int[][] {};
        result = spiralOrder(matrix);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
