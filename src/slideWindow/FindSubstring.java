package slideWindow;

import java.util.*;

/**
 * @Desc: 串联所有单词的子串
 * <p>
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
 * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
 * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
 * 输出顺序无关紧要。返回 [9,0] 也是可以的。
 * <p>
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
 * s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
 * 所以我们返回一个空数组。
 * <p>
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
 * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
 * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
 * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
 * @Author：zhh
 * @Date：2025/3/18 9:34
 */
public class FindSubstring {
    public static void main(String[] args) {
        FindSubstring findSubstring = new FindSubstring();
        String[] strings= {"foo","bar","the"};
        List<Integer> l = findSubstring.findSubstring("barfoofoobarthefoobarman", strings);
        System.out.println(l);
    }

    /**
     * 实现思路:
     * 1. 固定窗口,窗口大小为 words每个字符串总长度,开始滑动
     * 2. 滑动过程中,判断窗口内的字符串(每三个一组)是否与words一一匹配
     * 3. 匹配就记录窗口左边界的值,不匹配就继续滑动,直至右边界到达数组边界
     *
     * 难点？如何匹配，
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        int windowLength = 0;
        int wordLen = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String word: words){
            wordLen = word.length();
            windowLength = windowLength + word.length();
            map.put(word,map.getOrDefault(word, 0) + 1 );
        }
        ArrayList<Integer> ins = new ArrayList<>();
        int l = 0;
        int r = windowLength - 1;
        while (r < s.length()){
            String str = s.substring(l, r + 1);
            if(piPei(map,str,wordLen)){
                ins.add(l);
            }
            l++;
            r++;
        }
        return ins;
    }

    /**
     * 匹配算法太慢,需要优化
     * @param map
     * @param str
     * @param wordLen
     * @return
     */
    public boolean piPei(HashMap<String, Integer> map, String str, int wordLen){
        HashMap<String, Integer> map2 = new HashMap<>();
        int begin = 0;
        int end = wordLen -1;
        while (end < str.length()){
            String key = str.substring(begin, end + 1);
            map2.put(key,map2.getOrDefault(key,0)+1);
            begin = begin + wordLen;
            end = end + wordLen;
        }
        Set<String> stringSet = map.keySet();
        for (String s:stringSet){
            if(map2.getOrDefault(s, 0) < map.get(s)){
                return false;
            }
        }
        return true;
    }

}
