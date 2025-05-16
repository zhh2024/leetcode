package slideWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc: 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 *
 * 考察点: 1. 用数组记录每个字符出现的次数
 *        2. 固定窗口,每滑动一步,添加和删除一个字符
 * @Author：zhh
 * @Date：2025/5/6 14:19
 */
public class FindAnagrams {

    public static void main(String[] args) {
        FindAnagrams findAnagrams = new FindAnagrams();
        List<Integer> anagrams = findAnagrams.findAnagrams("cbaebabacd", "abc");
    }


    /**
     * 实现思路:
     * 1.固定窗口为字符串p的长度, 判断窗口内的字符串是否与p匹配
     * 2.每滑动一位,继续判断窗口内的字符串是否与p 匹配
     * 3.匹配就将窗口左边界存入数组中
     *
     * 难点: 匹配过程中如何减少计算量
     * 1.将p字符串初始化到数组中,记录字符和字符个数
     * 2.初始化窗口到数组中, 记录窗口中的字符和字符个数
     * 3.比较过程中只需要比较两个数组是否相等即可
     * 4.在此初始化窗口基础上进行滑动,每滑动一次,只需要对窗口边界做处理即可,也就是对数组做删除和增加即可,而不需要全部重新创建
     *
     *
     * 优化点: 不使用hash记录字符和字符个数,而是使用数组记录
     * 因为字符串只存在a-z,用上哈希表就是慢，因为每个操作相比数组都有哈希运算,而a-z本身就具有唯一性,所以得用数组
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> list = new ArrayList<>();
        //1. s长度必须大于p长度
        int sLen = s.length();
        int pLen = p.length();
        if(sLen < pLen){
            return list;
        }
        //2. 初始化p字符串和窗口
        int[] sInts = new int[26];
        int[] pInts = new int[26];
        for (int i = 0; i < pLen; i++) {
            ++pInts[p.charAt(i) - 'a'];
            ++sInts[s.charAt(i) - 'a'];
        }
        //3. 比较初始化窗口和p字符串是否相等
        if (Arrays.equals(sInts,pInts)){
            list.add(0);
        }
        //4. 开始滑动,右边界为sLen - pLen -1
        for (int i = 0; i < sLen - pLen; i++) {
            //5.  每滑动一次,只需要对窗口边界做处理即可,也就是对数组做删除和增加即可
            --sInts[s.charAt(i) - 'a'];
            ++sInts[s.charAt(i + pLen) - 'a'];
            if (Arrays.equals(sInts,pInts)){
                list.add(i + 1);
            }
        }
        return list;
    }
}
