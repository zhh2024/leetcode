package dynamicProgramming.one;

import java.util.LinkedList;

/**
 * @Desc: 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号 子串 的长度。
 * 左右括号匹配，即每个左括号都有对应的右括号将其闭合的字符串是格式正确的，比如 "(()())"。
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * @Author：zhh
 * @Date：2025/12/21 18:20
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        int i = longestValidParentheses.longestValidParentheses(")(((((()())()()))()(()))(");
        System.out.println(i);

    }

    /**
     * 利用二维数组记录了每个子序列是否是有效扩号子串，时间复杂度O(n^3)
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int maxLength = 0;
        //初始化窗口为2的情况
        for (int i = 0; i < length - 1; i++) {
            boolean b = s.charAt(i) == '(' && s.charAt(i + 1) == ')';
            if(b){
                maxLength = 2;
            }
            dp[i][i+1] = b;
        }
        //基于2的情况,依次记录窗口为4,6,8...的情况
        for (int i = 4; i <= s.length() ; i+=2) {
            int left = 0;
            int right = left + i - 1;
            while (right <= s.length() -1){
                char lc = s.charAt(left);
                char rc = s.charAt(right);
                if(lc == '(' && rc == ')' ){
                    boolean flag = false;
                    for (int j = 1; j <= right - left; j+=2) {
                        if (dp[left][left + j] && dp[left + j + 1][right]) {
                            flag = true;
                            break;
                        }
                    }
                    dp[left][right] = flag || dp[left+1][right -1];
                    if(dp[left][right]){
                        maxLength = right - left + 1;
                    }
                }
                left  ++;
                right ++;
            }
        }
        return maxLength;
    }

    /**
     * 要求是最长长度,可以转成一维dp, 判断当前下标是否是有效括号，再加上之前的就是当前下标最佳长度
     * 只需要判断当前下标值是')'即可，因为'('必然形成不了有效括号
     */
    public int longestValidParentheses02(String s) {
        int length = s.length();
        int max = 0;
        int[] dp = new int[length];
        for (int i = 1; i < length; i++) {
            char current = s.charAt(i);
            if(current == ')'){
                char pre = s.charAt(i - 1);
                if(pre == '('){
                    dp[i] = (i -2 >=0 ? dp[i-2] :0) + 2;
                }else if (i- dp[i-1] -1>=0 &&  s.charAt(i- dp[i-1] -1) == '(' ){
                    dp[i] = dp[i-1] + 2 + (i- dp[i-1] -2 >= 0 ? dp[i- dp[i-1] -2] :0);
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    /**
     * 括号匹配问题,应该立马想到用栈的数据结构来解决问题，消除已匹配的括号，并记录最佳长度
     *
     * 难点在于计算长度,应该计算最近一个没法匹配')'括号的下标，当前下标与最近一个')'括号的下标差值，就是当前长度
     * 如果不存在第一个)括号，应该记为-1,这样的话才可以记录 '()()()'场景的最佳长度,然后更新最近一个没法匹配')'括号的下标
     *
     */
    public int longestValidParentheses03(String s) {
        int length = s.length();
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if(c == '('){
                stack.push(i);
            }else {
                stack.pop();
                if (!stack.isEmpty()) {
                   max = Math.max(i - stack.peek(),max);
                }else {
                   stack.push(i);
                }
            }
        }
        return max;
    }
}
