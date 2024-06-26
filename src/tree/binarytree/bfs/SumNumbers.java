package tree.binarytree.bfs;

import tree.binarytree.TreeNode;

import java.util.LinkedList;

/**
 * @Desc: 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * <p>
 * 示例 2：
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * @Author：zhh
 * @Date：2024/6/24 14:03
 */
public class SumNumbers {
    public static void main(String[] args) {

    }


    /**
     * 时间复杂度O(n)
     * 思路: DFS（Depth-First Search）深度优先搜索,递归,每次进入递归计算值,遇到叶子节点进行累加。
     * @param root
     * @return
     */
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root,0);
        return sum;
    }
    public void dfs(TreeNode root,int number){
        if(root == null){
            return;
        }
        int num = number * 10 + root.val;
        if(root.left == null && root.right == null){
            sum += num;
        }
        dfs(root.left, num);
        dfs(root.right, num);
    }

    /**
     * 继续对上面优化, 回溯的时候返回叶子节点值,返回左子树+右子树,就是所有叶子节点累加的值。
     * @param root
     * @return
     */
    public int sumNumbers2(TreeNode root) {
        return  dfs2(root,0);
    }
    public int dfs2(TreeNode root,int number){
        if(root == null){
            return 0;
        }
        int sum = number * 10 + root.val;
        if(root.left == null && root.right == null){
            return sum;
        }
        return dfs2(root.left, sum) + dfs2(root.right, sum);
    }

    /**
     * 时间复杂度O(n),空间复杂度O(n)
     * 思路: 广度优先搜索,使用队列先进先出的特点,层次遍历二叉树,在遍历过程过,计算每个节点的值，存储到队列中。
     * 这两个队列的node和var是一一对应的。如果这个节点是叶子节点,就将值累加。
     * @param root
     * @return
     */
    public int sumNumbers3(TreeNode root) {
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> varQueue = new LinkedList<>();
        nodeQueue.offer(root);
        varQueue.offer(root.val);
        int sum = 0;
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            Integer var = varQueue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            //叶子节点,路径已走完
            if(left == null && right == null){
                sum +=  var;
            }
            if(left!=null){
                nodeQueue.offer(left);
                //在双亲上计算值
                varQueue.offer(var*10 + left.val);
            }
            if(right!=null){
                nodeQueue.offer(right);
                //在双亲上计算值
                varQueue.offer(var*10 + right.val);
            }
        }
        return sum;
    }

}
