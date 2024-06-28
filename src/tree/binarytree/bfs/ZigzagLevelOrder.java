package tree.binarytree.bfs;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Desc: 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * <p>
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 * <p>
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 * @Author：zhh
 * @Date：2024/6/25 15:40
 */
public class ZigzagLevelOrder {
    public static void main(String[] args) {

    }

    /**
     * 时间复杂度O(n) 空间复杂度O(n)
     * 思路: 广度优先搜索,设置一个标志位,每层遍历完,更改标志位,根据标志位的不同区分从左到右遍历,还是从右到左遍历。
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> varList = new LinkedList<>();
        if(root == null){
            return varList;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()){
            List<Integer> vars = new LinkedList<>();
            int size = queue.size();
            if(flag){
               //从右往左取值
                for (int i = size-1; i >= 0 ; i--) {
                    TreeNode node = queue.get(i);
                    vars.add(node.val);
                }
            }else {
               //从左往右取值
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.get(i);
                    vars.add(node.val);
                }
            }
            //放下一层值
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            varList.add(vars);
            flag = !flag;
        }
        return varList;
    }

    /**
     * 时间复杂度O(n) 空间复杂度O(n)
     * 思路: 与上面思路一致,只不过利用双端队列的数据结构简化了从右往左或从左往右遍历,根据标志位的不同将值存储到双端队列中first,fist
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> varList = new LinkedList<>();
        if(root == null){
            return varList;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()){
            LinkedList<Integer> deque = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                //放值的时候可以放到双端队列
                if(flag){
                    //头插,从右到左
                    deque.offerFirst(node.val);
                }else {
                    //尾插,从左到右
                    deque.offerLast(node.val);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            varList.add(deque);
            flag = !flag;
        }
        return varList;
    }
}
