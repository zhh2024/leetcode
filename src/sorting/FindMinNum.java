package sorting;

/**
 * @Author：zhh
 * @Date：2023/6/7 10:09
 *
 * 数组如何找到最大值/最小值
 *
 * 以此从走往右查找,以此与最小值比较,如果小于最小值 则覆盖
 */
public class FindMinNum {
    public static void main(String[] args) {
        int[] is = {2,5,8,6,7,9,1,4,5,3};
        //以值作为查找
        int min =is[0];
        for (int i = 1; i < is.length; i++) {
            if(min>is[i]){
                min = is [i];
            }
        }
        //以下标作为查找
        int minIndex = 0;
        for (int i = minIndex+1; i < is.length; i++) {
            if(is[minIndex]>is[i]){
                minIndex = i;
            }
        }
        System.out.println(is[minIndex]);
    }
}
