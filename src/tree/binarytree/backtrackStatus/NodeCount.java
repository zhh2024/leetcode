package tree.binarytree.backtrackStatus;

import tree.binarytree.TreeNode;

/**
 * @Desc: 统计二叉树中节点的个数
 *        递归根左右。 null返回0,回溯的时候+1 ,代表此时是根节点
 *        利用回溯的时候返回值比递归累加更好
 * @Author：zhh
 * @Date：2024/6/16 19:09
 */
public class NodeCount {
    public static void main(String[] args) {

    }
    public static int nodeCount(TreeNode root){
        if(root == null){
            return 0;
        }
        return nodeCount(root.left) + nodeCount(root.right) + 1;
    }
}
