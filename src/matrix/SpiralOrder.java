package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 螺旋矩阵
 * @Author：zhh
 * @Date：2025/4/14 9:08
 */
public class SpiralOrder {

    public static void main(String[] args) {
        SpiralOrder spiralOrder = new SpiralOrder();
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> integers = spiralOrder.spiralOrder(matrix);
        System.out.println(integers.toString());
    }

    /**
     * 解题思路: 右下左上 一圈为一次循环,每次循环边界-1,直至无法循环。
     * 定义左右上下每圈的范围,直至左右相等和上下相等
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> integers = new ArrayList<>();
        //长
        int len = matrix.length;
        //宽
        int wide = matrix[0].length;

        //初始化 左,右,上,下
        int left = 0;
        int right = wide-1;
        int top = 0;
        int bottom = len-1;

        while (left <= right && top <= bottom){
            cycle(matrix,left,right,top,bottom,integers);
            left++;
            top++;
            right--;
            bottom--;
        }
        return integers;
    }

    public void cycle(int[][] matrix,int left,int right, int top,int bottom, List<Integer> integers){
        //右
        for (int column = left; column <= right; column++) {
            integers.add(matrix[top][column]);
        }
        //下
        for (int row = top + 1; row <= bottom; row++) {
            integers.add(matrix[row][right]);
        }
        //判断是否存在左上
        if (left < right && top < bottom) {
            //左
            for (int column = right - 1; column > left; column--) {
                integers.add(matrix[bottom][column]);
            }
            //上
            for (int row = bottom; row > top; row--) {
                integers.add(matrix[row][left]);
            }
        }
    }


}
