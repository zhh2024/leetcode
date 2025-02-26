package Sorting;

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
        /*fromRigthtToLeft(is);
        for (int i = 0; i < is.length; i++) {
            System.out.println(is[i]);
        }*/
        test2();
    }


    public static void fromRigthtToLeft(int[] is){
        for (int i = 1; i < is.length; i++) {
            int insert = is[i];
            //i看成新数组长度,j看成新数组长度-1的最大下标
            int j = i-1;
            for ( ; j >=0 ; j--) {
                if(is[j] > insert ){
                    //向右移动
                    is[j+1] = is[j];
                }else {
                    break;
                }
            }
            //插入
            is[j+1] = insert;
        }
    }





    public static void test2() {
        int[] is = {5,3,6,2,7,1};
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
        for (int i = 0; i < is.length; i++) {
            System.out.println(is[i]);
        }
    }


}
