package backTrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Desc: 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * @Author：zhh
 * @Date：2025/5/22 14:40
 */
public class Permute {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permute permute = new Permute();
        List<List<Integer>> permute1 = permute.permute(nums);
        System.out.println(permute1.toString());
    }

    List<List<Integer>> ints = new ArrayList<>();

    /**
     * 全排列,循环次数是nums长度,所以显示for循环不合理,肯定是调用同一个方法使用递归
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<Integer> path = new ArrayList<>();
        backTrack(nums, nums.length, path);
        return ints;
    }

    /**
     * 传统的"选择-添加-回溯"  , contains时间复杂度过高
     */
    public void backTrack(int[] nums, int k, List<Integer> path) {
        if (k == 0) {
            ints.add(new ArrayList<>(path));
            return;
        }
        //排除掉path中的元素
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (path.contains(num)) {
                continue;
            }
            path.add(num);
            backTrack(nums, k - 1, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 递归交换法
     * @param nums
     * @return
     */
    public List<List<Integer>> permute02(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack02(n, output, res, 0);
        return res;
    }

    public void backtrack02(int deep, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == deep) {
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < deep; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack02(deep, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
