package dynamicProgramming.one;

/**
 * @Desc: 买卖股票的最佳时机 II
 *
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。然而，你可以在 同一天 多次买卖该股票，但要确保你持有的股票不超过一股。
 * 返回 你能获得的 最大 利润 。
 *
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
 * 最大总利润为 4 + 3 = 7 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
 * 最大总利润为 4 。
 * @Author：zhh
 * @Date：2025/12/25 16:44
 */
public class MaxProfit {

    public static void main(String[] args) {

        MaxProfit maxProfit = new MaxProfit();
        int[] a = {7,1,5,3,6,4};
        int i = maxProfit.maxProfit(a);
        System.out.println(i);
    }

    public int maxProfit(int[] prices) {
        int max = 0;
        int value = 0;
        for (int i = 1; i < prices.length; i++) {
            value = prices[i] - prices[i-1] + (Math.max(value, 0));
            max = Math.max(value,max);
        }
        return max;

    }
}
