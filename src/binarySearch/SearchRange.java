package binarySearch;

/**
 * @Desc: 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * @Author：zhh
 * @Date：2023/11/16 22:03
 */
public class SearchRange {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6,6,6,8,9};
        int[] ints = searchRange(nums, 6);
        System.out.println(ints[0]+"   "+ints[1]);

    }

    public static int[] searchRange(int[] nums, int target) {
        int[] index = new int[2];
        index[0] = -1;
        index[1] = -1;
        //先找到第一个target,然后一个往左,一个往右找。
        searchLeft(nums,target,index);
        searchRight(nums,target,index);
        return index;
    }

    public static void  searchLeft(int[] nums,int target,int[] index){
        int left=0,right=nums.length-1;
        while (left <= right){
            int mid = ((right-left) >> 1) + left;
            if (nums[mid]==target) {
                //找到了target,先赋值，但是循环还没结束，有可能还存在target,继续往左边找，直至循环结束。
                index[0]=mid;
                right = mid-1;
            }else if (nums[mid] >target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
    }

    public static void  searchRight(int[] nums,int target,int[] index){
        int left=0,right=nums.length-1;
        while (left <= right){
            int mid = ((right-left) >> 1) + left;
            if (nums[mid]==target) {
                //找到了target,先赋值，但是循环还没结束，有可能还存在target,继续往右边找，直至循环结束。
                index[1]=mid;
                left = mid+1;
            }else if (nums[mid] >target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
    }

}
