package tree.binarytree;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/6/18 17:21
 */
public class Connect {

    public static void main(String[] args) {

    }

    public static Node connect(Node root) {
        if(root == null){
            return null;
        }
        digui(root);
        digui2(root.left,root.right);
        return root;
    }

    public static Node digui(Node root){
        if(root == null){
            return null;
        }
        Node leftNode = digui(root.left);
        Node rightNode = digui(root.right);
        if(leftNode != null){
            leftNode.next = rightNode;
        }
        return root;
    }

    public static void digui2(Node leftTree,Node rightTree){
        leftTree.next = rightTree;
        if(leftTree.right != null && rightTree.left != null){
            digui2(leftTree.right,rightTree.left);
        }else if(leftTree.right != null && rightTree.right != null){
            digui2(leftTree.right,rightTree.right);
        }else if(leftTree.left != null && rightTree.left != null){
            digui2(leftTree.left,rightTree.left);
        }else if(leftTree.left != null && rightTree.right != null){
            digui2(leftTree.left,rightTree.right);
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
