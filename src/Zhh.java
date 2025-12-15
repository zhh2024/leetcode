
import tree.binarytree.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;


/**
 * @Desc:
 * @Author：zhh
 * @Date：2025/12/1 20:37
 */
public class Zhh {

    public static void main(String[] args) {
        //[236,104,701,null,227,null,911]
        TreeNode treeNode1 = new TreeNode(236);
        TreeNode treeNode2 = new TreeNode(104);
        TreeNode treeNode3 = new TreeNode(701);
        TreeNode treeNode4 = new TreeNode(227);
        TreeNode treeNode5 = new TreeNode(911);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.right = treeNode4;
        treeNode3.right = treeNode5;

        Zhh zhh = new Zhh();


    }







}


