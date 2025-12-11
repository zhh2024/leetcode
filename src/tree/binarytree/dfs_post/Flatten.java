package tree.binarytree.dfs_post;

import tree.binarytree.TreeNode;

/**
 * @Desc: 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 * @Author：zhh
 * @Date：2024/6/23 21:25
 */
public class Flatten {
    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, null, node6);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node3, node4);
        TreeNode node1 = new TreeNode(1, node2, node5);
    }

    /**
     * 思路: 所就是先把左子树展开成链表，找到链表的尾节点，再把右子树展开成链表，然后接到左子树的链表上
     * 注意点: 找左子树的尾结点需要遍历到末尾再接右子数的头结点
     * @param root
     */
    public void flatten(TreeNode root) {

        digui(root);

    }

    public TreeNode digui(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode leftTree = digui(root.left);
        TreeNode rightTree = digui(root.right);
        root.left = null;
        if(leftTree == null){
            return root;
        }
        root.right = leftTree;
        TreeNode current = leftTree;
        while (current.right != null){
            current = current.right;
        }
        current.right = rightTree;
        return root;
    }
}
