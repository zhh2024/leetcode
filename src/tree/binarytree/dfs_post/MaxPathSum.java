package tree.binarytree.dfs_post;

import tree.binarytree.TreeNode;

/**
 * @Desc: 二叉树中的最大路径和
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中至多出现一次 。该路径至少包含一个节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6\
 * <p>
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * @Author：zhh
 * @Date：2024/6/26 16:55
 */
public class MaxPathSum {
    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(3, null, null);
        TreeNode node5 = new TreeNode(25, null, null);
        TreeNode node4 = new TreeNode(25, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(100, null, null);
        TreeNode node1 = new TreeNode(-10, node2, node3);
        MaxPathSum maxPathSum = new MaxPathSum();
        System.out.println(maxPathSum.maxPathSum(node1));
    }
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        int dfs = dfs2(root);
        return Math.max(max,dfs);
    }
    /**
     * 思路1: 比思路2多思考了leftVar,rightVar。
     */
    public int dfs(TreeNode root){
        if(root == null){
            return Integer.MIN_VALUE >> 2;
        }
        int leftVar = dfs(root.left);
        int rightVar = dfs(root.right);
        //总共六种场景 leftVar,rightVar,root,leftVar + root.val,rightVar + root.val, leftVar + root.val + rightVar
        int sum = leftVar + root.val + rightVar;
        //只需要考虑,leftVar,rightVar,sum谁大就暂存起来，可以构成路径的返回局部最优了,最终会与max比较,所以无需考虑 root与其他组合。
        max = Math.max(max,Math.max(Math.max(leftVar,rightVar),sum));
        return Math.max (root.val,Math.max(leftVar + root.val, rightVar + root.val));
    }


    /**
     * 思路2 :
     * 1. 有四种情况,root.val,leftVar + root.val,rightVar + root.val,leftVar + root.val + rightVar
     * 2. 把四四种情况的最大值(当前子树最优解,比之前的最优解强，就是目前二叉树局部最优解)暂存,继续递归,最终求出全局最优解。
     * 3. 继续递归的条件,选择root.val,leftVar + root.val,rightVar + root.val中的最优解返回继续构成路径。
     * @param root
     * @return
     */
    public int dfs2(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftVar = dfs(root.left);
        int rightVar = dfs(root.right);
        int sum = leftVar + root.val + rightVar;
        int curr = Math.max (root.val,Math.max(leftVar + root.val, rightVar + root.val));
        max = Math.max(max,Math.max(sum,curr));
        //选择最优的递归
        return curr;
    }
}
