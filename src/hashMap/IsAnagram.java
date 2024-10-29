package hashMap;

import java.util.HashMap;

/**
 * @Desc: 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * @Author：zhh
 * @Date：2024/10/22 11:09
 */
public class IsAnagram {

    public static void main(String[] args) {
        IsAnagram isAnagram = new IsAnagram();
        isAnagram.isAnagram("ab","a");

    }

    /**
     * 思路: 使用hashMap,遍历s字符串存储s字符串每个字符出现的次数, 再遍历t字符串去校验hashMap是否存在key,如果存在-1且次数>0就符合。
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int length = s.length();
        int length1 = t.length();
        if(length != length1){
            return false;
        }
        HashMap<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if(map1.containsKey(c)){
                map1.put(c,map1.get(c) + 1);
            } else {
                map1.put(c,1);
            }
        }
        for (int i = 0; i < length; i++) {
            char c = t.charAt(i);
            if(!map1.containsKey(c)){
                return false;
            }
            if (map1.get(c) -1 < 0) {
                return false;
            }
            map1.put(c,map1.get(c) -1);
        }
        return true;
    }

    /**
     * 优化思路: 不使用hashMap。使用数组, ASCII码中每个字符对应的数字看作数组的小标位置,初始是0,符合位置就+1.
     * 再遍历t字符串去校验数组下标值是否>0,如果符合就-1。不符合就返回false
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram02(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] ints = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            ints[index] = ints[index] + 1;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            if (ints[index] == 0) {
                return false;
            }
            ints[index] = ints[index] - 1;
        }
        return true;
    }

}
