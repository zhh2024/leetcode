package partition;

import tree.binarytree.TreeNode;

/**
 * @Desc: 将有序数组转换为二叉搜索树
 * 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 *
 * 思路:
 * 因为是升序数组,二分,左边的就是左子树，右边的就是右子树。
 * 左右子树继续拆分,递归形成平衡二叉树。
 * @Author：zhh
 * @Date：2025/3/19 9:52
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        int[] nums = {-10,-3,0,5,9};
        TreeNode treeNode = sortedArrayToBST.sortedArrayToBST(nums);
        System.out.println(treeNode);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return digui(nums, 0, nums.length - 1);
    }

    public TreeNode digui (int[] nums, int left ,int right){
        if(left>right){
            return null;
        }
        int mid = left + (right - left)/2;
        TreeNode treeNode = new TreeNode();
        treeNode.left = digui(nums,left,mid -1);
        treeNode.right = digui(nums,mid +1 ,right);
        return treeNode;
    }
}
