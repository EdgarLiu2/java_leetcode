package edgar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/pascals-triangle/">118. 杨辉三角</a>
 * Created by liuzhao on 2022/8/10
 */
public class Solution0118_PascalsTriangle {

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {
            return result;
        }

        ArrayList<Integer> previousRow = new ArrayList<>(List.of(1));
        result.add(previousRow);

        if (numRows == 1) {
            return result;
        }

        for (int i = 2; i <= numRows; i++) {

            ArrayList<Integer> currentRow = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    // 第一个元素是1
                    currentRow.add(1);
                } else if (j == i - 1) {
                    // 最后一个元素也是1
                    currentRow.add(1);
                } else {
                    // 中间元素
                    int item = previousRow.get(j - 1) + previousRow.get(j);
                    currentRow.add(item);
                }
            }

            result.add(currentRow);
            previousRow = currentRow;
        }

        return result;
    }

    public static void main(String[] args) {

        List<List<Integer>> result;

        /*
         * 输入: numRows = 1
         * 输出: [[1]]
         */
        result = generate(1);
        System.out.println(result);

        /*
         * 输入: numRows = 5
         * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
         */
        result = generate(5);
        System.out.println(result);
    }
}
