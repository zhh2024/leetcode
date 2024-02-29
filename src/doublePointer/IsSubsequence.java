package doublePointer;

/**
 * @Desc: 判断是否是子字符串
 * @Author：zhh
 * @Date：2024/1/18 16:59
 */
public class IsSubsequence {

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    public static boolean isSubsequence(String s, String t) {
        int sIndex=0,tIndex=0;
        while (sIndex<s.length() && tIndex<t.length()){
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
            }
            tIndex++;
        }
        return sIndex == s.length();           
    }
}
