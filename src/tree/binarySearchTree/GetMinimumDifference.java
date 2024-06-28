package tree.binarySearchTree;

import tree.binarytree.TreeNode;

import java.util.LinkedList;

/**
 * @Desc: 二叉搜索树的最小绝对差
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * <p>
 * 示例 1：
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 * @Author：zhh
 * @Date：2024/6/28 11:03
 */
public class GetMinimumDifference {
    public static void main(String[] args) {

    }

    int min = Integer.MAX_VALUE;
    TreeNode pre;
    public int getMinimumDifference(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                if (pre != null) {
                    min = Math.min(node.val - pre.val, min);
                }
                pre = node;
                root = node.right;
            }
        }
        return min;
    }

    public int dfs(TreeNode root){
        if(root == null){
            return min;
        }
        dfs(root.left);
        if (pre != null) {
            min = Math.min(root.val - pre.val, min);
        }
        pre = root;
        dfs(root.right);
        return min;
    }
}
