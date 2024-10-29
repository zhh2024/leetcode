package hashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Desc: 同构字符串
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * <p>
 * 示例 1:
 * 输入：s = "egg", t = "add"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "foo", t = "bar"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：s = "paper", t = "title"
 * 输出：true
 * @Author：zhh
 * @Date：2024/10/21 18:00
 */
public class IsIsomorphic {
    public static void main(String[] args) {
        IsIsomorphic isIsomorphic = new IsIsomorphic();
        isIsomorphic.isIsomorphic("badc","baba");
    }

    /**
     * 思路: 因为这道题与值无关,符合AABB即可。
     * 将分别将两个字符串相同的字符进行分组，一样的字符下标放到数组中
     * 判断两个map中value 是否一致。
     *
     * 缺点: containsValue 时间复杂度会变高
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, List<Integer>> map1 = new HashMap<>();
        HashMap<Character, List<Integer>> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map1.containsKey(c)){
                List<Integer> integers = map1.get(c);
                integers.add(i);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map1.put(c,list);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if(map2.containsKey(c)){
                List<Integer> integers = map2.get(c);
                integers.add(i);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map2.put(c,list);
            }
        }
        for (List<Integer> value : map1.values()) {
            if (!map2.containsValue(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 思路: 两个字符串双向映射,如果s字符串出现相同的字符,此时t下标的字符是否与value一致。但是单映射无法确认是否符合条件,比如 badc 与baba
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            char c1 = t.charAt(i);
            if( (map1.containsKey(c) && map1.get(c) != c1) || (map2.containsKey(c1) && map2.get(c1) != c )){
                return false;
            }
            map1.put(c,c1);
            map2.put(c1,c);
        }
        return true;
    }
}
