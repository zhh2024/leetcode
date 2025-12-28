package dynamicProgramming.two;

/**
 * @Desc: 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11]
 *
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 * 解题思路:
 * 1. 转化成另一个问题，是否可以从数组中选取不重复的下标值，构成总和的一半。如果可以构成，就是true。
 * 2. 总和必然是偶数才可以，总和的一半必然大于数组中的最大值才可以。
 * 3. 如果按照穷尽排列，进行一一，二二，三三等排列，是可以算出来的
 * 4. 过程中会产生大量的重复解，比如当前下标的排列情况，取决于前面子数组的排列情况
 * 5. 那么就可以产生转移方程，当前下标的所有值情况，依赖于前面下标的所有值情况 + （前面下标所有值情况+当前小标值） = 当前下标值的所有情况。
 * 6. 所以需要一个数组来记录前一个下标的所有值情况，才可以推导出来当前下标值的所有值情况。

   其实该题本质是二维动态规划
   因为每一个下标所产生的解，都是多个，需要数组来存入。变成一维动态规划是因为当前下标会整合前面所有产生的解，所以只需要记录上一个下标的解就可以。
 * @Author：zhh
 * @Date：2025/12/21 15:45
 */
public class CanPartition {
    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        int[] a= {3,3,6,8,16,16,16,18,20};
        boolean b = canPartition.canPartition(a);
        System.out.println(b);
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        dp[nums[0]] = true;
        for (int i = 1; i < n; i++) {

            int value = nums[i];
            //需要注意的是第二层的循环我们需要从大到小计算，因为如果我们从小到大更新 dp 值，那么在计算 dp[j] 值的时候，dp[j−nums[i]] 已经是被更新过的状态，不再是上一行的 dp 值。
            for (int j = dp.length - 1; j >= value ; j--) {
                dp[j] = dp[j - value] || dp[j];
            }

        }
        return dp[dp.length-1];

    }




}
