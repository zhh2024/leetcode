package hashMap;

import java.util.HashMap;

/**
 * @Desc: 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 * 示例 1：
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * <p>
 * 示例 2：
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：ransomNote = "aab", magazine = "baa"
 * 输出：true
 * @Author：zhh
 * @Date：2024/7/15 9:38
 */
public class CanConstruct {
    public static void main(String[] args) {
        CanConstruct canConstruct = new CanConstruct();
        System.out.println();
        System.out.println(canConstruct.canConstruct("aab","baa"));
    }

    /**
     * 时间复杂度O(m+n)
     * 思路:  利用哈希表统计magazine字符和字符个数,然后ransomNote进行消除
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            if (map.containsKey(c)) {
                Integer number = map.get(c);
                map.put(c,++number);
            }else {
                map.put(c,1);
            }
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            Integer number = map.get(c);
            if(number == 0){
                return false;
            }
            map.put(c,--number);
        }
        return true;
    }

    /**
     * 优化: 这种单字符场景,字符有26个,从小到大, c- 'a' 就是字符c在cns的下标,这种场景下比hashMap计算hash值更快
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct02(String ransomNote, String magazine) {
        if(ransomNote.length()>magazine.length()){
            return false;
        }
        int[] cns = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            //字符有26个,从小到大, c- 'a' 就是字符c在cns的下标,这种场景下比hashMap计算hash值更快
            cns[c - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            cns[c - 'a']--;
            if(cns[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
