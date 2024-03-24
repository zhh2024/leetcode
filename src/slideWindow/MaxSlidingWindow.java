package slideWindow;

/**
 * @Desc: 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。返回滑动窗口中的最大值。
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * @Author：zhh
 * @Date：2024/3/15 16:04
 */
public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums={1,3,-1,-3,5,3,6,7};
        int[] ints = maxSlidingWindow(nums, 5);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }

    /**
     * 暴力解法:
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int left = 0,right = 0;
        int[] maxNums = new int[nums.length-k+1];
        int index =0;
        int max = Integer.MIN_VALUE;
        while (right < nums.length){
            max = Math.max(nums[right],max);
            if(right - left == k-1){
                //达到窗口大小,返回max,index++
                maxNums[index] = max;
                index++;
                //此时如果nums[left]是max,left移动后就需要重新计算max,如果nums[left]不是max,就无需重新计算
                if(nums[left]==max){
                    max = Integer.MIN_VALUE;
                    for (int i = 1; i < k; i++) {
                        max = Math.max(nums[left+i],max);
                    }
                }
                left++;
            }else {
                right++;
            }
        }
        return maxNums;
    }
}
