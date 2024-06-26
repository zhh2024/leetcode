package tree.binarytree.doubleNode;

import tree.binarytree.TreeNode;

/**
 * @Desc: 判断树是否是轴对称
 * 从root开始拆分左右子树,  与判断两个树A,B是否相同类似
 * A树左分支与B树右分支判断,A树右分支与B树左分支判断。这样就能判断出A,B是否是镜像对称了。
 * @Author：zhh
 * @Date：2024/6/16 19:36
 */
public class IsSymmetric {
    public static void main(String[] args) {

    }

    public static boolean isSymmetric(TreeNode root){
        if(root == null){
            return true;
        }
        return digui(root.left,root.right);
    }
    public static boolean digui(TreeNode p,TreeNode q){
        if(p == null && q == null){
            return true;
        } else if(p != null && q !=null && p.val == q.val){
            return digui(p.left,q.right) && digui(p.right,q.left);
        } else {
            return false;
        }
    }
}
