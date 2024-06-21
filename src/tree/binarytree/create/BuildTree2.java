package tree.binarytree.create;

import tree.binarytree.TreeNode;

import java.util.HashMap;

/**
 * @Desc: 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * @Author：zhh
 * @Date：2024/6/17 19:12
 */
public class BuildTree2 {

    public static void main(String[] args) {

        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        System.out.println(buildTree(inorder,postorder));
    }
    static HashMap<Integer, Integer> map = new HashMap<>();

    static int postorder_left = 0;
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        postorder_left= postorder.length-1;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }

        return buildTree2(postorder,  0, postorder.length - 1);
    }

    public static TreeNode buildTree2(int[] postorder,int inorder_left,int inorder_right){
        //为根节点,左右子树为null
        if(inorder_left > inorder_right){
            return null;
        }
        //前序数组数组首位就是根,前序数组向前走
        int root = postorder[postorder_left--];
        //建立根节点
        TreeNode rootNode = new TreeNode(root, null, null);
        //通过根节点,拆分中序数组为左右子树
        Integer i = map.get(root);
        //递归指向右子树的根节点
        rootNode.right = buildTree2(postorder,i+1,inorder_right);
        //递归指向左子树的根节点
        rootNode.left = buildTree2(postorder,inorder_left,i-1);

        return rootNode;
    }
}
