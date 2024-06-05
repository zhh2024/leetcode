package tree.binarytree;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 完全二叉树
 * @Author：zhh
 * @Date：2023/8/25 20:01
 */
public class CompleteBinaryTree {

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean end = false;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            // 如果已经遇到了叶节点，那么后面的节点都应该为null
            if (node == null) {
                if (end) return false;
                else end = true;
            } else {
                // 如果左子节点存在，那么它必须在当前节点之后被访问到
                if (node.left != null && !stack.isEmpty()) return false;
                // 如果右子节点存在，那么它必须在当前节点的下一层中
                if (node.right != null && !end) return false;
                stack.offer(node.right);
                stack.offer(node.left);
            }
        }
        return true;
    }

}
