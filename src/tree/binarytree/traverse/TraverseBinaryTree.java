package tree.binarytree.traverse;

import tree.binarytree.TreeNode;

import java.util.LinkedList;

/**
 * @Desc: 遍历二叉树
 * 前中后三种遍历,只有其中一种是无法确定树结构的，因为遍历结果只是节点的值,null被排除掉了
 *
 * 子树如果只有两个节点, 1,2  根是1,left可以是2,right也可以是2。是有歧义的
 * 根左右，左根右，左右根 只有两个节点的情况是有歧义的无法确定树结构。所以必须前+中 ,中+前才能确认
 * 如果前+后呢,也不可以。前序遍历是1,2 后序遍历是2,1。 根是1但左边可以是2,右边也可以是2,无法确定树结构。
 * @Author：zhh
 * @Date：2024/6/16 15:16
 */
public class TraverseBinaryTree {

    public static void main(String[] args) {
        //模拟二叉树
        TreeNode node10 = new TreeNode(10, null, null);
        TreeNode node9 = new TreeNode(9, null, null);
        TreeNode node8= new TreeNode(8, node10, null);
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, null, node9);
        TreeNode node5 = new TreeNode(5, null, node8);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, node7);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        prefaceTraversal(node1);
    }

    /**
     * 前序遍历 根左右
      * @param root
     */
    public static void prefaceTraversal(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.val);
        prefaceTraversal(root.left);
        prefaceTraversal(root.right);
    }

    /**
     * 中序遍历 左根右
     * @param root
     */
    public static void middleTraversal(TreeNode root){
        if(root == null){
            return;
        }
        middleTraversal(root.left);
        System.out.println(root.val);
        middleTraversal(root.right);
    }

    /**
     * 后序遍历 左右根
     * @param root
     */
    public static void sequentialTraversal(TreeNode root){
        if(root == null){
            return;
        }
        sequentialTraversal(root.left);
        sequentialTraversal(root.right);
        System.out.println(root.val);
    }

    /**
     * 非递归方式遍历二叉树 ,只能前序和中序遍历,后序遍历不可以,无法判断栈的进出顺序
     * left 进栈。如果为null,出栈,指向right
     *
     * 时间复杂度O(n),空间复杂度O(n)
     * @param root
     */
    public static void stackTraversal(TreeNode root){
        LinkedList<TreeNode> stackNode = new LinkedList<>();
        while (root != null || !stackNode.isEmpty()){
            if(root != null){
                System.out.println(root.val);
                stackNode.push(root);
                root = root.left;
            }else {
                TreeNode head = stackNode.pop();
                root = head.right;
            }
        }
    }
}
