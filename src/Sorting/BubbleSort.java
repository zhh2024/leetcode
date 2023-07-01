package Sorting;

/**
 * @Author：zhh
 * @Date：2023/6/7 9:16
 * 
 * 冒泡排序:
 *
 * 解析思路:
 * 1.从头开始 比较相邻的两个值,将两个中的大值放入右侧(从小到大) 放入左侧(从大到小),以此相邻比较,第一轮就可以
 *   选出最大/最小的值 在数组最右边。
 * 2.再次从头开始,相邻比较交换值,但是上次比较已经选出最大/最小值了,就不需要与上一轮选出的值比较,所以相邻比较次数-1
 *
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] is = {1,5,8,6,7,9,2,4,5,3};

        for (int k = 0; k < is.length; k++) {
            for (int i = 1; i < is.length-k; i++) {
                int num = is[i-1];
                int nextNum = is[i];
                if(num > nextNum){
                    is[i] = num;
                    is[i - 1] = nextNum;
                }
            }
        }

        for (int i = 0; i < is.length; i++) {
            System.out.println(is[i]);
        }
    }
}
