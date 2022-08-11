package edgar.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/pascals-triangle-ii/">119. 杨辉三角 II</a>
 * Created by liuzhao on 2022/8/11
 */
public class Solution0119_PascalsTriangleII {
    public static List<Integer> getRow(int rowIndex) {

        if (rowIndex == 0) {
            return Arrays.asList(1);
        }

        ArrayList<Integer> currentRow = new ArrayList<>(Arrays.asList(1, 1));
        if (rowIndex == 1) {
            return currentRow;
        }

        for (int i = 2; i <= rowIndex; i++) {
            ArrayList<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 第一个元素是1
                    row.add(1);
                } else if (j == i) {
                    // 最后一个元素也是1
                    row.add(1);
                } else {
                    // 中间元素
                    int item = currentRow.get(j - 1) + currentRow.get(j);
                    row.add(item);
                }
            }
            currentRow = row;
        }


        return currentRow;
    }

    public static void main(String[] args) {
        List<Integer> result;

        /*
         * 输入: rowIndex = 0
         * 输出: [1]
         */
        result = getRow(0);
        assert result.equals(Arrays.asList(1));

        /*
         * 输入: rowIndex = 1
         * 输出: [1,1]
         */
        result = getRow(1);
        assert result.equals(Arrays.asList(1, 1));

        /*
         * 输入: rowIndex = 3
         * 输出: [1,3,3,1]
         */
        result = getRow(3);
        assert result.equals(Arrays.asList(1, 3, 3, 1));
    }
}
