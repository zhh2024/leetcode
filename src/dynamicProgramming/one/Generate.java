package dynamicProgramming.one;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]
 * @Author：zhh
 * @Date：2025/12/22 11:26
 */
public class Generate {
    public static void main(String[] args) {
        Generate generate = new Generate();
        List<List<Integer>> generate1 = generate.generate(5);
        System.out.println(generate1);
    }

    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list.add(list2);
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> list3 = new ArrayList<>();
            List<Integer> integers = list.get(i - 1);
            list3.add(integers.get(0));
            for (int j = 1; j < integers.size(); j++) {
                list3.add(integers.get(j) + integers.get(j - 1));
            }
            list3.add(integers.get(integers.size() - 1));
            list.add(list3);
        }
        return list;

    }


}
