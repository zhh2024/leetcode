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

    /**
     * 思路 :
     * 1. 不能向上延伸场景: 根右形成一个闭环，整个路径无法再向上延伸，所以此场景需要特别计算当前最大值。
     * 2. 能向上延伸的场景: 根+ 左 与根 + 右比较 ，哪个大选哪边。
     * 3. 这样就可以覆盖所有场景了
     * 4. 为了方便计算，将负数或者不存在的左右子树与0进行比较，如果小于0，就赋值为0，意思不参与运算。
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        int num = digui(root);
        return Math.max(max,num);
    }

    public int digui(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftNum = Math.max(digui(root.left), 0);
        int rightNum = Math.max(digui(root.right), 0);
        max = Math.max(max,root.val + leftNum + rightNum);
        return root.val + Math.max(leftNum,rightNum);
    }
}
