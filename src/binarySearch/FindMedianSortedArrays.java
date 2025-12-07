package binarySearch;

/**
 * @Desc: 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * @Author：zhh
 * @Date：2025/3/19 9:30
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int midIndex = (nums1.length + nums2.length -1)/2;

        int lIndex = 0;
        int rIndex = 0;
        int sumStep = midIndex;

        while (lIndex < nums1.length && rIndex < nums2.length){
            if(nums1[lIndex] <= nums2[rIndex]){
                lIndex ++;
            }else {
                rIndex ++;
            }
            sumStep--;
            if(sumStep == 0){
                if(midIndex%2 == 0){
                    int currentNum = 1;
                    int preNum = 2;
                    return (double) (currentNum + preNum) /2;

                }else {
                    return 1;
                }
            }


        }
        while (lIndex < nums1.length){
            lIndex ++;
            sumStep --;
            if(sumStep == 0){
                if(midIndex%2 == 0){
                    int currentNum = nums1[lIndex];
                    int preNum = nums1[lIndex -1 ];
                    return (double) (currentNum + preNum) /2;

                }else {
                    return nums1[lIndex];
                }
            }
        }
        while (rIndex < nums2.length){
            rIndex ++;
            sumStep --;
            if(sumStep == 0){
                if(midIndex%2 == 0){
                    int currentNum = nums2[rIndex];
                    int preNum = nums2[rIndex -1 ];
                    return (double) (currentNum + preNum) /2;

                }else {
                    return nums2[rIndex];
                }
            }
        }

        return  -1;



    }

}
