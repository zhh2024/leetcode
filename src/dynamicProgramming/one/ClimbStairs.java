package dynamicProgramming.one;

import java.util.HashMap;

/**
 * @Desc: 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 *
 * 思路: 定位到 dp[i] = dp[i-1] + dp[i-2]   为什么i-1 和 i-2 不会有重复方案呢，因为最后一步的选择不一样，所以方案不会有任何重复。那如果前面的选择一样的话，阶梯数也一样了，不符合所有最终方案是不会重复的。
 *      所以可以定位函数f(n) = f(n-1) + f(n-2)
 * @Author：zhh
 * @Date：2025/3/18 14:13
 */
public class ClimbStairs {
    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        int i = climbStairs.climbStairs(5);
        System.out.println(i);
    }

    HashMap<Integer, Integer> map = new HashMap<>();

    /**
     * 实现思路:
     * f(1) = 1
     * f(2) = 2
     * f(3) = f(1) + f(2)  = 3
     * f(4) = f(3) + f(2)  = 5
     * f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(map.containsKey(n)){
            return map.get(n);
        }
        if(n == 1){
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        int i = climbStairs(n - 1) + climbStairs(n - 2);
        map.put(n,i);
        return i ;
    }

    /**
     * 动态规划,因为只依赖于前两个下标解，所以不需要一维数组记录，每一阶所需要的不同方法次数。
     * @param n
     * @return
     */
    public int climbStairs02(int n) {
        if(n <= 2 ){
            return n;
        }
        int n1 = 1; int n2 = 2;
        for (int i = 3; i < n; i++) {
            int tmp = n2;
            n2 = n1 + n2;
            n1 = tmp;
        }
        return n1 + n2;
    }



}
