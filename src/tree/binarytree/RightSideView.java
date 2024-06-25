package tree.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * @Desc: 二叉树的右视图
 * 给定一个二叉树的根节点root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * <p>
 * 示例 2:
 * 输入: [1,null,3]
 * 输出: [1,3]
 * <p>
 * 示例 3:
 * 输入: []
 * 输出: []
 * @Author：zhh
 * @Date：2024/6/25 10:24
 */
public class RightSideView {
    public static void main(String[] args) {
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node1 = new TreeNode(1, node2, null);
        RightSideView rightSideView = new RightSideView();
        rightSideView.rightSideView2(node1);
    }

    /**
     * 时间复杂度O(n),空间复杂度O(n)
     * 思路: 广度优先搜索,利用队列先进先出的原则,按树深度分层,每一层的最后一个元素就是右视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> var = new LinkedList();
        if(root == null){
            return var;
        }
        LinkedList<TreeNode> nodeQueue = new LinkedList();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                if(i == 0){
                    var.add(node.val);
                }
                TreeNode left = node.left;
                TreeNode right = node.right;
                if(right!=null){
                    nodeQueue.offer(right);
                }
                if(left!=null){
                    nodeQueue.offer(left);
                }
            }
        }
        return var;
    }
    List<Integer> var = new LinkedList();

    /**
     * 深度优先搜索,根右左模型。depth+1可以实时记录当前深度,深度如果与var中个数相同,就代表达到了新深度,存入该节点。该节点就是深度当层最右侧的节点。
     * @param root
     * @return
     */
    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root,0);
        return var;
    }

    public void dfs(TreeNode root,int depth) {
        if(root == null){
            return;
        }
        if(depth == var.size()){
            var.add(root.val);
        }
        dfs(root.right,depth+1);
        dfs(root.left,depth+1);
    }


}
