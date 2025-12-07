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
        int left = 0;
        int right = nums.length -1;
        int rightNum = nums[right];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            if(midNum == target){
                return mid;
            }
            //1. 无非就两个有序区间,判断mid 在哪个区间即可。
            if(midNum <= rightNum){
                //2. 确定在第二个区间,就可以判断取该区间的哪一边了
                if(target < midNum || target > rightNum){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                //2. 确定在第一个区间,就可以判断取该区间的哪一边了
                if(target < midNum && target > rightNum){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
