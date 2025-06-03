package backTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Desc: 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * @Author：zhh
 * @Date：2025/5/22 9:47
 */
public class LetterCombinations {
    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> strings = letterCombinations.letterCombinations("23");
        System.out.println(strings.toString());
    }

    HashMap<Character, String> map = new HashMap<>();

    ArrayList<String> list = new ArrayList<>();

    /**
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        StringBuilder stringBuilder = new StringBuilder();
        backTrack02(digits, 0, stringBuilder);
        return list;
    }

    /**
     * 思路: 使用for循环需要遍历digits 每个字符,循环次数是digits.length
     *      而且digits.length是随机的,是能把循环抽出一个方法,每次递归调用
     * @param digits
     * @param k
     * @param path
     */
    public void backTrack(String digits, int k, String path) {
        if (k == digits.length()) {
            if(!"".equals(path)){
                list.add(path);
            }
            return;
        }
        String s = map.get(digits.charAt(k));
        for (int i = 0; i < s.length(); i++) {
            path = path + s.charAt(i);
            backTrack(digits, k + 1, path);
            //去掉最后一个字符
            path = path.substring(0, path.length() - 1);
        }
    }

    /**
     * 优化:
     * 1. 字符传拼接,使用StringBuffer 时间复杂度O(1) 减少频繁字符串拷贝
     * 2. 直接删除最后一个元素,时间复杂度O(1)
     * @param digits
     * @param k
     * @param stringBuilder
     */
    public void backTrack02(String digits, int k, StringBuilder stringBuilder) {
        if (k == digits.length()) {
            if(stringBuilder.length()>0){
                list.add(stringBuilder.toString());
            }
            return;
        }
        String s = map.get(digits.charAt(k));
        for (int i = 0; i < s.length(); i++) {
            stringBuilder.append(s.charAt(i));
            backTrack02(digits, k + 1, stringBuilder);
            //去掉最后一个字符
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }
}
