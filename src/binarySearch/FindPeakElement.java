package binarySearch;

/**
 * @Desc: 峰值元素是指其值严格大于左右相邻值的元素。给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * @Author：zhh
 * @Date：2023/12/11 21:29
 */
public class FindPeakElement {
    public static void main(String[] args) {
        int[] nums = {1,3};
        System.out.println(findPeakElement(nums));
    }

    /**
     * 返回 任何一个峰值 所在位置即可。
     * 那么二分如何区分条件，就是当前mid值的下一个值比它是否大,如果大，肯定峰值在右侧，如果小肯定峰值在左侧。
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        int left=0,right=nums.length-1;
        while (left<right){
            int mid = ((right-left) >> 1 ) + left;
            //为什么mid+1不会越界，1.是因为 while条件left<right,触发了这个条件就肯定>=2,
            // 2. mid计算((right-left) >> 1 ) ,比如3/2，不足2,就选1，是逼近left的
            // 两个条件就确定了mid+1不会越界
            if(nums[mid+1]>nums[mid]){
                //右侧
                left = mid +1;
            }else {
                //左侧,但是此时mid值并没有与mid-1比较，所以mid值有可能是峰值
                right = mid;
            }
        }
        return left;
    }
}
