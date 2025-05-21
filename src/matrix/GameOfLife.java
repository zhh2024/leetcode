package matrix;

/**
 * @Desc: 生命游戏
 * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 1. 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 2. 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 3. 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 4. 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是 同时 发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 * 给定当前 board 的状态，更新 board 到下一个状态。
 * 注意 你不需要返回任何东西。
 * <p>
 * 示例 1：
 * 输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * 输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * <p>
 * 示例 2：
 * 输入：board = [[1,1],[1,0]]
 * 输出：[[1,1],[1,1]]
 * @Author：zhh
 * @Date：2025/5/20 14:43
 */
public class GameOfLife {
    public static void main(String[] args) {
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife02(board);
    }

    /**
     * 变化条件:  活细胞周围的活细胞个数> 3 or 个数<2 ,1变成0。
     *          细胞周围的活细胞个数 ==3 ,0变成1
     *
     * 思路: 1. 获取辐射周围一格内的数据
     *      2. 根据变化条件更改变化
     *
     * 难点:
     * 1. 如何获取辐射周围一格内的数据?
     *   i+1,j;
     *   i-1,j;
     *   i,j+1;
     *   i,j-1;
     *   i+1,j-1;
     *   i+1,j+1;
     *   i-1,j-1;
     *   i-1,j+1;
     *   总共有八种场景。用数组{0,1,-1}记录辐射范围,然后3*3遍历排除本身就是9-1=8种情况,就无需挨个定义,还需要判断边界问题
     * 2. 变化后如何不对后续产生影响?
     *   将原数组复制一份,读复制后的数组,写原数组,就可以做到
     * @param board
     */
    public static void gameOfLife(int[][] board) {
        int length = board.length;
        int wide = board[0].length;
        int[][] copy = new int[length][wide];
        //1. 复制
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < wide; j++) {
                copy[i][j] = board[i][j];
            }
        }
        //用数组记录辐射范围,然后3*3遍历排除本身就是9-1=8种情况,就无需挨个定义
        int[] range = {0,1,-1};
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < wide; j++) {
                //记录每一个细胞的辐射范围的活细胞个数
                int number = 0;
                //每一个细胞的辐射范围是3*3
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        //排除掉本身
                        if (range[k] != 0 || range[l] != 0) {
                            if(i + range[k] >= 0 && i + range[k] < length  && j + range[l] >= 0 && j + range[l] < wide ){
                                if (copy[i + range[k] ][j + range[l] ] == 1) {
                                    //记录辐射范围内活细胞个数
                                    number++;
                                }
                            }
                        }
                    }
                }
                // 变化条件:  活细胞周围的活细胞个数> 3 or 个数<2 ,1变成0。
                //          死细胞周围的活细胞个数 ==3 ,0变成1
                if (copy[i][j] == 1 && (number > 3 || number < 2)) {
                    board[i][j] = 0;
                }
                if (copy[i][j] == 0 && number == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }


    /**
     * 在1的基础上优化,不使用复制矩阵,如果发生变化,记做标记,比如用2表示1变成了0,用3表示0变成了1,然后复原即可
     * @param board
     */
    public static void gameOfLife02(int[][] board) {
        int length = board.length;
        int wide = board[0].length;
        //用数组记录辐射范围,然后3*3遍历排除本身就是9-1=8种情况,就无需挨个定义
        int[] range = {0,1,-1};
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < wide; j++) {
                //记录每一个细胞的辐射范围的活细胞个数
                int number = 0;
                //每一个细胞的辐射范围是3*3
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        //排除掉本身
                        if (range[k] != 0 || range[l] != 0) {
                            int row = i + range[k];
                            int col = j + range[l];
                            if(row >= 0 && row < length  && col >= 0 && col < wide ){
                                if (board[row][col] == 1 || board[row][col] == 2) {
                                    //记录辐射范围内活细胞个数
                                    number++;
                                }
                            }
                        }
                    }
                }
                // 变化条件:  活细胞周围的活细胞个数> 3 or 个数<2 ,1变成0。
                //          死细胞周围的活细胞个数 ==3 ,0变成1
                if (board[i][j] == 1 && (number > 3 || number < 2)) {
                    board[i][j] = 2;
                }
                if (board[i][j] == 0 && number == 3) {
                    board[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < wide; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
