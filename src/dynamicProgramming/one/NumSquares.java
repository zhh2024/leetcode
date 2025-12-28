package dynamicProgramming.one;

import java.util.HashMap;

/**
 * @Desc: 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 动态规划本质就是找到动态转移方程:
 * @Author：zhh
 * @Date：2025/12/19 15:11
 */
public class NumSquares {

    public int numSquares(int n) {
        int i = backTrack(n, n );
        return i;
    }

    /**
     * 递归记录重复解
     */
    HashMap<Integer, Integer> map = new HashMap<>();
    public int backTrack(int n , int target){
        if(map.containsKey(target)){
            return map.get(target) ;
        }
        if(target == 0){
            return 0;
        }
        int minCount = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if(target - i * i >= 0 ){
                int count = backTrack(n, target - i * i );
                minCount = Math.min(count + 1,minCount);
            }
        }
        map.put(target,minCount);
        return map.get(target);
    }

    /**
     * 由递归推导出来dp
     */
    public int numSquares02(int n) {
        int[] ints = new int[n +1];
        ints[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                int remain = i - j * j;
                if(remain > 0){
                    min = Math.min(min,ints[remain] + 1);
                }else if (remain == 0){
                    min = 1;
                }
            }
            ints[i] = min;
        }
        return ints[n];
    }

    /**
     * 通过平方进行剪枝
     */
    public int numSquares03(int n) {
        int[] ints = new int[n +1];
        ints[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j*j <= i; j++) {
                int remain = i - j * j;
                if(remain == 0){
                    min = 1;
                    break;
                }
                min = Math.min(min,ints[remain] + 1);
            }
            ints[i] = min;
        }
        return ints[n];
    }
}
