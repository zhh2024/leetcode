package sorting;

/**
 * @Author：zhh
 * @Date：2023/6/7 14:21
 *
 * 如果改变插入顺序,只需要改变< >
 *
 * 数组不能往左移,左有边界is[0],右默认范围足够大,所以只能往右移留出空隙来插入
 *
 * 两种思路:
 *
 * 1.从左往右
 *   1.1因为不能左移,所以从左往右遍历判断大小,只能先定位
 *   1.2定了位之后,定位后面的值再右移
 *   1.3插入空隙
 *
 * 2.从右往左
 *   2.1因为可以右移,所以从右往左遍历判断大小,不符合就可以右移
 *   2.3符合定位,直接插入

 * 插入数组  不能从前移动,这样的话一直都是第一次移动的值
 *                 for (int j = i; j<length ;j++) {
 *                     is[j+1] = is[j] ;
 *                 }  后面的值都会是is[j] 这种思路是错误的！！！
 *
 * 应该从后移动 ,这样的话就不会被覆盖了
 *                 for (int j = length; j >=i ; j--) {
 *                     is[j] = is[j-1] ;
 *                 }
 *
 */
public class InsertArray {
    public static void main(String[] args) {
        int insert = 17;
        //0相当于占位符 所以原数组长度要-1
        int[] is = {2,32,21,4,5,6,7,8,9,0};
        int length = is.length - 1;

        //fromLeftToRight(is,length,insert);
        fromRightToLeft(is,length,insert);
        for (int i = 0; i < is.length; i++) {
            System.out.println(is[i]);
        }
    }


    private static void fromLeftToRight(int[] is ,int length,int insert){
         for (int i = 0; i < length; i++) {
            //1.定位
            if(insert < is[i]){
                //2.后移
                for (int j = length-1; j >= i ; j--) {
                    is[j+1] = is[j] ;
                }
                //3.插入
                is[i] = insert;
                break;
            }
        }

        /*
        错误的思想！！！

        for (int i = 0; i < length; i++) {
            if(insert < is[i]){
                //后移
                for (int j = i; j<length ;j++) {
                    is[j+1] = is[j] ;
                }
                //插入
                is[i] = insert;
                break;
            }
        }*/
    }


    private static void fromRightToLeft(int[] is ,int length,int insert){
        /**
         * 这种有个问题,最后循环i=0,如果下标为0的不符合条件右移动了,但是不会插入
         * 有缺陷
         */
        /*for (int i=length-1 ; i >= 0; i--) {
            if(is[i] > insert){
                //后移
                is[i+1] = is[i] ;
            }else{
                is[i+1] = insert;
                break;
            }
        }*/

        /**
         * 优化下标为0不符合,再进入循环i-- 变成-1 跳出循环
         * 再赋值给下标0
         */
        int i = length-1;
        for ( ; i >= 0; i--) {
            if(is[i] > insert){
                //后移
                is[i+1] = is[i] ;
            }else{
                //定位到下标打破
                break;
            }
        }
        is[i+1] = insert;


        /**
         * whlie实现
         */
        /*int j =length-1;
        while((j>=0) && (is[j]>insert)) {
            is[j+1] = is[j];
            j--;
        }
        is[j+1] = insert;*/
    }

}
