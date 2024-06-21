package tree.binarytree.traverse;

import tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc: 层级遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * @Author：zhh
 * @Date：2024/6/21 15:21
 */
public class LevelOrder {
    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(7, null, null);
        TreeNode node4 = new TreeNode(15, null, null);
        TreeNode node3 = new TreeNode(20, node4, node5);
        TreeNode node2 = new TreeNode(9, null, null);
        TreeNode node1 = new TreeNode(3, node2, node3);
        levelOrder(node1);
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> levelVar = new ArrayList<>();
        int depth = getDepth(root);
        if(depth == 0){
            return levelVar;
        }
        ArrayList<List<TreeNode>> level = new ArrayList<>(depth);
        //初始化赋值根节点为第一层
        ArrayList<TreeNode> rootList = new ArrayList<>();
        rootList.add(root);
        level.add(rootList);
        //遍历深度,构造depth层
        for (int i = 0; i < depth - 1; i++) {
            List<TreeNode> treeNodes = level.get(i);
            ArrayList<TreeNode> nodeList = new ArrayList<>();
            for (int j = 0; j < treeNodes.size(); j++) {
                TreeNode treeNode = treeNodes.get(j);
                if (treeNode.left!=null) {
                    nodeList.add(treeNode.left);
                }
                if (treeNode.right!=null) {
                    nodeList.add(treeNode.right);
                }
            }
            level.add(nodeList);
        }
        //将treeNode替换成var
        for (int i = 0; i < level.size(); i++) {
            List<TreeNode> treeNodes = level.get(i);
            ArrayList<Integer> vars = new ArrayList<>();
            for (int j = 0; j < treeNodes.size(); j++) {
                int val = treeNodes.get(j).val;
                vars.add(val);
            }
            levelVar.add(vars);
        }
        return levelVar;
    }

    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}
