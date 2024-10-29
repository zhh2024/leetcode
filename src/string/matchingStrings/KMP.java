package string.matchingStrings;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/7/16 18:11
 */
public class KMP {
    public static void main(String[] args) {
        KMP kmp = new KMP();
        //                   [0, 0, 0, 1, 2, 3, 2]  [0, 0, 0, 0, 1, 2, 3]
        int[] ints = kmp.initNext("abcabcb");
        kmp.strStr("mississippi","pi");
    }
    public int strStr(String haystack, String needle){
        int[] ops = initNext(needle);
        int hl = haystack.length();
        int nl = needle.length();
        int h = 0;
        int n = 0;
        while (h < hl && n < nl){
            char ch = haystack.charAt(h);
            char cn = needle.charAt(n);
            if(ch == cn){
                h++;
                n++;
            }else {
                //遇到不相同的
                int op = ops[n];
                while (op > 0 && needle.charAt(op) != haystack.charAt(h)) {
                    op = ops[op];
                }
                if(needle.charAt(op) == haystack.charAt(h) ){
                    n = op;
                }else {
                    h++;
                    n =0;
                }
            }
        }
        if(n == nl ){
            return h - n;
        }else {
            return -1;
        }

    }

    public int[] initNext(String str){
        int[] next = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            char ic = str.charAt(i-1);
            int j = i-1;
            while ( j > 0 ){
                char jc = str.charAt(next[j]);
                if(jc == ic){
                    next[i] = next[j]+1;
                    break;
                }else {
                    j = next[j];
                }
            }
            if(j==0){
                next[i] = 0;
            }
        }
        return next;
    }
}
