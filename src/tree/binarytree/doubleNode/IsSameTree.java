package tree.binarytree.doubleNode;

import tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Desc: 判断两个树是否相同
 * 1. 递归根左右,分别判断两个树的节点是否一致
 * 2. 通过前序遍历和中序遍历结果来判断
 * @Author：zhh
 * @Date：2024/6/13 11:05
 */
public class IsSameTree {

    public static void main(String[] args) {

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        } else if(p != null && q !=null && p.val == q.val){
            //继续递归
        } else {
            return false;
        }
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}
