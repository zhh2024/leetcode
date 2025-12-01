package dynamicProgramming.one;

import java.util.Arrays;
import java.util.List;

/**
 * @Desc: 01背包问题
 * 物品不可以被重复选
 * @Author：zhh
 * @Date：2025/6/18 14:15
 */
public class Backpack {

    public static void main(String[] args) {
        Backpack backpack = new Backpack();
        int maxValue = backpack.backpack(15,
                Arrays.asList(8, 3, 4, 3),
                Arrays.asList(9, 3, 4, 3)
        );
        System.out.println(maxValue);
    }

    /**
     * 暴力回溯
     * @param capacity
     * @param values
     * @param weights
     * @return
     */
    public int backpack(int capacity , List<Integer> values , List<Integer> weights){
        backTrack(capacity,values,weights,0,0);
        return max;
    }
    int max = 0;
    public void  backTrack(int capacity,List<Integer> values , List<Integer> weights , int start, int sum ){
        max = Math.max(max,sum);

        for (int i = start; i < values.size(); i++) {
            int remain = capacity - weights.get(i);
            if(remain < 0){
                continue;
            }
            backTrack(remain , values ,weights , i + 1,sum + values.get(i));
        }
    }

    /**
     * 物品在fullBackpack02 重复了,当remain是当前weight倍数的时候,就有可能造成物品重复
     * 从后往前遍历容量就不会,因为大容量依赖与上一个剩余小容量,而小容量还没有值,所以从物品本身weight到capacity,还是只有一个物品
     * @param capacity
     * @param values
     * @param weights
     * @return
     */
    public int backpack02(int capacity , List<Integer> values, List<Integer> weights){
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < values.size(); i++) {
            Integer value = values.get(i);
            Integer weight = weights.get(i);
            for (int j = capacity; j >= weight; j--) {
                int remain = j - weight;
                dp[j] = Math.max(dp[j], value + dp[remain]);
            }
        }
        return dp[capacity];
    }
}
