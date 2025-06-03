package backTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 通用回溯模板
 *        不同的场景,中止条件不同,进入递归的条件也不同
 * @Author：zhh
 * @Date：2025/6/3 10:18
 */
public class Base {

    //结果
    List<List<Integer>> result = new ArrayList<>();
    //路径
    ArrayList<Integer> path = new ArrayList<>();

    public  void backTrack(int n, int k, int start) {
        //递归中止条件
        if (k == 0) {
            //结果.append(路径)
            result.add(new ArrayList<>(path));
            return;
        }
        //选择列表
        for (int i = start; i <= n - k + 1; i++) {
            //做选择
            path.add(i);
            //新的路径, 新的选择列表
            backTrack(n, k - 1, i + 1);
            //撤销选择
            path.remove(path.size() - 1);
        }
    }
}
