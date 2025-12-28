package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Desc: 划分字母区间
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * <p>
 * 示例 2：
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 *
 * 思路：将问题转化为"跳跃游戏"的变种
 * 找到每个字母能到达的最远位置：遍历字符串，记录每个字符最后一次出现的位置
 * 将字符串分割转化为跳跃范围：遍历字符串，维护当前片段的最新结束位置，当当前位置到达该结束位置时，说明可以切分
 *
 * 类比"跳跃游戏"的解释：
 * 每个字符 s[i] 就像是一个"跳板"，它能让你跳到它的最后出现位置
 * 在片段 [start, end] 中：
 * 我们从 start 开始
 * 遇到每个字符，我们就看这个字符能跳到的最远位置
 * 我们始终维护能跳到的最远距离 end
 * 当 i == end 时，说明我们到达了当前能跳到的最大距离
 * 这个距离内的所有字符，它们的最远出现位置都不会超过 end,因此可以安全切分
 * @Author：zhh
 * @Date：2025/12/28 21:19
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        int start = 0;
        int end = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, map.get(s.charAt(i)));
            if (end == i) {
                list.add(end - start + 1);
                start = end + 1;
            }
        }
        return list;

    }
}
