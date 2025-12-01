package dynamicProgramming.one;

import java.util.Arrays;

/**
 * @Desc: 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * 输入：coins = [1, 2, 4], amount = 11
 * 输出：3
 * 解释：11 = 4 + 4 + 2 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * @Author：zhh
 * @Date：2025/6/5 9:21
 */
public class CoinChange {
    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1,2,5};
        int i = coinChange.coinChange04(coins, 3);
        System.out.println(i);
    }

    /**
     * 暴力回溯,寻找所有可能得组合,并计算最少个数
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        backTrack(coins,amount,0);
        return min == Integer.MAX_VALUE ? -1: min;
    }
    int min = Integer.MAX_VALUE;
    public void backTrack(int[] coins, int amount ,int count ){
        if(amount < 0){
            return;
        }
        if(0 == amount){
            min = Math.min(min,count);
            return;
        }
        if(count >= min){
            return;
        }
        for (int coin : coins) {
            backTrack(coins, amount - coin, count + 1);
        }
    }



    /**
     * 优化回溯: 自顶向下备忘录
     * 比如  {1,2,5} ,11 最终目的是找11元的最少硬币数
     * 递归路径 1-1-2-2 与 2-2-2 的结果是（11-1-1-2-2） == （11 - 2 -2 -2） = 5
     * 两个递归路径下一步寻找5元对应的最少硬币数,这两个过程是一致的,当一个递归路径寻找完5元硬币的最少数，就可以记录下来，下一个递归路径过来就不需要再递归了。
     * @param coins
     * @param amount
     * @return
     */
    int[] map = null;
    public int coinChange02(int[] coins, int amount) {
        map = new int[amount + 1];
        backTrack02(coins,amount);
        return map[amount] == Integer.MAX_VALUE ? -1: map[amount];
    }
    public int backTrack02(int[] coins, int amount){
        if(amount < 0){
            return - 1 ;
        }
        if(0 == amount){
            return  0;
        }
        if(map[amount] != 0 ){
            return map[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin: coins){
            //回溯回来amount - coin 需要的最少硬币个数
            int minCount = backTrack02(coins, amount - coin);
            if(minCount != -1 && minCount != Integer.MAX_VALUE){
                // +1 包含 coin本身,minCount是amount - coin的最少硬币数
                min = Math.min(minCount + 1,min);
            }
        }
        map[amount] = min;
        return map[amount];
    }

    /**
     * 优化02,自顶向下,需要一直递归到最下面然后记录,才回到上层 ,直接自底向上不就可以了吗
     * 动态转移方程: i- coin
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange03(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int remain = i - coin;
                if (remain < 0) {
                    continue;
                }
                if(dp[remain] != -1){
                    min = Math.min(dp[remain] + 1,min);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1: min;
        }
        return dp[amount];
    }

    /**
     * 整合coinChange03 ,总结成动态转移方程: dp[i]  = Math.min(dp[i - coin] + 1,dp[i])
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange04(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        //因为要取最小值,所以初始化为最大值
        Arrays.fill(dp,amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i]  = Math.min(dp[i - coin] + 1,dp[i]);
                }
            }
        }
        return dp[amount] == amount +1 ? -1: dp[amount];
    }

}
