package matrix;

/**
 * @Desc: 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * @Author：zhh
 * @Date：2025/5/20 10:05
 */
public class Rotate {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 7},
                {4, 5, 6, 8},
                {7, 8, 9, 9},
                {5, 7, 3, 2}
        };
        rotate(matrix);
    }

    /**
     * 使用辅助矩阵
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int[][] newMatrix = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                //1. 往对角翻转  matrix[j][i] <-  matrix[i][j];
                //2. 水平翻转   matrix[j][colLen - 1 - i] <- matrix[j][i];
                //3. 合并推导   matrix[j][colLen - 1 - i] <- matrix[i][j];
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
     * 顺时针旋转90度: 1.转置矩阵 2.水平翻转
     * @param matrix
     */
    public static void rotate02(int[][] matrix) {
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
        for (int i = 0; i < rowLen; i++) {
            //调换只需要调换一半
            for (int j = 0; j < colLen / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][colLen - 1 -j];
                matrix[i][colLen - 1 -j] = temp;
            }
        }
    }
}
