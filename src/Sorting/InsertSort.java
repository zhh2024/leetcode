package Sorting;

/**
 * @Author：zhh
 * @Date：2023/6/7 14:02
 *
 * 插入排序
 *
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
        fromRigthtToLeft(is);
        for (int i = 0; i < is.length; i++) {
            System.out.println(is[i]);
        }
    }



    public static void fromLeftToRight(int[] is){
        for (int i = 1; i < is.length ; i++) {
            int insert = is[i];
            //里循环可以看成排序完的有序数组
            for (int j = 0; j < i; j++) {
                //定位
                if(insert<is[j]){
                    //后移
                    for (int k = i; k > j; k--) {
                        is[k] = is[k-1];
                    }
                    //插入
                    is[j]=insert;
                    break;
                }
            }
        }
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

}
