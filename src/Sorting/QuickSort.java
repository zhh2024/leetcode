package Sorting;

/**
 * @Author：zhh
 * @Date：2023/6/9 10:26
 *
 * 快速排序 ,以数组第一个数为基准, 分成两个数组,左边的大于基准,右边的小于基准。然后递归两个数组,直到每个数组长度为1
 *
 * 左边为基准,必须右哨兵先走,因为右哨兵符合条件,就停下等待交换,不符合条件,右哨兵会自减1,直至遇到左哨兵
 * 再交换基准,这样就能确保基准左边全部大于/小于基准, 基准右边全部小于/大于基准(因为左哨兵后走,肯定符合基准的要求)
 *
 * 重点:
 * 左边为基准,右先走 右不符合条件会自减1遇到左哨兵,左哨兵一定符合要求可以交换基准!!!
 * 右边为基准,左先走 左不符合条件会自增1遇到右哨兵,右哨兵一定符合要求可以交换基准!!!
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1,6,3,4,5};
        int length = arr.length;
        quickSort(arr,0,length-1);
        for (int i = 0; i < length; i++) {
            System.out.println(arr[i]);
        }
    }


    /**
     * [5,32,1,3,4,1,21,2,45,7]   5,2,1,3,4,1,21,32,45,7| 1,6,3,4,5
     *
     * 注意: rightBegin > leftBegin 不能写成 rightBegin >= leftBegin ,因为判断完毕后 rightBegin还会++,
     *      leftBegin还会-- ,就会陷入死循环,因为一直不相等
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort( int[] arr,int l,int r){
        if (l >= r){
            return;
        }
        //基准
        int reference = arr[l];
        //左哨兵
        int leftBegin = l;
        //右哨兵
        int rightBegin = r;
        while (rightBegin!=leftBegin){
            //定位右哨兵
            for (; rightBegin > leftBegin ; rightBegin--) {
                if(arr[rightBegin] < reference){
                    break;
                }
            }
            //定位左哨兵
            for (; leftBegin < rightBegin; leftBegin++) {
                if(arr[leftBegin] > reference ){
                    break;
                }
            }
            //左哨兵和右哨兵没有相遇,彼此互换
            int temp = arr[rightBegin];
            arr[rightBegin] = arr[leftBegin];
            arr[leftBegin] = temp;
        }
        //左哨兵i和右哨兵相遇了,基准交换
        int temp = arr[rightBegin];
        arr[rightBegin] = reference;
        arr[l] = temp;
        //开始递归
        quickSort(arr,l,rightBegin-1);
        quickSort(arr,rightBegin+1,r);
    }
}
