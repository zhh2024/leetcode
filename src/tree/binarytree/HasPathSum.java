package tree.binarytree;

/**
 * @Desc:给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 如果存在，返回 true ；否则，返回 false 。
 * @Author：zhh
 * @Date：2024/6/24 13:56
 */
public class HasPathSum {
    public static void main(String[] args) {

    }

    /**
     * 时间复杂度O(n),空间复杂度O(n)
     * 思路: 深度优先搜索, 根左右,每次进来减去根的值,如果遇到叶子节点此时targetSum == 叶子节点值,就返回true。
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }
        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);
    }
}
