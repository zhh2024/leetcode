package tree.binarytree;


import java.util.LinkedList;

/**
 * @Author：zhh
 * @Date：2023/8/25 20:05
 */
public class TreeNode {
    /**
     * 当前值
     */
    public int val;
    /**
     * 左孩子
     */
    public TreeNode left;
    /**
     * 右还子
     */
    public TreeNode right;


    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public void stack(TreeNode root) {
        LinkedList<TreeNode> nodes = new LinkedList<>();
        while (root != null || !nodes.isEmpty() ){
            if(root != null){
                nodes.push(root);
                root = root.left;
            }else {
                TreeNode pop = nodes.pop();
                root = pop.right;
            }
        }
    }



}
