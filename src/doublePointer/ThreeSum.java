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
        int[] nums = {0,0,0,0};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }

    /**
     * 解题思路: 1. 暴力算法n^3 , 但是还需要去重,还需要额外的时间和空间
     *          2. 先将无序变成有序，有序数组中就可以在遍历的过程中去重。
     *          3. 再利用有序的两数之和求法是n，就可以将三数之和转化成两数之和,需要循环遍历第一个数，用n^2解决。
     *             有序数组并且可以在遍历过程中去重,减少最后list去重的时间复杂度和空间复杂度
     *          4. 所以最终时间复杂度为 排序nlogn+ 遍历n^2 = nlogn+n^2
     *
     *          思考为什么有序数组可以在遍历过程中去重？不需要额外的时间复杂度和空间复杂度
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> allResult = new ArrayList<>();
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            //去重
            if (i>0 && nums[i-1] == nums[i]) {
                continue;
            }
            int target = sum - nums[i];
            int left = i+1;
            int right = nums.length - 1;
            int before = nums[right];
            while (left <right){
                //去重
                if(right<nums.length - 1 && nums[right] == before){
                    right--;
                    continue;
                }
                if(nums[left]+ nums[right] == target){
                    before = nums[right];
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
