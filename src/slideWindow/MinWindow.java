package slideWindow;

import java.util.HashMap;
import java.util.Set;

/**
 * @Desc: 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * <p>
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 * 解题思路:
 * 在 s 上滑动窗口，通过移动 r 指针不断扩张窗口。当窗口包含 t 全部所需的字符后，如果能收缩，我们就收缩窗口直到得到最小窗口。
 * 拆分成两个问题:
 * 1. 窗口滑动,找到符合条件的,收缩窗口,收缩后不符合继续滑动,符合继续收缩. 记录每次符合条件的最优值，直至窗口到数组顶端。
 * 2. 窗口内的字符串与t字符串比较是否符合条件,两个字符串比较是否包含。
 * 难度:
 * 每次指针滑动,都需要比较窗口内的字符串与t是否符合条件,才能继续滑动。 时间一般都在这里浪费了。
 *
 * @Author：zhh
 * @Date：2025/3/17 13:45
 */
public class MinWindow {

    public static void main(String[] args) {
        String s = minWindow03("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }

    /**
     * 实现思路:
     * 1.右指针先走，遇到符合条件的停下来
     * 2.左指针再走, 排查是否存在符合条件的,存在继续走左指针
     * 3.左指针排查不符合条件，停下，右指针继续走
     * 4.每次符合条件的都记录下来，进行比较替换
     *
     * 难点？如何比较两个字符串符合条件？
     * 使用map比较,复杂度是O(n) 但会超时总体时间就是O(n^2)
     * @param s
     * @param t
     * @return
     */
    public static String minWindow01(String s, String t) {
        int l = 0,r = 0 ;
        String minWindow = "";
        int minWindowLen = Integer.MAX_VALUE;
        while (r <= s.length() -1){
            String str = s.substring(l, r+1);
            int strLen = r - l + 1;
            if (!contains(str,t)) {
                r++;
            }else {
                if(minWindowLen > strLen){
                    minWindow = str;
                    minWindowLen = strLen;
                }
                while (l <= r){
                    String str2 = s.substring(l, r+1);
                    int strLen2 = r - l + 1;
                    if (!contains(str2,t)) {
                        break;
                    }else {
                        if(minWindowLen > strLen2){
                            minWindow = str2;
                            minWindowLen = strLen2;
                        }
                        l++;
                    }
                }
            }
        }
        return minWindow;
    }

    /**
     * 思路不变的基础上, 在minWindow01做代码简化
     * @param s
     * @param t
     * @return
     */
    public static String minWindow02(String s, String t) {
        int l = 0,r = -1 ;
        String minWindow = "";
        int minWindowLen = Integer.MAX_VALUE;
        while (r < s.length() -1 ){
            r++;
            String str = s.substring(l, r+1);
            while (contains(str,t) && l <= r){
                int strLen2 = r - l + 1;
                if(minWindowLen > strLen2){
                    minWindow = str;
                    minWindowLen = strLen2;
                }
                l++;
                str = s.substring(l, r+1);
            }
        }
        return minWindow;
    }


    public static boolean contains(String s, String t){
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)){
                Integer i1 = map.get(c);
                map.put(c,++i1);
            }else {
                map.put(c,1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            Integer number = map.get(c);
            if (number == 0) {
               return false;
            }
            map.put(c,--number);
        }
        return true;
    }


    /**
     * 优化minWindow02
     * 优化点1: 将t字符串循环抽出来,只初始化遍历存入map
     * 优化点2: 窗口内的字符如果存在t中则存入map2,减少map2大小,并且比较的时候时间复杂度更低
     * 优化点3: substring从while抽出来,只记录最小的边界,最后再substring
     *
     * * @param s
     * @param t
     * @return
     */
    public static String minWindow03(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        int l = 0,r = -1;
        int minWindowLen = Integer.MAX_VALUE;
        int minL = l;
        int minR = r;
        while (r < s.length() -1){
            r++;
            char c = s.charAt(r);
            //字符不存在t字符串中的跳过,没必要比较。
            if (map.containsKey(c)) {
                map2.put(c, map2.getOrDefault(c, 0) + 1);
            }
            while (check(map,map2) && l <= r){
                int strLen = r - l + 1;
                if(minWindowLen > strLen){
                    minL = l;
                    minR = r;
                    minWindowLen = strLen;
                }
                char c1 = s.charAt(l);
                map2.put(c1, map2.getOrDefault(c1,0) - 1);
                l++;
            }
        }
        return r == -1 ? "" : s.substring(minL,minR+1);
    }

    public static  boolean check(HashMap<Character, Integer> map , HashMap<Character, Integer> map2){
        Set<Character> characters = map.keySet();
        for (Character c:characters){
            if(map2.getOrDefault(c, 0) < map.get(c)){
                return false;
            }
        }
        return true;
    }
}
