package tree.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2025/6/4 8:57
 */
public class Test {
    public static void main(String[] args) {
        //[1,2,5,3,4,null,6]

        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(4, null, null);
        TreeNode node4 = new TreeNode(3, null, null);
        TreeNode node3 = new TreeNode(5, null, node6);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode root = new TreeNode(1, node2, node3);
        Test test = new Test();
        test.flatten(root);

    }



    public void bl (TreeNode root){
        LinkedList<TreeNode> nodes = new LinkedList<>();
        while (root != null || !nodes.isEmpty()){
            if (root != null) {
                nodes.push(root);
                root = root.left;
            }else{
                TreeNode top = nodes.pop();
                root = top.right;
            }
        }
    }

    public void cenci (TreeNode root){
        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        while (!nodes.isEmpty()){
            TreeNode poll = nodes.poll();
            if (poll.left!= null) {
                nodes.offer(poll.left);
            }
            if (poll.right!= null) {
                nodes.offer(poll.right);
            }
        }
    }

    public int deep (TreeNode root){
        if(root == null){
            return 0;
        }
        int l = deep(root.left) + 1;
        int r = deep(root.right) + 1;
        return Math.max(l, r);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public  boolean isSymmetric(TreeNode root){
        return digui(root.left,root.right);
    }

    /**
     * @param leftRoot
     * @param rightRoot
     * @return
     */
    public boolean digui (TreeNode leftRoot , TreeNode rightRoot) {
        if(leftRoot == null && rightRoot == null ){
            return true;
        }
        if(leftRoot == null || rightRoot == null ){
            return false;
        }
        if(leftRoot.val != rightRoot.val){
            return false;
        }
        boolean digui1 = digui(leftRoot.left, rightRoot.right);
        if(!digui1){
            return false;
        }
        return digui(leftRoot.right, rightRoot.left);

    }

    /**
     *     2
     *  -1  -2
     * @param root
     * @return
     */
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        digui2(root);
        return max;
    }


    public int digui2(TreeNode root) {
        if(root == null){
            return 0;
        }
        int lv = digui2(root.left);
        int rv = digui2(root.right);
        int v = root.val;

        //应该记录 v, v+lv, v+ rv, v+lv+rv 四个最大的值。不然返回后没记录会变的更小
        int returnMax = Math.max(Math.max(v, v + lv), Math.max(v, v + rv));
        max = Math.max(max,Math.max(returnMax,v + lv +rv));


        //返回是返回根左,根右,根 三者最大的 c zAa0-p
        return returnMax;

    }

    //[1,2,5,3,4,null,6]
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode temp = root.right;
        if(root.left != null){
            root.right = root.left;
            root.left = null;
            flatten(root.right);
            root.right.right = temp;
        }
        flatten(temp);
    }

}
