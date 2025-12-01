package dynamicProgramming.one;

import java.util.Arrays;
import java.util.List;

/**
 * @Desc: 完全背包问题
 * 物品可以被重复选
 * @Author：zhh
 * @Date：2025/6/18 10:20
 */
public class FullBackpack {
    public static void main(String[] args) {
        FullBackpack fullBackpack = new FullBackpack();
        int maxValue = fullBackpack.fullBackpack02(9, Arrays.asList(8,5,11), Arrays.asList(9,1,2));
        System.out.println(maxValue);
    }

    /**
     * 思路: 遍历容量,在该容量下,需要遍历判断每个商品和其剩余容量的价值和哪个最大,那么最大值就是该容量的值。
     * @param capacity
     * @param values
     * @param weights
     * @return
     */
    public int fullBackpack(int capacity , List<Integer> values , List<Integer> weights){
        int[] dp = new int[capacity + 1 ];
        dp[0] = 0;
        for (int i = 1; i <= capacity; i++) {
            for (int j = 0; j < weights.size(); j++) {
                int remain = i - weights.get(j);
                if (remain >= 0) {
                    dp[i] = Math.max(dp[i] ,values.get(j) + dp[remain]);
                }
            }
        }
        return dp[capacity];
    }

    /**
     * 优化fullBackpack  从物品开始遍历,可以减少循环次数，因为小于物品容量的场景不需要进入循环记录
     * 思路:
     * @param capacity
     * @param values
     * @param weights
     * @return
     */
    public int fullBackpack02(int capacity , List<Integer> values , List<Integer> weights){
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < values.size(); i++) {
            Integer value = values.get(i);
            Integer weight = weights.get(i);
            for (int j = weight; j <= capacity; j++) {
                int remain = j - weight;
                dp[j] = Math.max(dp[j], value + dp[remain]);
            }
        }
        return dp[capacity];
    }



}
