package binarySearch;

/**
 * @Desc: 滑动窗口标准模板
 * @Author：zhh
 * @Date：2025/5/19 11:29
 */
public class Base {

    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        //循环条件
        while (left <= right) {
            //找中间
            int mid = ((right - left) >> 1) + left;
            //判断在哪一边
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        /*
          此时最后一个值下标mid == left == right 进入最后一次循环,如果目标值比mid大 left = mid+1 否则  right = mid -1
          那么假设 目标值 大于最后一个值,插入位置正好是 下标+1 == left
          目标值 小于等于最后一个值,插入位置正好是 当前下标,还是left ,因为left此时不会变化。
        */
        return left;
    }
}
