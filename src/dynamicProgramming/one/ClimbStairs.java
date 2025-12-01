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
}
