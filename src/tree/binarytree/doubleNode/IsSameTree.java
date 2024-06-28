package tree.binarytree.doubleNode;

import tree.TreeNode;


/**
 * @Desc: 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1：
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * @Author：zhh
 * @Date：2024/6/13 11:05
 */
public class IsSameTree {

    public static void main(String[] args) {

    }

    /**
     * 思路: 深度优先搜索,前序遍历模型
     * 1. 递归根左右,分别判断两个树的节点是否一致
     * 2. 通过前序遍历和中序遍历结果来判断
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null && p.val == q.val) {
            //继续递归
        } else {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
