package dynamicProgramming.two;

/**
 * @Desc: 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * @Author：zhh
 * @Date：2025/6/9 17:04
 */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String s = longestPalindrome.longestPalindrome02("babad");
        System.out.println(s);
    }


    /**
     * 思路: 暴力解法,需要对n^2的子串进行判断是否为回文,每判断一次需要O(n)复杂度,是否可以将判断回文的复杂度减少到O(1)
     * 1. 较短子串几乎都被更长的子串包含,每次计算都是重复的,可以使用二维数组记录出现过的子串,已避免重复计算
     * 2. 初始化填充子串长度为1,2的场景,因为长度<3 ,判断回文串的方式不一样
     * 3. 循环填充子串长度3以上的场景,只需要判断两边值是否一致和内部是否为回文串即可
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        int maxi = 0;
        int maxj = 0;
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                maxi = i;
                maxj = i+1;
            }
        }
        for (int i = 2; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                if (s.charAt(j) == s.charAt(j + i) && dp[j+1][j + i-1]) {
                    dp[j][j + i] = true;
                    maxi = j;
                    maxj = j+i;
                }
            }
        }
        return s.substring(maxi,maxj +1);
    }


    /**
     * 注意 这种循环不可行的
     * 比如 babad ,当循环到第二次 ,下标到 aba的时候 ,此时b还没有被循环到,无法进行dp[i+1][j -1]判断
     * 因为循环是从i开始的,计算的是i-j之间的所有子串,而方程需要i+1的结果,此时还未进入下一次循环,所以无法使用这种循环
     * 应该选用上面的那种循环,一一,二二,三三,四四这种,这样当五五的时候,自然能获取到四四的值
     * @param s
     * @return
     */
    public String longestPalindrome02(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int maxi = 0;
        int maxj = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if(i == j){
                    dp[j][j] = true;
                }else if(j == i+1 && s.charAt(i) == s.charAt(j)){
                    maxi = i;
                    maxj = j;
                    dp[i][j] = true;
                }else if (s.charAt(i) == s.charAt(j) && dp[i+1][j -1]){
                    maxi = i;
                    maxj = j;
                    dp[i][j] = true;
                }
            }
        }
        return s.substring(maxi,maxj +1);
    }
}
