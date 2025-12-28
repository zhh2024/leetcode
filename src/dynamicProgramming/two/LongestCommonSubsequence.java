package dynamicProgramming.two;

/**
 * @Desc: 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * @Author：zhh
 * @Date：2025/12/24 16:21
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
    }

    /**
     * 最长公共子序列，依旧是从前到后的顺序，必然存在子结构问题，后面的字符依赖于前面的字符
     * 但因为是双字符串，所以需要二维数组，记录两个字符的当前状态
     * 所以动态转移方程就是 if(t1c == t2c){
     *                     dp[i][j] = dp[i-1][j-1] + 1;
     *                 }else {
     *                     dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
     *                 }
     * 当两个字符串当前下标的字符不相等的情况，取值应该包含当前下标字符。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int t1l = text1.length();
        int t2l = text2.length();
        int[][] dp = new int[t1l + 1][t2l + 1];
        for (int i = 1; i <= t1l; i++) {
            char t1c = text1.charAt(i-1);
            for (int j = 1; j <= t2l; j++) {
                char t2c = text2.charAt(j-1);
                if(t1c == t2c){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[t1l][t2l];
    }

}
