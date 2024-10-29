package hashMap;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Desc: 单词规律
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 * 示例1:
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:pattern = "abba", s = "dog cat cat fish"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: pattern = "aaaa", s = "dog cat cat dog"
 * 输出: false
 * @Author：zhh
 * @Date：2024/10/22 9:55
 */
public class WordPattern {
    public static void main(String[] args) {
        WordPattern wordPattern = new WordPattern();
        //boolean b = wordPattern.wordPattern("abba", "dog dog dog dog");
        //System.out.println(b);
        wordPattern.testStr("one two three dog");
    }

    /**
     * 思路: 双射
     * pattern字符 映射 s字符串 ,但是单向映射并不能实现， s字符串再映射pattern字符，这样双向映射才能确定是否符合规律
     * 比如: a -> dog 这样单映射不可以，b-> dog也可以不符合规律 。 每个字符对应唯一的字符串才可以 a -> dog ,dog -> a 双向映射
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> map1 = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();
        String[] s1 = s.split(" ");
        int length = pattern.length();
        if(length != s1.length){
            return false;
        }
        for (int i = 0; i < length; i++) {
            char c = pattern.charAt(i);
            if( (map1.containsKey(c) && !Objects.equals(map1.get(c), s1[i]))
                || (map2.containsKey(s1[i]) && map2.get(s1[i]) != c) ) {
                return false;
            }
            map1.put(c,s1[i]);
            map2.put(s1[i],c);
        }
        return true;
    }


    public void testStr(String ddd){
        String str = "one two three";
        HashMap<String, String> map = new HashMap<>();
        //split切割后的是 new String()对象,数组存的是字符串对象地址
        String[] parts = str.split(" ");
        for (int i = 0; i < parts.length; i++) {
            map.put(parts[i],parts[i]);
        }
        //true
        System.out.println(map.get("one") == parts[0]);
        //false
        System.out.println(map.get("one") == "one");
        // false
        System.out.println(parts[0] == "one");
        // false
        System.out.println(parts[1] == "two");
        // false
        System.out.println(parts[2] == "three");
    }
}
