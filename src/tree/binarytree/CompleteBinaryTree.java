package tree.binarytree;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 完全二叉树
 * @Author：zhh
 * @Date：2023/8/25 20:01
 */
public class CompleteBinaryTree {

    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        boolean completeTree = isCompleteTree(node1);
        System.out.println(completeTree);
    }

    public static boolean isCompleteTree(TreeNode root) {
        //1. 如果为null,则是空树
        if(root == null ){
            return true;
        }
        //2. 单节点二叉树
        if(root.left == null && root.right ==null){
            return true;
        }
        //3. 有且仅有一个根节点

        //4. 通过根节点寻找子树和子树关系
        TreeNode left = root.left;
        TreeNode right = root.right;
        //5. 根节点只有左右两分支,且左右子树和子树关系无交集
        LinkedList<TreeNode> leftList = new LinkedList<>();
        LinkedList<TreeNode> rightList = new LinkedList<>();
        findTreeNode(leftList,left);
        findTreeNode(rightList,right);

        HashMap<TreeNode,TreeNode> treeNodeMap = new HashMap<>();
        leftList.forEach(treeNode -> treeNodeMap.put(treeNode,treeNode));
        List<TreeNode> collect = rightList.parallelStream().filter(treeNode -> treeNodeMap.containsKey(treeNode))
                .collect(Collectors.toList());
        if(collect.size()>0){
            return false;
        }
        //5. 只有两个分支,左分支存在,那么右分支必然存在。
        if(left != null && left.left !=null){

        }
        boolean leftComplete = isCompleteTree(root.left);
        boolean rightComplete = isCompleteTree(root.right);
        return leftComplete && rightComplete;
    }

    public static List<TreeNode> findTreeNode(List<TreeNode> list,TreeNode root){
        if (root == null) {
            return list;
        }
        list.add(root);
        findTreeNode(list,root.left);
        return findTreeNode(list, root.right);
    }

}
