package DynamicProgramming;

import java.util.HashMap;

/**
 * @Desc: 青蛙跳阶  青蛙每次可以跳1阶 或者 2阶，跳到10阶需要多少次
 * @Author：zhh
 * @Date：2024/4/12 9:44
 */
public class FrogJumps {

    public static void main(String[] args) {
        System.out.println(jump2(10));
    }

    /**
     * 这个函数 f(n) = f(n-1) + f(n-2)  一看就可以使用递归,因为函数里面的操作也是该函数
     *
     * 时间复杂度O(2^n) 指数次增长 ,重复计算了多次f(n)
     *
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
    public static int  jump(int n){
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        //每次只能跳1 或者2阶 , 那么跳到n阶,就是跳到n-1的次数+ (n-2)的次数
        return jump(n-1) + jump(n-2);
    }

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
}
