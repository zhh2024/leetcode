package backTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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


    public ArrayList<HashMap> totalNQueens(int n) {
        HashMap<Integer, Integer> queen = new HashMap();
        backTrack(queen, n, 0);
        return list;
    }

    int number = 0;
    ArrayList<HashMap> list = new ArrayList<>();


    /**
     * 优化: 只处理第一行即可,因为n皇后,必然每行都有一个元素,第一个元素必然在第一行,第二个元素必然在第二行,以此类推
     *
     * @param queen
     * @param n
     * @param row
     */
    public void backTrack(HashMap<Integer, Integer> queen, int n, int row) {
        if (row == n) {
            list.add(new HashMap(queen));
            number++;
            return;
        }
        for (int j = 0; j < n; j++) {
            //1. 判断是否进入递归
            //TODO 使用map记录行列,需要O(n)复杂度判断,如何优化使得校验成为O(1)
            if (check(queen, row, j)) {
                queen.put(row, j);
                backTrack(queen, n, row + 1);
                queen.remove(row);
            }
        }
    }

    public boolean check(HashMap<Integer, Integer> queen, int row, int col) {
        if (queen.isEmpty()) {
            return true;
        }
        Set<Integer> integers = queen.keySet();
        for (Integer rows : integers) {
            //行
            if (rows == row) {
                return false;
            }
            Integer cols = queen.get(rows);
            //列
            if (cols == col) {
                return false;
            }
            //主对角线
            if (row - rows == col - cols) {
                return false;
            }
            //副对角线
            if (row - rows == cols - col) {
                return false;
            }
        }
        return true;
    }


    int sum = 0;

    /**
     * 使用map记录行列,需要O(n)复杂度判断.
     * 因为是根据行遍历，所以影响点就是列，主对角线，副对角线，列可以直接放到一个hash集合中，主/副对角线的规则也可以放到hash集合中。
     * 这样三个hash集合，就可以实现O(1)复杂度了。
     */
    public int totalNQueens02(int n) {
        Set<Integer> colSet = new HashSet<>();
        Set<Integer> mainDiagonalSet = new HashSet<>();
        Set<Integer> antiDiagonalSet = new HashSet<>();
        backTrack(n, 0, colSet, mainDiagonalSet, antiDiagonalSet);
        return sum;
    }

    public void backTrack(int n, int row, Set<Integer> colSet, Set<Integer> mainDiagonalSet, Set<Integer> antiDiagonalSet) {
        if (row == n) {
            sum++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (colSet.contains(i)) {
                continue;
            }
            //在同一条主对角线上的所有格子，它们的 行索引 - 列索引 的值是相等的！ 因为主对角线是越来越大的
            if (mainDiagonalSet.contains(row - i)) {
                continue;
            }
            //在同一条副对角线上的所有格子，它们的 行索引 + 列索引 的值是相等的！ 因为副对角线是越来越小的
            if (antiDiagonalSet.contains(row + i)) {
                continue;
            }
            colSet.add(i);
            mainDiagonalSet.add(row - i);
            antiDiagonalSet.add(row + i);
            backTrack(n, row + 1, colSet, mainDiagonalSet, antiDiagonalSet);
            colSet.remove(i);
            mainDiagonalSet.remove(row - i);
            antiDiagonalSet.remove(row + i);
        }
    }
}
