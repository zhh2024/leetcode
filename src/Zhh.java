
import tree.binarytree.TreeNode;



/**
 * @Desc:
 * @Author：zhh
 * @Date：2025/12/1 20:37
 */
public class Zhh {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(-5);
        TreeNode treeNode2 = new TreeNode(-3);

        TreeNode treeNode3 = new TreeNode(-2);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        maxPathSum(treeNode1);
        System.out.println(max);
    }

    static int  max = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        digui(root);
        return max;
    }

    public static  int digui(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftNum = Math.max(digui(root.left), 0);
        int rightNum = Math.max(digui(root.right), 0);
        max = Math.max(max,root.val + leftNum + rightNum);
        return root.val + Math.max(leftNum,rightNum);
    }





}


