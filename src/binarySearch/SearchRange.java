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
        if(nums.length ==0){
            index[0] = -1;
            index[1] = -1;
            return index;
        }
        int left = 0 ,right = nums.length-1;
        while (left <= right){
            int mid = ((right-left) >> 1) + left;
            if (nums[mid]==target) {
                //找左节点
                int l = diguiLeft(mid,nums, target, left, mid - 1);
                index[0] = l;
                //找右节点
                int r = diguiRight(mid,nums, target, mid + 1, right);
                index[1] = r;
                return index;
            }else if (nums[mid] >target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        index[0] = -1;
        index[1] = -1;
        return index;
    }

    public static int  diguiLeft(int l,int[] nums, int target,int left,int right){
        while (left <= right){
            int mid = ((right-left) >> 1) + left;
            if (nums[mid]==target) {
                l = mid;
                right = mid-1;
            }else if (nums[mid] >target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return l;
    }

    public static int  diguiRight(int r,int[] nums, int target,int left,int right){
        while (left <= right){
            int mid = ((right-left) >> 1) + left;
            if (nums[mid]==target) {
                r = mid;
                left = mid+1;
            }else if (nums[mid] >target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return r;
    }

}
