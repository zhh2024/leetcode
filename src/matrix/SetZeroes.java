package matrix;

/**
 * @Desc: 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用原地算法。
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * @Author：zhh
 * @Date：2025/5/20 16:56
 */
public class SetZeroes {

    public static void main(String[] args) {

    }

    /**
     * 思路: 1. 遍历原数组,使用两个数组记录0出现的行列
     *      2. 遍历两个数组,进行更新
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] a = new int[row];
        int[] b = new int[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    //记录要刷新的行列
                    a[i]++;
                    b[j]++;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(a[i] > 0 || b[j] > 0 ){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 不适用额外数组记录行列
     * 
     * 1. 遍历排除首行首列数组的过程中，如果出现0,就将首行首列记为0。
     * 2. 然后遍历更新的原数组,只遍历首行首列,首列出现0只更新对应的行,首行出现0只更新对应的列。
     * 3. 现在的问题就是首行首列还没更新。
     * 4. 如果原首行存在0,记录标志,代表首行要更新,首列存在0,记录标志,代表首列要更新
     *
     * 难点:
     * 会排除出去首行首列,首行首列得额外判断
     * @param matrix
     */
    public void setZeroes02(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean firstCol = false;
        boolean firstRow = false;
        //检查首行首列是否要更新
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] ==0) {
                firstCol = true;
            }
        }
        for (int i = 0; i < col; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
            }
        }
        //排除首行首列,元素出现0,更新首行首列
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        //更新排除首行首列的元素
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //更新首行首列
        if(firstRow){
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if(firstCol){
            for (int j = 0; j < row; j++) {
                matrix[j][0] = 0;
            }
        }

    }


}
