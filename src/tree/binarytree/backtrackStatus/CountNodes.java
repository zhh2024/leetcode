package tree.binarytree.backtrackStatus;

import tree.binarytree.TreeNode;

/**
 * @Desc: 完全二叉树的节点个数
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 * @Author：zhh
 * @Date：2024/6/24 15:53
 */
public class CountNodes {
    public static void main(String[] args) {

    }

    /**
     * 思路: 递归的时候回溯带回节点个数,左子树+右子树+自身就是节点总个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
