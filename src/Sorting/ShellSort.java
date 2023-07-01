package Sorting;

/**
 * @Author：zhh
 * @Date：2023/6/8 10:14
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {5,32,1,3,4,1,21,2,45,7};
        int length = arr.length;
        int gap = length;
        while (gap>1){
            gap = gap/5;
            if(gap == 0){
                gap = gap+1;
            }
            ShellSort(arr,length,gap);
        }

        for (int i = 0; i < length; i++) {
            System.out.println(arr[i]);
        }
    }


    public static void  ShellSort(int[] arr, int length, int gap) {
        /**
         * gap间隔,表示每次取后一个值的间隔 . 那么最后一个值的下标+间隔 = 数组长度 才不会越界
         * 所以循环次数为 数组长度-间隔,这样就可以获取到最后一个下标值的后一个值,而不会越界
         *
         * 比如[1,2,3,4,5,6,7,8,9,10] 长度10 间隔10/2,每个间隔为5 ,循环10-5次就可以获取到所有值了
         * 可以将每个间隔的值看成一组[1,6][2,7][3,8][4,9][5,10] 然后每组都可以按照插入排序进行插入
         *
         * 直到gap分不动了,表明之前每个分组已经排序的差不多了,就进行最后一次全部排序
         */
        for (int i = 0; i < length-gap; i++) {
            //lastIndex可以看成当前组的最后一个下标
            int lastIndex = i;
            //nextNum就为插入值,要插入lastIndex的数组
            int nextNum = arr[i+gap];
            //不符合插入条件就循环往后移动
            while (i>=0){
                if(nextNum<arr[i]){
                    //后移
                    arr[i+gap]=arr[i];
                    //指向上一个下标,继续循环判断
                    i = i-gap;
                }else {
                    //不满足插入条件开始打破,为什么不在此处插入,如果恰好下标为0也后移了,循环跳出去了,也没执行
                    //此代码块,就插入不了了,应该等循环完毕,获取下标值再插入
                    break;
                }
            }
            //跳出循环此时i的下标值不满足插入条件,插入i+gap下面即可
            arr[i+gap] = nextNum;
        }


    }

}
