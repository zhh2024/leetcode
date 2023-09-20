package string;

/**
 * @Author：zhh
 * @Date：2023/9/8 21:55
 *
 * 找出字符串中第一个匹配项的下标
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle
 * 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 */
public class FirstMatchSubscript {

    public static void main(String[] args) {
        String haystack ="hello";
        String needle ="ll";
        System.out.println(findFirstIndex(haystack,needle));
    }

    /**
     *
     * 思路:
     * 1.因为字符串是连续的从前往后,所以只要有一个不符合就开始新的。
     * 2.从第一个开始遍历,构成子字符串 。能构成子字符串个数为m-n, 所以循环次数就是 m-n 。
     * 3.循环要比较的字符串, 子字符串与要比较的字符串一个一个比,存在返回下标,不存在继续遍历。
     * 问题点,子字符串如何构建？ 那就在第一次下标的基础上+循环比较的字符串下标不就是了嘛,这样不就一一对应了
     *
     * 难点: 没想到子字符串与比较的字符串怎么一一对应比较
     * 时间复杂度O(m-n)*O(n) 约等于 O(mn) ,空间复杂度O(1)
     * @param haystack
     * @param needle
     * @return
     */
    public static int  findFirstIndex(String haystack,String needle ){
        int m = haystack.length();
        int n = needle.length();
        for (int i = 0; i <= m-n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    flag = false;
                }
            }
            if(flag){
                return i;
            }
        }
        return -1;
    }


}
