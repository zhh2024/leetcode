package binarySearch;

/**
 *
 * 二分查找的本质就是先找到一个中间值, 总满足一个条件能够判断出来放到左区间 还是 右区间
 * 只要能够分辨出左区间符合条件就是左边，不符合就放到右边，直至剩最后一个值。
 *
 * @Author：zhh
 * @Date：2023/10/10 21:08
 */
public class Search {
    public static void main(String[] args) {
        int[] nums = {1,3};
        System.out.println(search(nums,0));

    }
    public static int search(int[] nums, int target) {
        int left =0,right = nums.length-1;
        while (left<=right){
            int mid = ((right-left) >> 1) + left;
            if(nums[mid] == target){
                return mid;
            }
            int leftValue = nums[left];
            if(nums[mid] < leftValue){
                //左边不是有序的,符合条件就是左区间
                if(nums[left] > target && nums[mid] < target  ){
                    left = mid +1;
                }else {
                    right = mid -1;
                }
            }else {
                //左边是有序的，最大值比target小,或者最小值比target大，说明不在左区间
                if(nums[mid] < target || nums[left] > target){
                    left = mid +1;
                }else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }
}
