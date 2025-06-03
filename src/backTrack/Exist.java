package backTrack;

/**
 * @Desc: 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 * @Author：zhh
 * @Date：2025/5/29 13:48
 */
public class Exist {

    public static void main(String[] args) {
       /* char[][]board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };*/
        char[][]board = {
                {'a'},
                {'b'}

        };
        Exist exist = new Exist();
        boolean abcced = exist.exist(board, "ba");
        System.out.println(abcced);
    }

    /**
     * 1. 先找到起始字符
     * 2. 然后上下左右找附近字符
     * 3. 继续上下左右找附近字符
     * 4. 循环次数不确定,取决于字符串长度,所以需要递归
     * 5. 使用二维数组存储出现过的字符下标,记为true,附近字符不可出现在二维数组里面
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == word.charAt(0)){
                    visited[i][j] = true;
                    backTrack(board, word, visited, i, j, 1);
                    visited[i][j] = false;
                    if (flag) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    boolean flag = false;

    public void backTrack(char[][] board, String word ,boolean[][] visited , int row, int col ,int len){
        if (len == word.length()) {
            flag = true;
            return;
        }
        char target = word.charAt(len);

        if(col - 1 >= 0  && !visited[row][col-1]  &&  board[row][col - 1] ==  target){
            visited[row][col-1] = true;
            backTrack(board,word,visited, row,col - 1,len +1);
            visited[row][col-1] = false;

        }
        if(col + 1 < board[0].length && !visited[row][col+1] &&  board[row][col + 1] == target ){
            visited[row][col+1] = true;
            backTrack(board,word,visited,row,col + 1,len +1);
            visited[row][col+1] = false;
        }
        if(row - 1 >= 0 && ! visited[row - 1][col] &&  board[row - 1][col] ==target ){
            visited[row - 1][col] = true;
            backTrack(board,word,visited,row -1 ,col,len +1);
            visited[row - 1][col] = false;
        }
        if(row + 1 < board.length && ! visited[row + 1][col] &&  board[row + 1][col] == target){
            visited[row + 1][col] = true;
            backTrack(board,word,visited,row + 1,col,len +1 );
            visited[row + 1][col] = false;
        }
    }

    public boolean exist02(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == word.charAt(0)){
                    boolean flag = backTrack02(board, word, visited, i, j, 0);
                    if (flag) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    /**
     * 优化:
     * 1. 上下左右代码冗余
     * 2. 如果返回true提前退出递归
     * @param board
     * @param word
     * @param visited
     * @param row
     * @param col
     * @param len
     */
    public boolean backTrack02(char[][] board, String word, boolean[][] visited, int row, int col ,int len){
        if(board[row][col] !=  word.charAt(len)){
            return false;
        }
        if (len == word.length()) {
            return true;
        }
        visited[row][col] = true;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        //上下左右
        for (int[] dir: directions){
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if(newRow >=0 && newRow  < board.length && newCol >= 0 && newCol  < board[0].length){
                if(!visited[newRow][newCol]   ){
                    if (backTrack02(board,word,visited,newRow, newCol,len +1)) {
                        result =  true;
                        break;
                    }
                }
            }
        }
        visited[row ][col] = false;
        return result;
    }
}
