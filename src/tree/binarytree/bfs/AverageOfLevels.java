package tree.binarytree.bfs;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Desc: 二叉树的层平均值
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
 * 因此返回 [3, 14.5, 11] 。
 *
 * 示例 2:
 * 输入：root = [3,9,20,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * @Author：zhh
 * @Date：2024/6/25 18:39
 */
public class AverageOfLevels {
    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(7, null, null);
        TreeNode node4 = new TreeNode(15, null, null);
        TreeNode node3 = new TreeNode(20, node4, node5);
        TreeNode node2 = new TreeNode(9, null, null);
        TreeNode node1 = new TreeNode(3, node2, node3);
        AverageOfLevels averageOfLevels = new AverageOfLevels();
        averageOfLevels.averageOfLevels2(node1);
    }

    /**
     * 思路: 广度优先搜索,层次遍历分层,求每层的和/个数 就是每层的平均数
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> average = new LinkedList<>();
        if(root == null){
            return average;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            average.add(sum / size);
        }
        return average;
    }

    /**
     * 思路: 深度优先搜索,可以获取深度,将同一深度的节点获取个数累加求和,求平均值。
     *      技巧:用数组的下标映射树的深度。
     * @param root
     * @return
     */
    List<Integer> count = new LinkedList();
    List<Double> sum = new LinkedList<>();
    public List<Double> averageOfLevels2(TreeNode root) {
        dfs(root,0);
        List<Double> average = new LinkedList<>();
        for (int i = 0; i < count.size(); i++) {
            Double var = sum.get(i);
            Integer c = count.get(i);
            average.add(var/c);
        }
        return average;
    }
    public void dfs(TreeNode root, int depth){
        if(root == null){
            return;
        }
        if(depth == 0 || depth >= count.size()){
            count.add(1);
            sum.add((double)root.val);
        }else {
            Integer nodeCount = count.get(depth);
            Double var = sum.get(depth);
            count.set(depth,++nodeCount);
            sum.set(depth,(double)var+root.val);
        }
        dfs(root.left,depth+1);
        dfs(root.right,depth+1);
    }

}
