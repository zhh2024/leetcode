package tree;

/**
 * @Author：zhh
 * @Date：2023/8/25 20:05
 */
public class TreeNode {
    /**
     * 当前值
     */
    int val;
    /**
     * 左孩子
     */
    TreeNode left;
    /**
     * 右还子
     */
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
