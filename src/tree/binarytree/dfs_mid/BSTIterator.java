package tree.binarytree.dfs_mid;

import tree.TreeNode;

import java.util.LinkedList;

/**
 * @Desc: 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
 * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 * <p>
 * 示例：
 * 输入
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * 输出
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 * 解释
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // 返回 3
 * bSTIterator.next();    // 返回 7
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 9
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 15
 * bSTIterator. hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 20
 * bSTIterator.hasNext(); // 返回 False
 * @Author：zhh
 * @Date：2024/6/24 14:54
 */
public class BSTIterator {
    public static void main(String[] args) {

    }

    /**
     * 思路: 深度优先搜索,实现方式有两种,递归和栈,因为要实现API调用,所以利用栈将node存于栈中,才可以实现调用
     * 栈的实现思路,先迭代left存入栈中,如果left为null出栈,找right。 将right看作新的子树,继续迭代left存入栈中。
     * 出栈的时候返回值，就是中序遍历。入栈的时候返回值就是前序遍历。
     * @param root
     */
    LinkedList<TreeNode> stack = new LinkedList();
    public BSTIterator(TreeNode root) {
        if(root == null){
            return;
        }
        //初始化栈
        while (root != null ){
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        //出栈
        TreeNode node = stack.pop();
        //进栈
        TreeNode root = node.right;
        while (root != null){
            stack.push(root);
            root = root.left;
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
