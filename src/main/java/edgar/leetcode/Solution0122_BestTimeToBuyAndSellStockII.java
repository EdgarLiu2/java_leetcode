package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/">122. 买卖股票的最佳时机 II</a>
 * Created by liuzhao on 2022/8/12
 */
public class Solution0122_BestTimeToBuyAndSellStockII {
    public static int maxProfit(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0];
        int totalProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // 当i比i-1小时，之前的获利可以套现
            if (prices[i] < prices[i-1]) {
                totalProfit += prices[i-1] - minPrice;
                minPrice = prices[i];
            }
        }

        // 处理最后2个数相同的特殊情况
        if (prices[prices.length - 1] >= prices[prices.length - 2]) {
            totalProfit += prices[prices.length - 1] - minPrice;
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        int[] inputs;

        /*
         * 输入：prices = [7,1,5,3,6,4]
         * 输出：7
         * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
         *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
         *      总利润为 4 + 3 = 7 。
         */
        inputs = new int[]{7,1,5,3,6,4};
        assert 7 == maxProfit(inputs);

        /*
         * 输入：prices = [1,2,3,4,5]
         * 输出：4
         * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
         *      总利润为 4 。
         */
        inputs = new int[]{1,2,3,4,5};
        assert 4 == maxProfit(inputs);

        /*
         * 输入：prices = [7,6,4,3,1]
         * 输出：0
         * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
         */
        inputs = new int[]{7,6,4,3,1};
        assert 0 == maxProfit(inputs);

        /*
         * 输入：prices = [1,9,6,9,1,7,1,1,5,9,9,9]
         * 输出：25
         */
        inputs = new int[]{1,9,6,9,1,7,1,1,5,9,9,9};
        assert 25 == maxProfit(inputs);
    }
}
