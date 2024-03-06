package slideWindow;

import java.util.HashMap;

/**
 * @Desc: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 * @Author：zhh
 * @Date：2024/3/5 16:48
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring02("abcabcbb"));
    }
    public static int lengthOfLongestSubstring(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        int left =0;
        int right =0;
        int maxlen = 0;
        while (right< s.length()){
            char c = s.charAt(right);
            if (map.containsKey(c) && map.get(c)>=left) {
                maxlen = Math.max(right-left,maxlen);
                left++;
            }else {
                map.put(c,right);
                right++;
            }
        }
        maxlen = Math.max(right-left,maxlen);
        return maxlen;

    }

    public static int lengthOfLongestSubstring02(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        int left =0;
        int right =0;
        int maxlen = 0;
        while (right< s.length()){
            char c = s.charAt(right);
            if (map.containsKey(c) ) {
                maxlen = Math.max(right-left,maxlen);
                Integer index = map.get(c);
                while (left <= index) {
                    map.remove(s.charAt(left));
                    left++;
                }
            }
            map.put(c,right);
            right++;
        }
        maxlen = Math.max(right-left,maxlen);
        return maxlen;
    }


}
