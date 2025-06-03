package backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc: 组合总和
 * 给你一个无重复元素的整数数组candidates和一个目标整数 target ，找出 candidates 中可以使数字和为目标数target的所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * <p>
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * @Author：zhh
 * @Date：2025/5/22 15:23
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2,3,5};
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, 8);
        System.out.println(lists.toString());
    }
    ArrayList<Integer> path = new ArrayList<>();
    List<List<Integer>> ints = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTrack(candidates,target,0);
        return ints;
    }

    /**
     * start从i开始 ,包含本身
     * @param candidates
     * @param target
     * @param start
     */
    public void backTrack(int[] candidates, int target,int start) {
        if(target  < 0){
            return;
        }
        if(target == 0){
            ints.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int number = candidates[i];
            path.add(number);
            backTrack(candidates,target - number , i);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 优化点
     * 1.将target < 0 放到循环里,就可以避免进入递归
     * 2.提前排序,可以减少重复元素进入递归
     * 3.排序后,如果target<0,表示后面的元素都大,都不符合条件,可以直接break
     * @param candidates
     * @param target
     * @param start
     */
    public void backTrack02(int[] candidates, int target,int start) {
        if(target == 0){
            ints.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int number = candidates[i];
            if(i > start && candidates[i +1]== candidates[i] ){
                continue;
            }
            if(target - number  < 0){
                break;
            }
            path.add(number);
            backTrack02(candidates,target - number , i);
            path.remove(path.size() - 1);
        }
    }
}
