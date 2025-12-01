package dynamicProgramming.one;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Desc: 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 * 注意，你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * @Author：zhh
 * @Date：2025/6/4 15:08
 */
public class WordBreak {
    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        boolean b = wordBreak.wordBreak("leetCode", Arrays.asList("leet", "Code"));
        System.out.println(b);
    }


    /**
     * 用dp[i]表示前i个字符能否匹配wordDict,当前下标能为true的条件如下:
     * 动态转移方程为 dp[i]= dp[j] && check(s[j..i])
     * 对于边界条件，定义 dp[0]=true 表示空串且合法
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     *
     * 优化:
     * 利用单词最大长度和最小长度,定位内部循环的可退出的条件,时间复杂度最坏是O(n^2)
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak02(String s, List<String> wordDict) {
        HashSet<String> sets = new HashSet<>();
        int minLen = Integer.MAX_VALUE;
        int maxLen = Integer.MIN_VALUE;
        for (String str : wordDict) {
            minLen = Math.min(minLen, str.length());
            maxLen = Math.max(maxLen, str.length());
            sets.add(str);
        }
        boolean[] dp = new boolean[s.length() + 1];
        //因为得从每个true下标后截取到,从头开始也算,所以需要定义一开始的下标为true
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            if(i < minLen){
                dp[i] = false;
                continue;
            }
            int cur = Math.max(0,i - maxLen );
            while (cur < i ){
                if (dp[cur] && sets.contains(s.substring(cur,i))) {
                    dp[i] = true;
                    break;
                }
                cur ++;
            }

        }
        return dp[s.length()];
    }


}
