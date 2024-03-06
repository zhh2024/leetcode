package binarySearch;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * @Desc: 二分查找入门
 * @Author：zhh
 * @Date：2023/9/20 21:25
 */
public class SearchInsert {

    public static void main(String[] args) {
        int nums[] = {1,3,5,7,9};
        int target = 5 ;
        System.out.println(searchInsert(nums,target));
    }

    /**
     * 二分查找:  能实现二分查找的,必然是有序的 ,不有序，找中间值就没有意义。
     * 查找思路: 找到中间值,然后目标值与中间值比较,相等 找到，小于中间值, 找中间值左边的数组，大于中间值找右边的数组
     * 新数据依旧先找到中间值,继续同样的处理，直到找到目标值。就算找不到也是无限逼近目标值,此时的下标就是无限逼近目标值
     *
     * 问题: 1. 中间值怎么求？ (left+right) >> 1 还是 ((right-left) >> 1) + left 呢？
     *          (left+right) 的方式有个隐患 ,可能会超出整形范围  。right-left就不会有这个隐患 选择 right-left
     *       2. 递归可以, while循环也可以,需要重新对数组的left 和 right下标赋值。
     *       3. 循环和递归条件？ left<=right  保证至少一个元素
     *          退出循环和递归条件? left>rigth就退出。
     *       4. 注意mid不符合,新拆分的对半数组一定要排除掉比较过的mid ,也就是要 mid+1 ,或者 mid -1
     *       5. 没有找到 应该返回最接近插入的下标值。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int left =0 ,right = nums.length-1;
        while (left <= right){
            int mid = ((right-left) >> 1) + left;
            //大 说明在右区间
            if(target >  nums[mid] ){
                left = mid+1;
            }else {
                right = mid -1;
            }
        }
        //走到这一步 表示目标值没找到,但是无限逼近目标值。
        //此时最后一个值下标mid == left == right 进入最后一次循环,如果目标值比mid大 left = mid+1 否则  right = mid -1
        //那么假设 目标值 大于最后一个值,插入位置正好是 下标+1 == left
        //         目标值 小于等于最后一个值,插入位置正好是 当前下标,还是left ,因为left此时不会变化。
        return left;
    }


}
