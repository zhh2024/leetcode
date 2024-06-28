package tree.binarytree.backtrackStatus;

import tree.binarytree.TreeNode;

import java.util.LinkedList;

/**
 * @Desc: 二叉树深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：root = [1,null,2]
 * 输出：2
 * @Author：zhh
 * @Date：2024/6/12 12:29
 */
public class MaxDepth {
    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, null, node6);
        TreeNode node4 = new TreeNode(4, node5, null);
        TreeNode node3 = new TreeNode(3, node4, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node1 = new TreeNode(1, node2, node3);
        System.out.println(maxDepth(node1));
    }

    static int maxLength = 0;
    static LinkedList<TreeNode> depthList = new LinkedList<>();

    public static int maxDepth(TreeNode root) {
        depthList.push(root);
        if (root == null) {
            return maxLength;
        }
        maxLength = Math.max(depthList.size(), maxLength);
        maxDepth(root.left);
        depthList.pop();
        maxDepth(root.right);
        depthList.pop();
        return maxLength;
    }

    /**
     * 思路1: 采用虚拟机栈思想，栈中元素达到最大值就是树的最大深度
     * 思路2: 二叉树只有左右两个子树, 比较左右两个子树谁的深度大，选择深度大的+1就是 二叉树的深度
     * 回溯的时候比较且+1,null 返回值必然是0
     * <p>
     * 利用回溯的时候返回值比递归累加更好
     *
     * @param root
     * @return
     */
    public static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        return Math.max(left, right) + 1;
    }


}
