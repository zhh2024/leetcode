package matrix;

/**
 * @Desc: 矩阵基础操作
 * 注意: 涉及到对角线转置,前提得是n*n矩阵,才会存在对角线。非n*n矩阵,无法使用对角线转置
 * @Author：zhh
 * @Date：2025/5/19 14:39
 */
public class Base {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 7},
                {4, 5, 6, 8},
                {7, 8, 9, 9},
                {5, 7, 3, 2}
        };
        //horizontalFlip(matrix);
        //verticalFlip(matrix);
        rotateCounterClockwise02(matrix);
        printMatrix(matrix);

    }
    // 辅助函数：打印矩阵
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    /**
     * 水平翻转: 列交换,左右交换
     * @param matrix
     */
    public static void horizontalFlip(int[][] matrix){
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        //每一行都需要调换
        for (int i = 0; i < rowLen; i++) {
            //调换只需要调换一半
            for (int j = 0; j < colLen / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][colLen - 1 -j];
                matrix[i][colLen - 1 -j] = temp;
            }
        }
    }

    /**
     * 垂直翻转: 行交换,上下交换
     * @param matrix
     */
    public static void verticalFlip(int[][] matrix) {
        int rowLen = matrix.length;
        //交换上下行,一行整体调换
        for (int i = 0; i < rowLen / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[rowLen - 1 -i];
            matrix[rowLen - 1 -i] = temp;
        }
    }

    /**
     * 转置矩阵: 主对角线翻转
     * 对称轴：主对角线（从左上到右下的对角线）。
     * 操作规则：交换矩阵中元素的行列索引，即元素 (i, j) 变为 (j, i)。
     * B[j][i]=A[i][j]。
     * 原地转置限制：方阵可原地转置（行=列），非方阵需新建矩阵。
     * 注意: 因为i j互换,只有n*n 矩阵才可以原地转置,非n * n矩阵不可以原地转置,需新建矩阵(需要额外时间复杂度)
     * @param matrix
     */
    public static void mainReverse(int[][] matrix){
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        //行列交换
        for (int i = 0; i < rowLen; i++) {
            //注意此下标,是从i开始,而不是0,为了排除已交换的数据
            for (int j = i; j < colLen; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 转置矩阵: 副对角线翻转
     * 对称轴：副对角线（从右上到左下的对角线）。
     * 操作规则：元素 (i, j) 变为 (n-1-j, m-1-i)（假设矩阵为 m x n）。
     * 注意: 对角线翻转,只有n * n 矩阵才有对角线。因为i j互换,只有n*n 矩阵才可以原地转置,非n * n矩阵不可以原地转置,需新建矩阵(需要额外时间复杂度)
     * @param matrix
     */
    public static void subReverse(int[][] matrix){
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        //行列交换
        for (int i = 0; i < rowLen; i++) {
            //注意此下标,是从0开始,但是列每次都-1
            for (int j = 0; j < colLen - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[rowLen - 1- i][colLen - 1 - j];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 顺时针旋转90度: 1.转置矩阵 2.水平翻转
     * @param matrix
     */
    public static void rotateClockwise(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        //1.行列交换
        for (int i = 0; i < rowLen; i++) {
            for (int j = i; j < colLen; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //2. 左右交换
        horizontalFlip(matrix);
    }

    /**
     * 顺时针旋转90度推导过程
     * 1. 主对角翻转  matrix[j][i] <-  matrix[i][j];
     * 2. 水平翻转   matrix[j][colLen - 1 - i] <- matrix[j][i];
     * 3. 合并推导   matrix[j][colLen - 1 - i] <- matrix[i][j];
     * @param matrix
     */
    public static void rotateClockwise02(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int[][] newMatrix = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                newMatrix[j][colLen - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }

    /**
     * 逆时针旋转90度:  1.转置矩阵 2.垂直翻转
     * @param matrix
     */
    public static void rotateCounterClockwise(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        for (int i = 0; i < rowLen; i++) {
            for (int j = i; j < colLen; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        verticalFlip(matrix);
    }

    /**
     * 逆时针旋转90度推导过程
     * 1. 主对角翻转  matrix[j][i] <-  matrix[i][j];
     * 2. 垂直翻转   matrix[rowLen - 1 - j][i] <- matrix[j][i];
     * 3. 合并推导   matrix[rowLen - 1 - j][i] <- matrix[i][j];
     * @param matrix
     */
    public static void rotateCounterClockwise02(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int[][] newMatrix = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                newMatrix[rowLen - 1 - j][i] = matrix[i][j];
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }

}
