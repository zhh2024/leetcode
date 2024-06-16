package dynamicProgramming;

import java.util.HashMap;

/**
 * @Desc: 青蛙跳阶  青蛙每次可以跳1阶或者2阶,跳到10阶有多少种跳法
 * @Author：zhh
 * @Date：2024/4/12 9:44
 */
public class FrogJumps {

    public static void main(String[] args) {
        System.out.println(jump4(10));
    }

    /**
     * 这个函数 f(n) = f(n-1) + f(n-2)  一看就可以使用递归,因为函数里面的操作也是该函数
     *
     * 时间复杂度O(2^n) 指数次增长 ,重复计算了多次f(n)
     *青蛙每次可以跳1阶或者2阶,跳到10阶有多少种跳法````````````````
     * 比如f(10) = f(9) + f(8)
     *
     * f(9) = f(8) +f(7)
     *
     * f(8) = f(7) +f(6)
     *
     * f(7) = f(6) +f(5)
     *
     * .... 直至左侧退出开始计算右侧
     *
     * @param n
     * @return
     */
    public static int jump(int n){
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        //每次只能跳1 或者2阶 , 那么跳到n阶,就是跳到n-1的次数+ (n-2)的次数
        return jump(n-1) + jump(n-2);
    }

    /**
     * 自顶向下的备忘录法
     */
    static HashMap<Integer,Integer> map = new HashMap<>();
    public static int jump2(int n){
        if(map.containsKey(n)){
            return map.get(n);
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int jump = jump(n - 1)+jump(n - 2);
        map.put(n,jump);
        return map.get(n);
    }

    /**
     * 自底向上法 时间复杂度O(n),空间复杂度O(n)
     * @param n
     * @return
     */
    public static int jump3(int n){
        Integer[] jump = new Integer[n];
        jump[0] = 1;
        jump[1] = 2;
        for (int i = 2; i < jump.length; i++) {
            jump[i]=jump[i-1] + jump[i-2];
        }
        return jump[jump.length-1];
    }

    /**
     * 自底向上法并压缩空间 时间复杂度O(n),空间复杂度O(1)
     * @param n
     * @return
     */
    public static int jump4(int n){
        int index_n_2 = 1;
        int index_n_1 = 2;
        int index_n = 0;
        for (int i = 2; i < n; i++) {
            index_n = index_n_1+index_n_2;
            index_n_2 = index_n_1;
            index_n_1 = index_n;
        }
        return index_n;
    }
}
