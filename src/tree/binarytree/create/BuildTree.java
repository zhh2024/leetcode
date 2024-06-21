package tree.binarytree.create;

import tree.binarytree.TreeNode;

import java.util.HashMap;

/**
 * @Desc: 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * @Author：zhh
 * @Date：2024/6/17 19:12
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        System.out.println(buildTree(preorder,inorder));
    }
    static HashMap<Integer, Integer> map = new HashMap<>();

    static int preorder_left = 0;
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return buildTree2(preorder,   0, length - 1);
    }

    public static TreeNode buildTree2(int[] preorder
                                      ,int inorder_left,int inorder_right){
        //为根节点,左右子树为null
        if(inorder_left > inorder_right){
            return null;
        }
        //前序数组数组首位就是根,前序数组向前走
        int root = preorder[preorder_left++];
        //建立根节点
        TreeNode rootNode = new TreeNode(root, null, null);
        //通过根节点,拆分中序数组为左右子树
        Integer i = map.get(root);
        //递归指向左子树的根节点
        rootNode.left = buildTree2(preorder,inorder_left,i-1);
        //递归指向右子树的根节点
        rootNode.right = buildTree2(preorder,i+1,inorder_right);
        return rootNode;
    }
}
