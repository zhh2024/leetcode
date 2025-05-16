package greedy;

/**
 * @Desc: 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * @Author：zhh
 * @Date：2023/8/15 22:23
 */
public class SkipGame {

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 0, 4};
        boolean b = canJump(nums);
        System.out.println(b);
    }

    /**
     * 思路: 每次选择当前最优解
     * 1.初始化可跳的最远距离
     * 2.遍历这个最远距离内的下标
     * 3.重新选取可跳的最远距离
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int targetIndex = nums.length-1;
        int maxIndex = 0;
        for (int i = 0; i < nums.length  ; i++) {
            //核心判断,必须在目前最远范围之内
            if(i <= maxIndex){
                //重新选取最远范围
                int currentMaxIndex = nums[i] + i;
                maxIndex = Math.max(maxIndex,currentMaxIndex);
                //最远范围是否超越目标
                if(maxIndex >= targetIndex){
                    return true;
                }
            }
        }
        return false;
    }


}
