package tree.binarytree;

/**
 * @Desc: 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * @Author：zhh
 * @Date：2024/6/27 18:05
 */
public class IsValidBST {
    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(7, null, null);
        TreeNode node5 = new TreeNode(3, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(6, node5, node6);
        TreeNode node2 = new TreeNode(4, null, null);
        TreeNode node1 = new TreeNode(5, node2, node3);
        IsValidBST isValidBST = new IsValidBST();
        isValidBST.isValidBST(node1);
    }

    Long tmp = Long.MIN_VALUE;
    /**
     * 思路: 中序遍历模型 左根右, 左<根<右。是个递增顺序
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST(root.left);
        if (tmp <  root.val) {
            tmp = (long) root.val;
        }else {
            return false;
        }
        boolean right = isValidBST(root.right);
        return left && right;
    }

}
