package dynamicProgramming.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc: 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 * @Author：zhh
 * @Date：2025/6/9 10:12
 */
public class MinimumTotal {

    public static void main(String[] args) {
        MinimumTotal minimumTotal = new MinimumTotal();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(-1));
        triangle.add(Arrays.asList(-2,-3));

        int i = minimumTotal.minimumTotal(triangle);
        System.out.println(i);
    }

    /**
     * 思路: 初始化最下层的元素，然后从下往上选择每一层元素的最优解,这样上一层只需要关注下一层的最优解即可,就无需重新寻找了。
     * 动态转移方程为: dp[i][j] = Math.min(ints.get(j) + dp[i+1][j],ints.get(j) + dp[i+1][j+1]);
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[][] dp = new int[row][triangle.get(row - 1).size()];
        for (int i = row - 1; i >= 0 ; i--) {
            List<Integer> ints = triangle.get(i);
            for (int j = 0; j < ints.size() ; j++) {
                if(i + 1 < row){
                    dp[i][j] = Math.min(ints.get(j) + dp[i+1][j],ints.get(j) + dp[i+1][j+1]);
                }else {
                    //最后一层
                    dp[i][j] = ints.get(j);
                }
            }
        }
        return dp[0][0];
    }
}
