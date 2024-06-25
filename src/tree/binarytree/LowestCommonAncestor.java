package tree.binarytree;

/**
 * @Desc: 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * <p>
 * 示例 2：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 示例 3：
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * @Author：zhh
 * @Date：2024/6/24 16:45
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {

    }
    TreeNode node = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return node;
    }

    /**
     * 时间复杂度O(n)
     * 思路: 1. 先将树看成一个节点,如果节点是p或者q,那么该节点就是公共祖先
     *      2. 再将树看成左右根三个节点,如果根不是p,q。左右子树各自存在p或者q,根就是公共祖先。
     *                          如果根是p或者q。左右子树有一个存在p或者q,根就是公共祖先。
     *      3. 开始递归,左右根,由底往上,将结果回溯,找到第一个公共祖先就是最近公共祖先。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public Boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if (root == null){
            return false;
        }
        //递归左右根顺序，判断以下两种情况
        Boolean leftTree = dfs(root.left, p, q);
        Boolean rightTree = dfs(root.right, p, q);
        //1. 如果根是p或者q,那么左右子树存在p或者q,最近公共祖先就是root
        if( (root == p || root == q ) && (leftTree || rightTree) ){
            //递归从底向上,获取最深最近的公共祖先,防止被上层覆盖。
            if(node == null){
                node = root;
            }
        }
        //2. 如果根不是p和q,那么左右子树存在p和q,最近公共祖先就是root
        if(root !=p && root !=q && leftTree && rightTree){
            if(node == null){
                node = root;
            }
        }
        //回溯结果
        if(root == p || root == q){
            return true;
        }else {
            //不能返回false,root既然不是p,q。应该返回左子树和右子树的结果,有可能左子树和右子树存在p,q。
            return leftTree || rightTree;
        }
    }
}
