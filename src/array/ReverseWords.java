package array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * @Desc: 反转字符串中的单词
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * <p>
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 * <p>
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 *
 *
 *  这道题主要考察字符串基本操作, 去除首尾和中间的多余空格
 * @Author：zhh
 * @Date：2025/4/23 15:40
 */
public class ReverseWords {

    public static void main(String[] args) {
        String s = "the sky is blue";
        ReverseWords reverseWords = new ReverseWords();
        String s1 = reverseWords.reverseWords(s);
        System.out.println(s1);
    }


    /**
     * 思路: 1. 从前往后排序,去除空格,将单词添加到数组中
     *      2. 从后往前排序单词数组,追加到字符串中,每隔一个元素，添加一个空格
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        ArrayList<String> words = new ArrayList<>();
        int index = 0;
        char pre = ' ';
        while (index < s.length()){
            char c = s.charAt(index);
            if(c != ' ' && pre == ' '){
                //开始单词
                StringBuffer stringBuffer = new StringBuffer();
                while (index < s.length() && s.charAt(index) != ' '){
                    char c1 = s.charAt(index);
                    stringBuffer.append(c1);
                    index++;
                }
                words.add(stringBuffer.toString());
            }else {
                index++;
            }
            pre = s.charAt(index -1);
        }
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = words.size() - 1; i >=0 ; i--) {
            stringBuffer.append(words.get(i));
            if(i != 0){
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 优化: 使用双端队列,而不是ArrayList ,因为arrayList取的时候还需要遍历一次
     * @param s
     * @return
     */
    public String reverseWords02(String s) {
        Deque<String> words = new ArrayDeque<>();
        int index = 0;
        char pre = ' ';
        while (index < s.length()){
            char c = s.charAt(index);
            if(c != ' ' && pre == ' '){
                //开始单词
                StringBuffer stringBuffer = new StringBuffer();
                while (index < s.length() && s.charAt(index) != ' '){
                    char c1 = s.charAt(index);
                    stringBuffer.append(c1);
                    index++;
                }
                words.offerFirst(stringBuffer.toString());
            }else {
                index++;
            }
            pre = s.charAt(index -1);
        }

        return String.join(" ", words);
    }

    /**
     * 去除收尾空格,且使用双端队列
     * @param s
     * @return
     */
    public String reverseWords03(String s) {
        Deque<String> words = new ArrayDeque<>();
        int left = 0;
        int right = s.length()-1;
        //去除首尾空格
        while (left <= right && s.charAt(left) == ' '){
            left++;
        }
        while (left <= right && s.charAt(right) == ' '){
            right--;
        }
        StringBuilder sb = new StringBuilder();
        while (left <= right){
            char c = s.charAt(left);
            if(c == ' ' && sb.length() != 0){
                words.offerFirst(sb.toString());
                sb.setLength(0);
            }else if(c != ' '){
                sb.append(c);
            }
            left ++;
        }
        words.offerFirst(sb.toString());
        return String.join(" ", words);
    }
    /**
     * 难点: 1.去除头尾空格
     *      2.去除中间多余空格
     * @param s
     * @return
     */
    public String trimSpaces(String s) {
        int left = 0;
        int right = s.length()-1;
        //去除首尾空格
        while (left <= right && s.charAt(left) == ' '){
            left++;
        }
        while (left <= right && s.charAt(right) == ' '){
            right--;
        }
        //去除中间空格
        StringBuilder sb = new StringBuilder();
        while (left <= right){
            char c = s.charAt(left);
            if(c != ' '){
                sb.append(c);
            }else if (s.charAt(sb.length()-1) != ' '){
                sb.append(c);
            }
            left++;
        }
        return sb.toString();

    }
}
