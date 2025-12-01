package dynamicProgramming.two;

/**
 * @Desc: 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * 示例 1：
 * 输入：matrix = [
 *                 ["1","0","1","0","0"],
 *                 ["1","0","1","1","1"],
 *                 ["1","1","1","1","1"],
 *                 ["1","0","0","1","0"]
 *                                       ]
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：matrix = [
 *                 ["0","1"],
 *                 ["1","0"]
 *                           ]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 * @Author：zhh
 * @Date：2025/6/19 14:23
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0'},
                {'1','0','1','1'},
                {'1','1','1','1'},
                {'1','1','1','1'},
                };

        MaximalSquare maximalSquare = new MaximalSquare();
        int i = maximalSquare.maximalSquare04(matrix);
        System.out.println(i);
    }

    /**
     * 思路: 判断 1x1 ,2x2 ,3x3,4x4 ... 的所有场景是否能构成全1的正方形
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int number = Math.min(row, col);
        int max = 0;
        for (int i = 1; i <= number; i++) {
            for (int j = 0; j <= row - i; j++) {
                for (int k = 0; k <= col - i; k++) {
                    //以当前为下标获取范围
                    if (check(matrix, j, k, i)){
                        max = i;
                    }
                }
            }
        }
        return max * max;
    }
    
    public boolean check (char[][] matrix , int i, int j, int number){
        for (int k = 0; k < number; k++) {
            for (int l = 0; l < number; l++) {
                if (matrix[i+k][j+l] != '1') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 主要时间复杂度在于判断是否是全1正方形, 4x4包含 3x3 ,3x3 包含2x2,具备重叠子问题,可以用动态规划来做
     * 如何记录 2x2,3x3范围是否是正方形,使用了三维数组记录
     * @param matrix
     * @return
     */
    public int maximalSquare02(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int number = Math.min(row, col);
        boolean[][][] dp = new boolean[row][col][number+1];
        int max = 0;
        for (int j = 0; j < row ; j++) {
            for (int k = 0; k < col ; k++) {
                if (matrix[j][k] == '1') {
                    dp[j][k][1] = true;
                    max = 1;
                }
            }
        }
        for (int i = 2; i <= number; i++) {
            for (int j = 0; j <= row - i; j++) {
                for (int k = 0; k <= col - i; k++) {
                    dp[j][k][i] = dp[j][k][i-1] && check02(matrix,j,k,i);
                    if( dp[j][k][i]){
                        max = i;
                    }
                }
            }
        }
        return  max * max;
    }

    public boolean check02 (char[][] matrix , int row, int col, int number){
        for (int l = 0; l < number; l++) {
            if (matrix[row + number- 1][col+l] != '1') {
                return false;
            }
        }
        for (int l = 0; l < number; l++) {
            if (matrix[row + l][col + number -1] != '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * 优化maximalSquare02
     * 1. dp由三维数组优化成二维数据,直接更大的范围覆盖更小的范围即可,无需记录哪个范围是正方形,因为max已经记录了
     * 2. 判断是否是更大正方形，判断内部四个小正方形是否是正方形即可。
     * 最终动态转移方程为: dp[j][k] = dp[j][k] && dp[j][k+1] && dp[j+1][k] && dp[j+1][k+1]
     * @param matrix
     * @return
     */
    public int maximalSquare03(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int number = Math.min(row, col);
        boolean[][] dp = new boolean[row][col];
        int max = 0;
        for (int j = 0; j < row ; j++) {
            for (int k = 0; k < col ; k++) {
                if (matrix[j][k] == '1') {
                    dp[j][k] = true;
                    max = 1;
                }
            }
        }
        for (int i = 2; i <= number; i++) {
            for (int j = 0; j <= row - i; j++) {
                for (int k = 0; k <= col - i; k++) {
                    dp[j][k] = dp[j][k] && dp[j][k+1] && dp[j+1][k] && dp[j+1][k+1];
                    if( dp[j][k]){
                        max = i;
                    }
                }
            }
        }
        return  max * max;
    }

    /**
     * 核心思路
     * 1. 每个当前下标表示正方形最大边长,从当前到左上的范围
     * 2. 判断当前下标能否符合正方形要求,只需要判断里面三个的子正方形的边长,如果有边长,代表是存在小正方形。获取三个小正方形的最短边即可
     *
     * 优化maximalSquare03
     * 1. 往里面缩,而不是往外扩,可以减少一层循环
     * 2. 往里面缩的过程中,当前下标,只需要获取到上一层的三个小正方形的最短边长
     * 最终动态转移方程为: dp[i][j] = Math.min(Math.min(dp[i-1][j-1] +1,dp[i][j-1]),dp[i-1][j]) + 1;
     * @param matrix
     * @return
     */
    public int maximalSquare04(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int max = 0;
        //初始化行
        for (int i = 0; i < col; i++) {
            if(matrix[0][i] == '1'){
                dp[0][i] = 1;
                max = 1;
            }
        }
        //初始化列
        for (int i = 0; i < row; i++) {
            if(matrix[i][0] == '1'){
                dp[i][0] = 1;
                max = 1;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i][j-1]),dp[i-1][j]) + 1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max * max;
    }


}
