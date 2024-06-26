package tree.binarytree.dfs_pre;

import tree.binarytree.TreeNode;

/**
 * @Desc: 翻转二叉树
 * @Author：zhh
 * @Date：2024/6/16 19:16
 */
public class InvertTree {
    public static void main(String[] args) {

    }
    public static TreeNode invertTree(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
