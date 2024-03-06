package slideWindow;

import java.util.Arrays;

/**
 * @Desc: 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 * @Author：zhh
 * @Date：2024/2/29 11:13
 */
public class MinSubArrayLen {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(minSubArrayLen03(15, nums));
    }

    /**
     * 暴力法 n^2
     * 解题思路:
     *    1. 从数组第一个开始遍历
     *    2. 依次累加数组后面的值,直至符合sum>=target,与minLen比较哪个最小,将最小值赋予minLen
     *    3. 但是此时minLen赋值就会有歧义,一开始如何定义初始化值,目的是与(minLen比较哪个最小)所以一开始应该定义成最大值。
     *       这样第一次找到的len默认最小值,后续判断就可以与第一次的len比较，直至找到最小的minLen。
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen01(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum =0;
            for (int j = i; j < nums.length; j++) {
                sum = sum +nums[j];
                if(sum >= target){
                    minLen = Math.min(j-i+1,minLen);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? 0:minLen;
    }

    /**
     * 前缀和+二分查找法  nlogn
     * 解题思路:
     *   假设数组是{2,3,1,2,4,3} target=7
     *   1. 在暴力法的基础上做优化,内层遍历求和复杂度是n
     *   2. 遍历求和累加,每次累加后的值符合递增数组
     *      下标0 的累加值  {2,5,6,8,12,15}
     *      下标1 的累加值  {3,4,6,10,13}
     *      下标2 的累加值  {1,3,7,10}
     *      下标3 的累加值  {2,6,9}
     *      下标4 的累加值  {4,7}
     *      下标5 的累加值  {3}
     *   可以看出每个下标的累加值数组符合递增数组可以二分查找，但是需要每个下标生成一个数组,如何使用一个共用数组满足
     *   每个下标的累加值要求呢？
     *   3. 下标0 的累加值  {2,   5, 6, 8,12,15}
     *      下标1 的累加值  {0,   3, 4, 6,10,13}
     *      下标2 的累加值  {-3,  0, 1, 3,7, 10}
     *      下标3 的累加值  {-4, -1, 0, 2,6, 9}
     *      下标4 的累加值  {-6, -3,-2, 0,4, 7}
     *      下标5 的累加值  {-10,-7,-8,-4,0, 3}
     *   可以发现规律，下标1的累加值是下标0的累加值减去2
     *               下标2的累加值是小标0的累加值减去5
     *               以此类推就是每减去前面一个值就是当前下标的累加值数组 ，这是因为前面一个值是当前下标值前面的所有下标之和
     *               把它减掉就是排除了前面的数组元素。
     *
     *   3. 但是共用数组使用，不能每次外层遍历都减去前面一个数再二分，这样效率低,可以让target加上前面一个值再去二分
     *   4. 原数组{2,3,1,2,4,3}与递增数组 {2,5,6,8,12,15} 进行循环运算 ,下标不映射
     *      如果递增数组是{0,2,5,6,8,12,15} ,此时循环运算target加前面一个值,就不会出现下标越界问题。并且可以映射
     *      所以最终递增数组是{0,2,5,6,8,12,15}
     *   4. 这样通过二分查找,找到的值所对应的下标index就是原数组所累加到的下标+1，
     *      此时再减去外层遍历的下标 就是长度(因为递增数组多了一个下标值0)。
     *
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen02(int target, int[] nums) {
        int sums[] = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            sums[i+1] = nums[i]+sums[i];
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int s = target + sums[i];
            int index = binarySearch(sums, s);
            if (index == sums.length) {
                continue;
            }
            minLen = Math.min((index - i) ,minLen);
        }
        return minLen == Integer.MAX_VALUE ? 0:minLen;
    }

    public static int binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while (left <= right){
            int mid = ((right - left) >> 1) + left;
            if(nums[mid] >= target){
                right = mid-1;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int minSubArrayLen03(int target, int[] nums) {
        int sums[] = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            sums[i+1] = nums[i]+sums[i];
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int num = sums[i];
            int s = num + target;
            int index = binarySearch(sums, s);
            if (index == sums.length) {
                continue;
            }
            minLen = Math.min((index - i) ,minLen);
        }
        return minLen == Integer.MAX_VALUE ? 0:minLen;
    }

}
