package Sorting;

/**
 * @Author：zhh
 * @Date：2023/6/16 15:14
 */
public class Test {

    public static void main(String[] args) {
        int[] is = {4,2,1,3,9,7};
        int length = is.length;
        //bubbleSort(is,length);
        //selectSort(is,length);
        //selectSort02(is,length);
        //insertSort(is,length);
        /*int gap = length;
        while ( gap >1 ){
            gap = gap/3;
            if(gap ==0){
                ++gap;
            }
            shellSort(is,length,gap);
        }*/
        quickSort(is,0,length-1);
        for (int i = 0; i <length ; i++) {
            System.out.println(is[i]);
        }

    }

    /**
     * 冒泡排序
     * @param is
     * @param length
     */
    public static void bubbleSort(int[] is ,int length){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <length-1-i ; j++) {
                int num1 = is[j];
                int num2 = is[j+1];
                if(num1<num2){
                    is[j] = num2;
                    is[j+1] = num1;
                }
            }
        }

    }

    /**
     * 选择排序
     * @param is
     * @param length
     */
    public static void selectSort(int[] is,int length){
        for (int i = 0; i < length; i++) {
            //选择交换的下标
            int selectIndex = i;
            //后面的值与下标的值进行比较
            for (int j = i+1; j < length; j++) {
                //满足条件就交换
                if(is[selectIndex] < is[j]){
                    int temp = is[selectIndex];
                    is[selectIndex] = is[j];
                    is[j]= temp;
                }
            }
        }
    }

    /**
     * 选择排序 以下标为基准 减少交换次数
     * @param is
     * @param length
     */
    public static void selectSort02(int[] is,int length){
        for (int i = 0; i < length; i++) {
            //选择交换的下标
            int selectIndex = i;
            //后面的值与下标的值进行比较
            for (int j = i+1; j < length; j++) {
                //满足条件就交换
                if(is[selectIndex] < is[j]){
                    selectIndex=j;
                }
            }
            int temp = is[i];
            is[i] = is[selectIndex];
            is[selectIndex] = temp;
        }
    }

    /**
     * 插入排序   注意得在循环外面插入,不能在循环里面else插入 比如 [2,3,4] 1要插入,遍历完循环也进不了else!!!
     *
     * 1 就相当于希尔排序中的gap==1
     * @param is
     * @param length
     */
    public static void insertSort(int[] is,int length){
        for (int i = 0; i < length-1; i++) {
            //要插入的值
            int insertNum = is[i+1];
            //循环被插入数组,找准位置进行插入
            int index = i;
            while (index>=0){
                if(insertNum>is[index]){
                    //后移
                    is[index+1]= is[index];
                }else {
                    //找到要插入的点了,打破
                    break;
                }
                index --;
            }
            //插入
            is[index+1] = insertNum;
        }
    }

    /**
     * 希尔排序 每组间隔可以看成一个数组,采用插入排序
     */
    public static void shellSort(int[] is,int length,int gap){
        //长度减去间隔,is[i+gap] 就能获取到所有的值
        for (int i = 0; i < length-gap; i++) {
            //要插入的值
            int insertNum = is[i+gap];
            //循环被插入数组,找准位置进行插入
            int index = i;
            while (index>=0){
                if(insertNum>is[index]){
                    is[index+gap] = is[index];
                }else {
                    break;
                }
                index = index-gap;
            }
            //插入
            is[index+gap] = insertNum;
        }
    }

    /**
     * 快速排序 ,以数组第一个数为基准, 分成两个数组,左边的大于基准,右边的小于基准。然后递归两个数组,直到每个数组长度为1
     * @param is
     * @param left
     * @param right
     *
     * 左边为基准,必须右哨兵先走,因为右哨兵符合条件,就和基准或者左哨兵交换,不符合条件,右哨兵会自减1,直至遇到左哨兵
     * 再交换基准,这样就能确保基准左边全部大于/小于基准, 基准右边的全部小于/大于基准(因为左哨兵后走,肯定符合基准的要求)
     *
     */
    public static  void quickSort(int[] is,int left ,int right){
        if (left >= right){
            return;
        }
        //基准
        int reference = is[left];
        //左哨兵
        int leftBigin = left;
        //右哨兵
        int rightBegin = right;
        while (rightBegin!=leftBigin){
            while (rightBegin >leftBigin){
                //比基准大,应该交换,但是还需要等待左哨兵走完
                if(is[rightBegin] > reference){
                    break;
                }
                rightBegin--;
            }
            while (leftBigin<rightBegin){
                //比基准小,应该交换
                if(is[leftBigin] < reference){
                    //左右哨兵交换
                    int leftNum = is[leftBigin];
                    is[leftBigin]=is[rightBegin];
                    is[rightBegin]=leftNum;
                    break;
                }
                leftBigin++;
            }

        }
        //左右哨兵碰头了,就把基准和该下标值交换
        is[left]=is[leftBigin];
        is[leftBigin] = reference;
        //开始递归
        quickSort(is,left,rightBegin-1);
        quickSort(is,rightBegin+1,right);
    }
}
