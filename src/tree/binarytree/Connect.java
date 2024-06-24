package tree.binarytree;

import linkedList.ListNode;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/6/18 17:21
 */
public class Connect {

    public static void main(String[] args) {
        Node node7 = new Node(7, null, null, null);
        Node node6 = new Node(6, null, null, null);
        Node node5 = new Node(5, null, null, null);
        Node node4 = new Node(4, null, null, null);
        Node node3 = new Node(3, null, node7, null);
        Node node2 = new Node(2, node4, node5, null);
        Node node1 = new Node(1, node2, node3, null);
        connect2(node1);
    }

    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node dummyNode = new Node();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                dummyNode.next = node;
                dummyNode = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    public static Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node head = root;
        while (head!=null){
            //构建哑节点
            Node dummyNode = new Node();
            Node pre = dummyNode;
            //遍历head
            Node tmp = head;
            while (tmp!=null){
                if(tmp.left!=null){
                    pre.next = tmp.left;
                    pre = tmp.left;
                }
                if(tmp.right!=null){
                    pre.next = tmp.right;
                    pre = tmp.right;
                }
                tmp = tmp.next;
            }
            //head重新赋值
            head = dummyNode.next;
        }
        return root;
    }



    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

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
