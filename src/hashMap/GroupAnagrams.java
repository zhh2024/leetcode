package hashMap;

import java.util.*;

/**
 * @Desc: 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * <p>
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 * <p>
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * @Author：zhh
 * @Date：2024/10/22 14:38
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        String[] strings= {"eat","tea","tan","ate","nat","bat"};
        groupAnagrams.groupAnagrams02(strings);
    }

    /**
     * 方法1: 时间复杂度O(n^3)
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> lists = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        if(strs.length == 0){
            list.add("");
            lists.add(list);
            return lists;
        }
        list.add(strs[0]);
        lists.add(list);
        for (int i = 0; i < strs.length -1; i++) {
            String str = strs[i+1];
            boolean flag = false;
            for (int j = 0; j < lists.size(); j++) {
                List<String> strings = lists.get(j);
                if (isAnagrams(strings.get(0),str)) {
                    flag = true;
                    strings.add(str);
                }
            }
            if(!flag){
                ArrayList<String> strings = new ArrayList<>();
                strings.add(str);
                lists.add(strings);
            }
        }
        return lists;
    }
    public boolean isAnagrams(String s, String t) {
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

    /**
     * 方法2: 时间复杂度O(n^2logn)
     * 实现思路: 利用排序,排序后相同的字符串就代表同一组,这样就可以将排序后的字符串放入map中，后续遍历的过程中去取map中的key比较
     * 对比方法1,优化了遍历比较,时间复杂度由O(n)到O(1),但是排序比isAnagrams()方法复杂度高logn,所以总体来说方法二比方法一更优
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams02(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length ; i++) {
            String str1 = strs[i];
            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String str = new String(charArray);
            List<String> orDefault = map.getOrDefault(str, new ArrayList<>());
            orDefault.add(str1);
            map.put(str,orDefault);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 方法3: 在方法1的基础上优化到 O(n^2),方法1慢在当前遍历到的字符串需要与之前的字符串比较
     * 既然是字母异位词有没有可能有一种规律,可以作为独有的key,将其存到map中，使得与之前的字符串比较复杂度为O(1)
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams03(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int[] ints = new int[26];
            for (int j = 0; j < str.length(); j++) {
                ints[str.charAt(j) - 'a']++;
            }
            //符合字母异位词的字符串，ints数组必然相同,转化成string必然相同,可以作为map中的key
            String key = new String(Arrays.toString(ints));
            List<String> orDefault = map.getOrDefault(key, new ArrayList<>());
            orDefault.add(str);
            map.put(key,orDefault);
        }
        return new ArrayList<>(map.values());
    }


}
