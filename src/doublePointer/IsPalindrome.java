package doublePointer;

/**
 * @Desc:如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 * @Author：zhh
 * @Date：2023/12/13 15:11
 */
public class IsPalindrome {

    public static void main(String[] args) {
        String s = "00";
        System.out.println(isPalindrome(s));
    }

    /**
     * 思路: 回文串就是去掉非数组和字母 ，是对称的。那么 左和右一一相等，那么就可以设置左右指针,遇到非数组和字母跳过，进行比较
     * 如果发生不相同就是false
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        int left=0,right=s.length()-1;
        while (left<right){
            char lc = s.charAt(left);
            //非数字字母跳过
            if(!Character.isLetterOrDigit(lc)) {
                left++;
                continue;
            }
            char rc = s.charAt(right);
            //非数字字母跳过
            if(!Character.isLetterOrDigit(rc)) {
                right--;
                continue;
            }
            //字符判断
            if (Character.toLowerCase(lc) != Character.toLowerCase(rc)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
