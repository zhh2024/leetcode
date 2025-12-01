package sorting;

import java.util.Arrays;

/**
 * @Author：zhh
 * @Date：2023/6/7 14:02
 *
 * 插入排序
 * 最好情况（数组已完全有序）每次插入新元素时，只需比较一次即可确定位置，无需移动后续元素。时间复杂度为 O(n)。
 * 最坏情况（数组完全逆序）每次插入新元素时，需要比较并移动所有已排序的元素。总比较和移动次数为n(n−1 )/2，时间复杂度为 O(n²)。
 * 平均情况（随机排列的数组）平均每个元素需要与一半已排序序列进行比较和移动。时间复杂度仍为 O(n²)。
 *
 * 总结
 * 最好时间复杂度：O(n)
 * 最坏时间复杂度：O(n²)
 * 平均时间复杂度：O(n²)
 * 插入排序比冒泡和选择排序好, 因为冒泡,选择不管什么情况都需要完整的把数组遍历完毕,都是O(n²)
 *
 *
 * 类似于整理扑克牌，每次将一个元素插入到已经排序好的序列中的适当位置
 * 1.从第一个元素开始，该元素可以认为已经被排序；
 *
 * ​ 2.取出下一个元素，在已经排序的元素序列中从后向前扫描；
 *
 * ​ 3.如果该元素（已排序）大于新元素，将该元素移到下一位置；
 *
 * ​ 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 *
 * ​ 5.将新元素插入到该位置后；
 *
 * ​ 6.重复步骤2~5。

 */
public class InsertSort {
    public static void main(String[] args) {
        int[] is = {1,5,8,6,7,9,2,4,5,3};
        test2(is);
        System.out.println(Arrays.toString(is));
    }

    public static void test2(int[] is) {
        //这个循环，是拿出当前值去插入到已经排好序的数组
        for (int i = 1; i < is.length; i++) {
            int curr = is[i];
            //这个循环，是循环已经排好序的数组,当前值的前一位就是排好序数组的最后一位
            int j = i - 1;
            for (; j >= 0; j--) {
                int number = is[j];
                if(curr < number){
                    is[j+1] = number;
                }else {
                    break;
                }
            }
            //当 curr 需要插入到已排序部分的第一个位置时（即所有已排序元素都比 curr 大），内层循环会执行到 j = -1 退出循环，但此时没有将 curr 插入到正确位置，导致数据丢失。
            is[j+1] = curr;
        }
    }

    /**
     * 优化代码
     * @param nums
     */
    public void insertSort(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            int insertNum = nums[i];
            int j = i - 1;
            //如果已经是有序,此循环根本进不去,达不到nums[j] > insertNum这个条件,所以最好情况是O(n)
            for (; j >= 0 && nums[j] > insertNum; j--) {
                swap(nums,j,j + 1);
            }
            nums[j + 1] = insertNum;
        }
    }


    public void swap (int[] nums, int i , int j){
        nums[i] = nums[i] ^ nums[j];
        //此时i = i ^j   j = i ^ j & j = i
        nums[j] = nums[i] ^ nums[j];
        //此时i = i ^j ,j = i,  i = i ^ j ^ i = j
        nums[i] = nums[i] ^ nums[j];
    }
}
