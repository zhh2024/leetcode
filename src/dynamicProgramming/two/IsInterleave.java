package dynamicProgramming.two;

/**
 * @Desc: 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 * @Author：zhh
 * @Date：2025/6/10 15:00
 */
public class IsInterleave {
    public static void main(String[] args) {
        IsInterleave isInterleave = new IsInterleave();
        boolean interleave = isInterleave.isInterleave02("aabcc", "dbbca", "aadbbcbcac");
        System.out.println(interleave);
    }

    /**
     * 暴力递归
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public  boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return backtrack(s1, 0, s2, 0, s3, 0);
    }

    private  boolean backtrack(String s1, int i, String s2, int j, String s3, int k) {
        // 全部字符匹配完成
        if (k == s3.length()) {
            return true;
        }

        // 尝试从s1取字符（如果可能）
        boolean fromS1 = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            fromS1 = backtrack(s1, i + 1, s2, j, s3, k + 1);
        }

        // 尝试从s2取字符（如果可能）
        boolean fromS2 = false;
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            fromS2 = backtrack(s1, i, s2, j + 1, s3, k + 1);
        }

        // 返回两种选择的逻辑或
        return fromS1 || fromS2;
    }

    /**
     * 在暴力回溯的过程中,当字符串s1,和字符串s2的下标 都有可能出现 比如1,1的场景，多次回溯每次都要从1,1的场景计算,造成了大量的重复计算
     * 所以可以由底向上记录每个下标对应的状态。因为有两个字符串,所以需要二维数组记录每个字符串的当前下标位置
     * 因为i,j 可以从s1字符串达到,也可以从s2字符串达到 i,j == (i-1 + 1 ,j) == (i,j- 1 +1) 有两种能达到
     * 因为需要记录空串的情况,所以0,0被占用了,故而动态转移方程如下:
     * dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(c) )|| ( dp[i][j-1] && s2.charAt(j-1) == s3.charAt(c) )
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public  boolean isInterleave02(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();
        if (m + n != t) {
            return false;
        }

        // dp[i][j] 表示s1的前i个字符和s2的前j个字符能否交错组成s3的前i+j个字符
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化：两个空字符串可以组成空字符串
        dp[0][0] = true;

        //初始化列
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i- 1][0] && (s1.charAt(i -1) == s3.charAt(i - 1));
        }

        //初始化行
        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i -1] && (s2.charAt(i -1) == s3.charAt(i - 1));
        }
        //s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c = s3.charAt(i + j - 1);
                boolean n1 = dp[i-1][j] && s1.charAt(i-1) == c;
                boolean n2 = dp[i][j-1] && s2.charAt(j-1) == c;
                dp[i][j] = n1 || n2;
            }
        }
        return dp[m][n];
    }

    /**
     * TODO 滚动数组优化
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public  boolean isInterleave03(String s1, String s2, String s3) {
       return false;
    }

}
