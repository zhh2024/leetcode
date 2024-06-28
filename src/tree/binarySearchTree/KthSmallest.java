package tree.binarySearchTree;

import tree.binarytree.TreeNode;

import java.util.LinkedList;

/**
 * @Desc: 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
 * <p>
 * 示例 1：
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * @Author：zhh
 * @Date：2024/6/28 10:23
 */
public class KthSmallest {
    public static void main(String[] args) {

    }

    /**
     * 思路: 用栈实现递归,出栈时就是中序遍历,k--，直至最后一个,就是要求的值,返回即可
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root!= null || !stack.isEmpty()){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else {
                //此时出栈就是中序遍历
                TreeNode node = stack.pop();
                if(k == 1){
                    return node.val;
                }
                --k;
                root = node.right;
            }
        }
        return 0;
    }
}
