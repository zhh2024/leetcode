package backTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @Desc: N 皇后 II
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * @Author：zhh
 * @Date：2025/5/26 18:27
 */
public class TotalNQueens {
    public static void main(String[] args) {
        TotalNQueens totalNQueens = new TotalNQueens();
        ArrayList<HashMap> list = totalNQueens.totalNQueens(4);
        System.out.println(list.toString());
    }

    /**
     * 1. 明确递归深度为n
     * 2. 进入递归前需要判断,是否可以进入递归
     * 3. 如果达到递归深度,number++
     * 4. 返回可以达到递归深度的number
     *
     * 难点: 如何判断是否可以进入递归？
     * 1. 需要记录每个皇后所在的行列
     * @param n
     * @return
     */
    public  ArrayList<HashMap> totalNQueens(int n) {
        HashMap<Integer,Integer> queen = new HashMap();
        backTrack02(queen,n,0);
        return list;
    }
    int number = 0 ;
    ArrayList<HashMap> list = new ArrayList<>();

    /**
     * 1. 会产生大量的重复解,比如{0=1, 1=3, 2=0, 3=2} 然后第一层递归进入1=3,又产生了0=1,2=0,3=2
     * @param queen
     * @param n
     * @param deep
     */
    public void backTrack(HashMap<Integer,Integer> queen, int n ,int deep){
        if(deep == 0){
            list.add(new HashMap(queen));
            number ++;
            return;
        }
        //{0=1, 1=3, 2=0, 3=2}
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //1. 判断是否进入递归
                if (!check(queen,i,j)) {
                   continue;
                }
                queen.put(i,j);
                backTrack(queen,n ,deep -1);
                queen.remove(i);
            }
        }
    }

    /**
     * 优化: 只处理第一行即可,因为n皇后,必然每行都有一个元素,第一个元素必然在第一行,第二个元素必然在第二行,以此类推
     * @param queen
     * @param n
     * @param row
     */
    public void backTrack02(HashMap<Integer,Integer> queen, int n ,int row){
        if(row == n){
            list.add(new HashMap(queen));
            number ++;
            return;
        }
        for (int j = 0; j < n; j++) {
            //1. 判断是否进入递归
            //TODO 使用map记录行列,需要O(n)复杂度判断,如何优化使得校验成为O(1)
            if (check(queen,row,j)) {
                queen.put(row,j);
                backTrack02(queen,n ,row + 1);
                queen.remove(row);
            }
        }
    }

    /**
     * @param queen
     * @param row
     * @param col
     * @return
     */
    public boolean check(HashMap<Integer,Integer> queen , int row , int col){
        if(queen.isEmpty()){
            return true;
        }
        Set<Integer> integers = queen.keySet();
        for (Integer rows: integers){
            //行
            if(rows == row){
                return false;
            }
            Integer cols = queen.get(rows);
            //列
            if(cols == col){
                return false;
            }
            //主对角线
            if(row - rows ==  col - cols){
                return false;
            }
            //副对角线
            if(row - rows ==  cols - col){
                return false;
            }
        }
        return true;
    }
}
