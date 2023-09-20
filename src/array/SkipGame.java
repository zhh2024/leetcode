package array;

/**
 * @Author：zhh
 * @Date：2023/8/15 22:23
 */
public class SkipGame {

    public static void main(String[] args) {
        int[] nums = {3,2,3,0,4};
        boolean b = canJump(nums);
        System.out.println(b);
    }

    public static boolean skipGame(int[] nums){
        int skip =0;
        int prevPace =0;
        while (skip+nums[skip] < nums.length-1){
            if(nums[skip] ==0){
                return false;
            }
            int pace = skip+nums[skip];
            for (int i = prevPace+1; i < pace; i++) {
                if(nums[i]+i > skip+nums[skip]){
                    skip = i;
                }
            }
            if(pace == skip+nums[skip]){
                skip = pace;
            }
            prevPace = pace;
        }
        return true;
    }

    /**
     * 贪心算法思想
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }


}
