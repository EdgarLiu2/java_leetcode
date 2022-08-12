package edgar.leetcode;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/">121. 买卖股票的最佳时机</a>
 * Created by liuzhao on 2022/8/12
 */
public class Solution0121_BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
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

        /*
         * 输入：prices = [7,6,4,3,1]
         * 输出：0
         * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
         */
        inputs = new int[]{7,6,4,3,1};
        assert 0 == maxProfit(inputs);

    }
}
