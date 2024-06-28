package tree.binarytree.traverse;

import tree.binarytree.traverse.TraverseBinaryTree;
import tree.binarytree.TreeNode;

/**
 * @Desc: 利用遍历创建二叉树
 * 二叉树 是根左右,递归创建。所以只能是先序遍历的顺序构造数组。
 * @Author：zhh
 * @Date：2024/6/16 15:55
 */
public class CreateBinaryTree {
    public static void main(String[] args) {
        // -1代表 null,是可以确定一根二叉树的
        int[] vars = {1,2,4,-1,-1,5,-1,8,10,-1,-1,-1,3,6,-1,9,-1,-1,7,-1,-1};
        TreeNode root = createBinaryTree(vars);
        //前序遍历是否与vars一致
        TraverseBinaryTree.prefaceTraversal(root);
    }

    static int index = 0;

    /**
     * 利用前序遍历创建二叉树
     * @param vars
     */
    public static TreeNode createBinaryTree(int[] vars ){
        int var = vars[index++];
        if(var == -1){
            return null;
        }
        TreeNode root = new TreeNode(var, null, null);
        TreeNode leftTree = createBinaryTree(vars);
        TreeNode rightTree = createBinaryTree(vars);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }
}
