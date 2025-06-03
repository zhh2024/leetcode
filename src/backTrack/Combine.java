package backTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * <p>
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * @Author：zhh
 * @Date：2025/5/22 11:15
 */
public class Combine {

    public static void main(String[] args) {
        Combine combine = new Combine();
        List<List<Integer>> combine1 = combine.combine(4, 2);
        System.out.println(combine1.toString());
    }

    public List<List<Integer>> combine(int n, int k) {
        ArrayList<Integer> ch = new ArrayList<>();
        List<List<Integer>> ints = new ArrayList<>();
        combine03(n, k, 1);
        return ints;
    }

    /**
     * 缺点, 无法动态,如果需要四个为一组就需要,就再加一层for循环
     * @param n
     * @return
     */
    public static List<List<Integer>> combine01(int n) {
        List<List<Integer>> ints = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    ArrayList<Integer> path = new ArrayList<>();
                    path.add(i);
                    path.add(j);
                    path.add(k);
                    ints.add(path);
                }
            }
        }
        return ints;
    }

    /**
     * 根据for循环推导出来的递归原则
     * 1. 如果k个为一组,就需要for循环嵌套k次
     * 2. 内层for循环,要在外层for循环基础上+1
     * 3. 这样的for嵌套循环,从外到里的数组合在一起,就是新组合
     * 4. 使用递归,可以定义for循环次数,更加灵活
     *
     * @param n
     * @param group
     * @param index
     * @param ch
     * @param ints
     * @return
     */
    public static List<List<Integer>> combine02(int n, int group, int index, List<Integer> ch, List<List<Integer>> ints) {
        if (group == 1) {
            for (int i = index; i <= n; i++) {
                ArrayList<Integer> integers = new ArrayList<>(ch);
                integers.add(i);
                ints.add(integers);
            }
            return ints;
        }
        for (int i = index; i <= n; i++) {
            ch.add(i);
            combine02(n, group - 1, i + 1, ch, ints);
            ch.remove(ch.size() - 1);
        }
        return ints;
    }


    ArrayList<Integer> path = new ArrayList<>();
    List<List<Integer>> ints = new ArrayList<>();

    /**
     * 优化点分析
     * 1. 移除元素的效率问题
     * 原代码中通过 ch.remove(ch.indexOf(i)) 删除元素，但 indexOf(i) 需要遍历列表，时间复杂度为 O(n)。
     * 优化后直接使用 ch.remove(path.size() - 1)，直接移除最后一个元素，时间复杂度降为 O(1)。
     * 2. 递归终止条件码在 group == 1 时循环添加剩余元素，需多次复制列表
     * 优化后直接group == 0,再进入一次递归,就会携带path进来,无需循环
     * 3. 剪枝优化
     * 原代码的循环条件是 i <= n，未限制无效递归。
     * 优化后，通过 i <= n - k + 1 提前终止无法满足剩余元素数量的情况。因为剩余元素不够一组k
     * 4. 优化参数
     * 参数太多,list放到外层即可,无需作为方法参数
     *
     * @param n
     * @param k
     * @param start
     */
    private void combine03(int n, int k, int start) {
        if (k == 0) {
            ints.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            path.add(i);
            combine03(n, k - 1, i + 1);
            path.remove(path.size() - 1);
        }
    }


}
