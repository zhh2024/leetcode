package dynamicProgramming.one;

/**
 * @Desc: 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 解题思路: 找到动态转移方程为:
 *             int currentMax = Math.max(nums[i],Math.max(nums[i] * preMax,nums[i] * preMin));
 *             int currentMin = Math.min(nums[i],Math.min(nums[i] * preMax,nums[i] * preMin));
 * @Author：zhh
 * @Date：2025/12/21 15:21
 */
public class MaxProduct {
    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        int[] a= {0,2};
        int i = maxProduct.maxProduct(a);
        System.out.println(i);
    }

    public int maxProduct(int[] nums) {
        int preMax = nums[0];
        int preMin = nums[0];
        int finalMax = preMax;
        for (int i = 1; i < nums.length; i++) {
            int currentMax = Math.max(nums[i],Math.max(nums[i] * preMax,nums[i] * preMin));
            int currentMin = Math.min(nums[i],Math.min(nums[i] * preMax,nums[i] * preMin));
            finalMax = Math.max(finalMax,currentMax);
            preMax = currentMax;
            preMin = currentMin;
        }
        return finalMax;
    }
}
