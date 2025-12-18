package array;

/**
 * @Desc: 分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子中，评分更高的那个会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 示例 1：
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 *
 * 示例 2：
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 * @Author：zhh
 * @Date：2025/12/18 15:46
 */
public class Candy {

    /**
     * 两次遍历
     * 因为相邻是左右相邻，需要两次遍历
     * 1. 先从左到右，依次算额外糖果数
     * 2. 再从右到左，此时就是判断额外糖果数是否需要增加
     * 最终累加就是最少糖果数
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int length = ratings.length;
        int sumCandy = length;
        int[] candy = new int[length];
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i-1] + 1;
            }
        }
        for (int i = length - 2; i >=0; i--) {
            if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) {
                candy[i] = candy[i + 1] + 1;
            }
        }
        for (int j:candy){
            sumCandy = sumCandy +j;
        }
        return sumCandy;

    }
}
