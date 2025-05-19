package prefixSum;

import java.util.HashMap;

/**
 * @Desc: 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 *
 *
 * 考察点: 滑动窗口
 * @Author：zhh
 * @Date：2025/5/7 10:02
 */
public class SubarraySum {


    public static void main(String[] args) {
        SubarraySum subarraySum = new SubarraySum();
        int[] nums = {-1,2,-1,1,3};
        int i = subarraySum.subarraySum(nums, 0);
        System.out.println(i);
    }


    /**
     * 滑动动窗口针对的情况是，窗口变大或者变小，窗口内的某种性质是单调变化的。
     * 比如这个题，如果 nums[i] 都是正整数的话，那么窗口范围变大时，窗口内和是递增的，窗口范围变小时，窗口内和是递减的。
     * 这种情况下是可以使用滑动窗口的。
     *
     * 但是此题目有负数,不满足滑动窗口的单调性,无法确认是否收缩。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int number = 0;
        for (; right < nums.length; right++) {
            sum = sum + nums[right];
            while (left < right && sum > k ){
                sum = sum - nums[left];
                left++;
            }
            if(sum == k){
                number++;
            }
        }
        return number;
    }


    /**
     * 枚举,将每个出现的子数组都计算一次
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum02(int[] nums, int k) {
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum+ nums[j];
                if(sum == k){
                    number++;
                }
            }
        }
        return number;
    }


    /**
     * 优化枚举造成的计算重复问题,使用前缀和 + 哈希
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum03(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap<Integer, Integer> mp = new HashMap <> ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
