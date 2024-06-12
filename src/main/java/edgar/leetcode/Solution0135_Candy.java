package edgar.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/candy/description/">135. 分发糖果</a>
 *
 * @author Edgar.Liu
 * @since 2024/6/11 - 19:46
 */
public class Solution0135_Candy {

    static int candy(int[] ratings) {

        // 左规则：从左侧计算每个位置的最小糖果数
        int[] leftCandyNum = new int[ratings.length];
        leftCandyNum[0] = 1;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                // 如果i位置比i-1位置rating高，i位置拿的糖果数比i-1位置多1
                leftCandyNum[i] = leftCandyNum[i-1] + 1;
            } else {
                // 否则i位置只拿1个
                leftCandyNum[i] = 1;
            }
        }

        // 右规则：从右侧计算每个位置的最小糖果数
        int[] rightCandyNum = new int[ratings.length];
        rightCandyNum[ratings.length - 1] = 1;

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // 如果i位置比i+1位置rating高，i位置拿的糖果数比i+1位置多1
                rightCandyNum[i] = rightCandyNum[i+1] + 1;
            } else {
                // 否则i位置只拿1个
                rightCandyNum[i] = 1;
            }
        }

        // 最后结果
        int[] candyNum = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            candyNum[i] = Math.max(leftCandyNum[i], rightCandyNum[i]);
        }

        return Arrays.stream(candyNum).sum();
    }

    public static void main(String[] args) {
        int[] input;

        /*
         * 输入：ratings = [1,0,2]
         * 输出：5
         * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
         */
        input = new int[]{1, 0, 2};
        assert 5 == candy(input);

        /*
         * 输入：ratings = [1,2,1]
         * 输出：4
         * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
         *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
         */
        input = new int[]{1, 2, 1};
        assert 4 == candy(input);
    }
}
