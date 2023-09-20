package string;

/**
 * @Author：zhh
 * @Date：2023/9/8 22:39
 * 最后一个单词的长度
 */
public class LastWordLength {
    public static void main(String[] args) {
        String s ="s ";
        System.out.println(lengthOfLastWord(s));
    }

    /**
     * 思路:
     * 1. 从后往前走,直到走到第一个字母处,记录下标 (因为可能会有 ' ')
     * 2. 从该下标继续开始从后往前走,走到第一个' '值,记录走的次数,就是最后一个单词的长度
     *
     * 难点:没想到先走到最后一个字母处,因为字符串中肯定是有字母的
     * 时间复杂度O(n) 空间复杂度O(1)
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        int start = s.length() - 1;
        while (start>=0 && s.charAt(start) == 32){
            start--;
        }
        int count =0;
        while (start>=0 && s.charAt(start) != 32){
            count++;
            start--;
        }
        return count;
    }
}
