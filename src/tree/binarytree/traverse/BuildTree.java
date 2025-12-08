package tree.binarytree.traverse;

import tree.binarytree.TreeNode;

import java.util.HashMap;

/**
 * @Desc: 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 解题:
 * 必须得有中序 + 前序 or 中序 + 后序。
 * 前后没法判定一个二叉树，前序和后序都只能告诉我们谁是谁的根，但无法告诉我们哪些节点属于左子树，哪些属于右子树。
 * 单中序遍历又没法确认根的位置。所以前或者后,必须得配合中序，来判断走哪边，判断条件就是是有该测是由有值。
 * @Author：zhh
 * @Date：2024/6/17 19:12
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder));
    }

    //数组中的值必然是唯一的，如果不是唯一的，通过数组根本没法确认结构。所以可以利用哈希表，取中序数组的下标。
    static HashMap<Integer, Integer> map = new HashMap<>();

    //必须定义在外层，因为递归传入进去的话，回溯时候，下标也会回溯回去，该场景，前序数组应该一直从前往后走，而不应该回溯回去。
    static int preorder_left = 0;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree2(preorder, 0, length - 1);
    }

    public static TreeNode buildTree2(int[] preorder
            , int inorderLeft, int inorderRight) {
        //为根节点,左右子树为null
        if (inorderLeft > inorderRight) {
            return null;
        }
        //前序数组数组首位就是根,前序数组向前走
        int root = preorder[preorder_left++];
        //建立根节点
        TreeNode rootNode = new TreeNode(root, null, null);
        //通过根节点,拆分中序数组为左右子树
        Integer i = map.get(root);
        //递归指向左子树的根节点
        rootNode.left = buildTree2(preorder, inorderLeft, i - 1);
        //递归指向右子树的根节点
        rootNode.right = buildTree2(preorder, i + 1, inorderRight);
        return rootNode;
    }
}
