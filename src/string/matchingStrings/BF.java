package string.matchingStrings;

/**
 * @Desc: BF算法匹配字符串
 * @Author：zhh
 * @Date：2024/7/16 18:11
 */
public class BF {

    public static void main(String[] args) {
        BF bf = new BF();
        int bf1 = bf.bf("mississippi", "issip");
        System.out.println(bf1);
    }


    public int bf(String haystack, String needle){
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
}
