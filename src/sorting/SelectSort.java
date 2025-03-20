package sorting;

/**
 * @Author：zhh
 * @Date：2023/6/7 9:49
 *
 * 选择排序
 *
 * 选择排序算法的原理如下：
 *
 * 1.首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 *
 * 2.再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *
 * 3.重复第二步，直到所有元素均排序完毕。
 *
 * 先去看看怎么获取数组中的最小值/最大值
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] is = {1,5,8,6,7,9,2,4,5,3};

        for (int i = 0; i < is.length; i++) {
            int minNum = is[i];
            for (int j = i+1; j < is.length; j++) {
                int nextNum = is[j];
                if(minNum >nextNum){
                    is[i] = nextNum;
                    is[j] = minNum;
                    minNum=nextNum;
                }
            }
        }

        /**
         * 第二种可以减少交换次数
         */
        for (int i = 0; i < is.length; i++) {
            int min = i;
            for (int j = i+1; j < is.length; j++) {
                int minNum = is[min];
                int nextNum = is[j];
                if(minNum >nextNum){
                    min = j;
                }
            }
            int temp = is[i];
            is[i] = is[min];
            is[min] = temp;
        }

        for (int i = 0; i < is.length; i++) {
            System.out.println(is[i]);
        }
    }
}
