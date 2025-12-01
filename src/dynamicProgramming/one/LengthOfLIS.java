package dynamicProgramming.one;

import java.util.Arrays;

/**
 * @Desc: 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * @Author：zhh
 * @Date：2025/6/6 17:14
 */
public class LengthOfLIS {
    public static void main(String[] args) {
        int[] nums = {7,7,7,7,7};
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        int i = lengthOfLIS.lengthOfLIS(nums);
        System.out.println(i);
    }


    /**
     * 在暴力回溯的基础上,发现有重复子问题
     * 比如在[10,9,2,5,3,7,101,18] 数组中。  2->5->7->101 与 5->7->101 与 7->101 都有重复的子问题
     * 那么可以记录每一个当前元素的最优解,当上一个元素到达当前元素,就无需再计算了
     * 那么需要int[] dp = new int[nums.length]; dp数组记录每个元素的最优解
     * 动态转移方程为: dp[i] = Math.max(dp[j] + 1,dp[i])
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int max = 1;
        for (int i = nums.length - 2; i >= 0 ; i--) {
            for (int j = i +1; j <= nums.length -1; j++) {
                if(nums[i] < nums[j] ){
                    dp[i] = Math.max(dp[j] + 1,dp[i]);
                }
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }


}
