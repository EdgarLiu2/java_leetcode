package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/">121. 买卖股票的最佳时机</a>
 * Created by Edgar.Liu on 2022/8/12
 */
public class Solution0121_BestTimeToBuyAndSellStock {
    /**
     * 贪婪算法
     * @param prices 一只股票每天的价格
     * @return 最大收益
     */
    static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0 ;

        for (int price : prices) {
            if (price < minPrice) {
                // 找到比当前最低价格更低
                minPrice = price;
                continue;
            }

            // 基于当前最低价计算的利润
            int profit = price - minPrice;
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

    /**
     * 动态规划算法
     * @param prices 一只股票每天的价格
     * @return 最大收益
     */
    static int maxProfit2(int[] prices) {
        // 先处理异常情况
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        // 初始化
        // 第一天不持有股票的利润
        dp[0][0] = 0;
        // 第一天持有股票的利润
        dp[0][1] = - prices[0];

        for (int i = 1; i < prices.length; i++) {
            /*
             * 先计算不持有股票的最大收益
             * 1. i-1不持有，i也不持有（即什么都不做）
             * 2. i-i持有，i不持有（即卖了）
             */
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);

            /*
             * 再计算持有股票的最大收益
             * 1. i-1不持有，i持有（即买入）
             * 2. i-i持有，i也持有（即什么都不做）
             */
            dp[i][1] = Math.max(- prices[i], dp[i-1][1]);
        }

        return dp[prices.length-1][0];
    }

    public static void main(String[] args) {
        int[] inputs;

        /*
         * 输入：[7,1,5,3,6,4]
         * 输出：5
         * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
         *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
         */
        inputs = new int[]{7,1,5,3,6,4};
        assert 5 == maxProfit(inputs);
        assert 5 == maxProfit2(inputs);

        /*
         * 输入：prices = [7,6,4,3,1]
         * 输出：0
         * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
         */
        inputs = new int[]{7,6,4,3,1};
        assert 0 == maxProfit(inputs);
        assert 0 == maxProfit2(inputs);

    }
}
