package doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc: 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * @Author：zhh
 * @Date：2024/2/27 21:49
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-2,0,0,2,2}; //
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> allResult = new ArrayList<>();
        int sum=0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i>0 && nums[i-1] == nums[i]) {
                continue;
            }
            int target = sum - nums[i];
            int left = i+1;
            int right = nums.length - 1;
            while (left <right){
                if(nums[left]+ nums[right] == target){
                    List<Integer> result = new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[left]);
                    result.add(nums[right]);
                    allResult.add(result);
                    left++;
                    right--;
                }else if(nums[left]+ nums[right] < target){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return allResult;
    }


}
