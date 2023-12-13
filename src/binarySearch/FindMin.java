package binarySearch;

/**
 * @Desc: 知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * @Author：zhh
 * @Date：2023/12/11 21:42
 */
public class FindMin {
    public static void main(String[] args) {
        int[] nums = {11,13,15,17};
        System.out.println(findMin(nums));
    }

    /**
     * 解题思路:二分查找本质就是需要明确一个条件可以符合,选左侧还是选右侧。只有这个条件符合才可以二分
     * 如何寻找这个条件，我个人建议图形结合，将数组连续的值用坐标方式展示出来，就可以清晰的看到数组值的规律，从而找到一个坐标点，思考是否有条件满足选择其中一侧。
     * 此题就需要图形结合画出来，可知旋转点就是最小值，旋转点右侧的值必然全部小于旋转点左侧的值。通过这个可以求解。
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int left=0,right=nums.length-1;
        int rightNum = nums[right];
        while (left<right){
            int mid = ((right-left) >> 1 ) + left;
            if(nums[mid]>rightNum){
                //代表旋转点不在左侧
                left = mid+1;
            }else {
                //代表旋转点在右侧,但mid值并没有比较，mid有可能是旋转点
                right = mid;
            }
        }
        //无限逼近，直至left==right就是最终结果
        return nums[left];
    }
}
