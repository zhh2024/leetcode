package dynamicProgramming.two;

/**
 * @Desc: 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 *
 * 思路: 动态规划
 *      1. 两笔交易可以转化成买前面的一笔交易+ 买后面的一笔交易。
 *      2. 单笔交易的最优子结构是 Math.max(prices[i] - prices[i-1] + (i-1)下标的剩余值  ,max与目前最大值的比较 )
 *         最优子结构存在是因为，是从前到后的过程，此过程是一个计算差值的过程，那么记录已经遍历过的下标剩余差值，然后当前（下标-前一个下标） + 前一个下标的剩余差值，就是选取当前股票的最优解，但是全局最优解不一定会选当前股票，所以需要记录全局最优解
 *      3. 所以需要两个dp数组，记录从前到后的当前下标的最优解和从后到前的最优解。
 *      4. 然后就可以进行加法求得最大值了。
 * @Author：zhh
 * @Date：2025/12/25 16:55
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};
        MaxProfit maxProfit = new MaxProfit();
        int i = maxProfit.maxProfit02(prices);
        System.out.println(i);
    }

    /**
     *  时间复杂度是O(n^2)
     *  这都能超出时间复杂度，看来有O(nlogn)/O(n)的解决方案
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max,singleMaxProfit(prices,1,i) + singleMaxProfit(prices,i+2,prices.length -1 ));
        }
        return max;

    }

    /**
     * dp 计算单笔股票的最大利润
     */
    public int singleMaxProfit(int[] prices,int start,int end){
        if(start > end){
            return 0;
        }
        int max = 0;
        int value = 0;
        for (int i = start; i <= end; i++) {
            value = prices[i] - prices[i-1] + (Math.max(value, 0));
            max = Math.max(value,max);
        }
        return max;
    }


    /**
     * 利用 dp 计算单笔股票的最大利润 的算法，先计算从前到后当前下标的最大单笔利润， 然后再计算从后到前当前下标的最大利润。
     * 这样两笔交易就可以自然而然的拼接在一起，就是两笔交易的最大值
     */
    public int maxProfit02(int[] prices) {
        int length = prices.length;
        int[] maxDp = new int[length];
        int currentValue = 0;
        for (int i = 1; i < length; i++) {
            currentValue = prices[i] - prices[i-1] + (Math.max(currentValue, 0));
            maxDp[i] = Math.max(currentValue,maxDp[i-1]);
        }
        currentValue = 0;
        int[] minDp = new int[length];
        for (int i = length - 2; i >= 0; i--) {
            currentValue = prices[i+1] - prices[i] + (Math.max(currentValue, 0));
            minDp[i] = Math.max(currentValue,minDp[i+1]);
        }
        int max = 0;
        for (int i = 1; i < length - 1; i++) {
            max = Math.max(maxDp[i] + minDp[i+1],max);
        }
        return Math.max(max,maxDp[length-1]);
    }



    public int maxProfit03(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

}
