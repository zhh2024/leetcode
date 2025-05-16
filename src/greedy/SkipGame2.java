package greedy;

/**
 * @Desc: 跳跃游戏 II
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * @Author：zhh
 * @Date：2023/8/15 22:23
 */
public class SkipGame2 {

    public static void main(String[] args) {
        int[] nums = { 5,9,3,2,1,0,2,3,3,1,0,0};
        int b = canJump01(nums);
        System.out.println(b);
    }

    /**
     * 思路: 在当前可跳的辐射范围之内,计算每个下标所辐射的距离,哪个下标辐射的距离最远就跳到哪里。
     * 因为能辐射的距离更远，说明该下标能覆盖其他下标所辐射的距离，还能达到其他下标所不能达到的距离,故选择此下标是最优解。
     *
     * @param nums
     * @return
     */
    public static int canJump01(int[] nums) {
        int length = nums.length -1;
        int next = 0;
        int number = 0;
        while (next < length){
            int currentLen = nums[next] + next;
            if(currentLen >= length){
                return ++number;
            }
            int maxLen = 0;
            int maxIndex = 0;
            for (int i = next + 1; i <= currentLen; i++) {
                if(nums[i] +i > maxLen){
                    maxLen = nums[i] +i;
                    maxIndex = i;
                }
            }
            next = maxIndex;
            number++;
        }
        return number;
    }


    /**
     * 优化01的代码
     * @param nums
     * @return
     */
    public int jump02(int[] nums) {
        int length = nums.length -1;
        //当前这一步所辐射的最大距离
        int end = 0;
        //步数
        int steps = 0;
        //当前可跳的最远位置
        int maxPosition = 0;
        for (int i = 0; i < length; i++) {
            maxPosition = Math.max(maxPosition,nums[i] +i);
            if(end == i){
                if(maxPosition > length){

                    steps++;
                }

            }
        }
        return steps;

    }

}
