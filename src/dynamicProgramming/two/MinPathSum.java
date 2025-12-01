package dynamicProgramming.two;

/**
 * @Desc: 最小路径和
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例 1：
 * 输入：grid = [
 *               [1,3,1],
 *               [1,5,1],
 *               [4,2,1]
 *             ]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * @Author：zhh
 * @Date：2025/6/9 14:36
 */
public class MinPathSum {
    public static void main(String[] args) {
        MinPathSum minPathSum = new MinPathSum();
        int[][] grid = {
                {1,2},
                {1,1}

        };
        int i = minPathSum.minPathSum(grid);
        System.out.println(i);
    }


    /**
     * 思路: 记录每个下标的最优解,当前下标判断可到达的各个下标,然后比较哪个是最优解,那么就选择哪个下标，从而保证当前下标的最优解。
     * 动态转移方程为: dp[i][j] = Math.min(dp[i][j-1] + grid[i][j],dp[i-1][j] + grid[i][j]);
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid){
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = grid[i][j];
                if(j - 1 >= 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }
                if (i - 1 >= 0 ){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }
                if (i - 1 >= 0 && j - 1 >= 0){
                    dp[i][j] = Math.min(dp[i][j-1] + grid[i][j],dp[i-1][j] + grid[i][j]);
                }
            }
        }
        return dp[row-1][col-1];
    }

    /**
     * 优化边界问题,减少循环里面的逻辑判断
     * @param grid
     * @return
     */
    public int minPathSum02(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        // 初始化起点
        dp[0][0] = grid[0][0];

        // 初始化第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        // 初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        // 填充剩余部分
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }
}
