package tree.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/6/13 11:05
 */
public class IsSameTree {

    public static void main(String[] args) {

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<TreeNode> pNodeList = new ArrayList<>();
        List<TreeNode> qNodeList = new ArrayList<>();
        digui(pNodeList,p);
        digui(qNodeList,q);
        if(pNodeList.size() != qNodeList.size()){
            return false;
        }
        for (int i = 0; i < pNodeList.size(); i++) {
            if (pNodeList.get(i).val != qNodeList.get(i).val) {
                return false;
            }
        }
        return true;
    }

    public void digui(List<TreeNode> pNodeList ,TreeNode node){
        if(node == null){
            return;
        }
        digui(pNodeList,node.left);
        digui(pNodeList,node.right);
        pNodeList.add(node);
    }
}
