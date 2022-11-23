package edgar.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/triangle/">120. 三角形最小路径和</a>
 * Created by liuzhao on 2022/8/6
 */
public class Solution0120_Triangle {
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }

        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }

        // 共n行
        int n = triangle.size();
        int[][] result = new int[triangle.size()][triangle.get(n - 1).size()];
        // 第0行的，第0个元素
        result[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            // 第i行元素
            List<Integer> rowI = triangle.get(i);
            // 第i行有m列
            int m = rowI.size();

            for (int j = 0; j < m; j++) {
                result[i][j] = rowI.get(j);

                if (j == 0) {
                    // 首元素
                    result[i][j] += result[i-1][j];
                } else if (j == m - 1) {
                    // 尾元素
                    result[i][j] += result[i-1][j-1];
                } else {
                    // 中间元素
                    result[i][j] += Math.min(result[i-1][j-1], result[i-1][j]);
                }
            }
        }

        return Arrays.stream(result[n-1]).min().orElse(0);

    }

    public static void main(String[] args) {
        List<List<Integer>> inputs;

        /*
         * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
         * 输出：11
         * 解释：如下面简图所示：
         *    2
         *   3 4
         *  6 5 7
         * 4 1 8 3
         * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
         */
        inputs = new ArrayList<>();
        inputs.add(List.of(2));
        inputs.add(List.of(3, 4));
        inputs.add(List.of(6, 5, 7));
        inputs.add(List.of(4, 1, 8, 3));
        assert 11 == minimumTotal(inputs);

        /*
         * 输入：triangle = [[-10]]
         * 输出：-10
         */
        inputs = new ArrayList<>();
        inputs.add(List.of(-10));
        assert -10 == minimumTotal(inputs);
    }
}
