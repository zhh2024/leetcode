package array;

/**
 * @Desc: 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组是数组中的一个连续部分。
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * 解题思路: 动态规划
 * 1.因为是累加,所以可以用一个数组，记录当前下标可以累加的最大值(如何计算呢，就是上一个dp数组的最大值加上当前下标进行比较即可)，然后该dp数组最大值就是最大子数组和
 * 2.因为发现dp数组只使用了最新的一个下标值，所以用一个变量就可以替换dp数组，从而减轻空间复杂度。
 * @Author：zhh
 * @Date：2025/12/19 10:15
 */
public class MaxSubArray {


    public int maxSubArray(int[] nums) {
        int subMax = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            subMax = Math.max(subMax + num,num);
            max = Math.max(subMax,max);
        }
        return max;
    }
}
