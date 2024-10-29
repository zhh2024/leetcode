package hashMap;

import java.util.HashSet;

/**
 * @Desc: 快乐数(重点问题!!!)
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false
 * <p>
 * 示例 1：
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * 示例 2：
 * 输入：n = 2
 * 输出：false
 * @Author：zhh
 * @Date：2024/10/22 17:22
 */
public class IsHappy {
    public static void main(String[] args) {
        IsHappy isHappy = new IsHappy();
        isHappy.isHappy(19);

    }

    /**
     * 方法1:思路
     * 有三种情况:
     * 1. 最终会得到 1。
     * 2. 最终会进入循环。
     * 3. 值会越来越大，最后接近无穷大
     *
     * 1. 先拆分整数, 先取10的余数,可以得到最后一位,然后原整数除10,就可以排除掉最后一位。继续循环求最后一位,直至整数为0。在循环过程中会以此累加,就可以得到每个位置上的数字的平方和。
     * 2. 将求得的平方和存入set中,循环求下一个平方和，判断求得的结果是否存在set中,存在就代表有环，退出
     * 3. 没有环，整数每个位置上的数字平方和是否可以无限增大，出现一直循环增大, 显然是不可能的,因为 平方和是加法，每个位置最大只能是9,9的平方是81。
     *    假设n是  999 -> 81 + 81 + 81 = 243 最大是243. 243又回退了,不会超过999，所以是有范围的，就符合程序表达。
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)){
            set.add(n);
            n = sum(n);
        }
        return n == 1;
    }

    public int sum(int n){
        int sum =0;
        while ( n > 0){
            int i1 = n % 10;
            n = n/10;
            sum = sum + i1 * i1;
        }
        return sum;
    }

    /**
     * 既然排除了可以无限增大的可能, 那么只存在两个情况
     * 形成一条链表,终点是1
     * 形成一条环,始终循环
     * 变成问题就是,是否判断一个链表是否有环。就可以使用解决方案 快慢指针乌龟赛跑是否相遇的思路
     * @param n
     * @return
     */
    public boolean isHappy02(int n) {
        int lowNumer = n ;
        int fastNumber = sum(n);
        while ( fastNumber!= 1 &&  lowNumer != fastNumber){
            lowNumer = sum(lowNumer);
            fastNumber = sum(sum(fastNumber));
        }
        return fastNumber == 1;
    }
}
