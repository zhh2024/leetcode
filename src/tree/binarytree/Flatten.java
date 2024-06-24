package tree.binarytree;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/6/23 21:25
 */
public class Flatten {
    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, null, node6);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node3, node4);
        TreeNode node1 = new TreeNode(1, node2, node5);
    }

    public static void flatten(TreeNode root){
        if(root == null){
            return ;
        }
        flatten(root.left);
        flatten(root.right);
        //回溯时调换位置
        TreeNode tmp = root.right;
        if(root.left!=null){
            root.right = root.left;
            TreeNode curr = root.right;
            while (curr.right!=null){
                curr = curr.right;
            }
            curr.right = tmp;
            root.left = null;
        }
    }
}
