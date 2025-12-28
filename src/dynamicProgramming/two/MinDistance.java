package dynamicProgramming.two;

/**
 * @Desc: 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 *
 * 插入 horse -> ro   dp[i][j] = dp[i][j-1] + 1
 * 删除 hors  -> ros  dp[i][j] = dp[i-1][j] +1
 * 替换 hors -> ro    dp[i][j] = dp[i-1][i-j]
 *
 *
 *
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 *
 * 思路: 动态规划，难点是当前最优子结构结果依赖于四种(增，删，改，不变)可能发生的情况，然后取最小值就是当前最优子结构。
 * @Author：zhh
 * @Date：2025/6/10 15:18
 */
public class MinDistance {

    public static void main(String[] args) {
        MinDistance minDistance = new MinDistance();
        int i = minDistance.minDistance02("", "a");
        System.out.println(i);
    }

    /**
     * 最后一步操作分析 这是DP设计的核心思维方式。我们逆向思考：在最优的转换序列中，最后一步操作是什么？
     * 只有三种可能：
     * 删除：删掉 word1 的最后一个字符
     * 插入：插入 word2 的最后一个字符
     * 替换：把 word1 的最后一个字符换成 word2 的最后一个字符（或不变）
     * 具体推理：
     * 设 word1 有 i 个字符，word2 有 j 个字符。
     * 情况1：最后一步是删除
     * 先让 word1[0..i-2] 变成 word2[0..j-1]（代价：D[i-1][j]）
     * 再删除 word1[i-1]（代价：+1）
     * 总代价：D[i-1][j] + 1
     * <p>
     * 情况2：最后一步是插入
     * 先让 word1[0..i-1] 变成 word2[0..j-2]（代价：D[i][j-1]）
     * 再插入 word2[j-1]（代价：+1）
     * 总代价：D[i][j-1] + 1
     * <p>
     * 情况3：最后一步是替换或不操作
     * 先让 word1[0..i-2] 变成 word2[0..j-2]（代价：D[i-1][j-1]）
     * 如果 word1[i-1] == word2[j-1]：不需要额外操作（代价：+0）
     * 如果 word1[i-1] != word2[j-1]：替换最后一个字符（代价：+1）
     * 总代价：D[i-1][j-1] + cost，其中 cost = 0/1

     * 示例 1：
     * 输入：word1 = "horse", word2 = "ros"
     * 最后一步的操作有下面三种可能情况
     * 插入 horse -> ro   dp[i][j] = dp[i][j-1] + 1
     * 删除 hors  -> ros  dp[i][j] = dp[i-1][j] +1
     * 替换 hors -> ro    dp[i][j] = dp[i-1][i-j]
     */
    public int minDistance02(String word1, String word2) {
        int w1l = word1.length();
        int w2l = word2.length();
        int[][] dp = new int[w1l + 1][w2l +1 ];
        //初始化，解决边界问题，代表word1 没有字符的情况，达到word2 需要多少次取决于word2长度
        for (int i = 0; i <= w2l; i++) {
            dp[0][i] = i;
        }
        //初始化，解决边界问题，代表word1 ，达到word2空字符串 需要多少次取决于word1长度
        for (int i = 0; i <= w1l; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= w1l; i++) {
            for (int j = 1; j <= w2l; j++) {
                int insert = dp[i][j - 1] + 1;
                int del = dp[i - 1][j] + 1;
                int update = dp[i - 1][j - 1];
                //更新不一样的点在于前面的都匹配了，看当前字符是否需要更新了。
                if(word1.charAt(i-1) != word2.charAt(j-1)){
                    update = update+1;
                }
                dp[i][j] = Math.min(update,Math.min(insert,del));
            }
        }
        return dp[w1l][w2l];
    }





}
