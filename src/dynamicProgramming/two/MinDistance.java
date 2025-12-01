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
 * @Author：zhh
 * @Date：2025/6/10 15:18
 */
public class MinDistance {

    public static void main(String[] args) {
        MinDistance minDistance = new MinDistance();
        int i = minDistance.minDistance("horse", "ros");
        System.out.println(i);
    }

    public int minDistance(String word1, String word2) {
        // 基本情况：任一字符串为空
        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word2.isEmpty()) {
            return word1.length();
        }

        // 首字符相同的情况
        if (word1.charAt(0) == word2.charAt(0)) {
            return minDistance(word1.substring(1), word2.substring(1));
        }

        // 计算三种操作的成本
        // 插入 字符串1插入了与字符串2首字符相同的值，因为插入是伪插入,字符串1还是当前首字符, 字符串2进入下一个字符
        int insert = minDistance(word1, word2.substring(1));
        // 删除 字符串1首字母删除了,进入下一个字符
        int delete = minDistance(word1.substring(1), word2);
        // 替换 首字符必然相等了,同步进入下一个字符
        int replace = minDistance(word1.substring(1), word2.substring(1));

        // 返回最小值 + 当前操作成本
        return 1 + Math.min(Math.min(insert, delete), replace);
    }



    public int minDistance02(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // 初始化边界
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // 动态规划填表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 字符匹配
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(insert, delete), replace);
                }
            }
        }
        return dp[m][n];
    }
}
