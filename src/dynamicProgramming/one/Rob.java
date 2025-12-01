package dynamicProgramming.one;

/**
 * @Desc: 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * @Author：zhh
 * @Date：2025/6/4 13:48
 */
public class Rob {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        Rob rob = new Rob();
        int rob1 = rob.rob(nums);
        System.out.println(rob1);
    }

    /**
     * 用 dp[i] 表示前 i 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程：
     * dp[i]=max(dp[i−2]+nums[i],dp[i−1])
     * 边界条件为：
     * dp[0]=nums[0]
     * dp[1]=max(nums[0],nums[1])
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i-2],dp[i-1]);
        }
        return dp[nums.length-1];
    }

    /**
     * 优化: 不需要使用数组记录每个房屋,每次只比较上一个和当前+上上一个,只需要两个房屋坐标，每次覆盖即可。
     * @param nums
     * @return
     */
    public int rob02(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int dp1  = nums[0];
        int dp2 = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = dp2;
            dp2 = Math.max(nums[i] + dp1,dp2);
            dp1 = temp;
        }
        return dp2;
    }

}
