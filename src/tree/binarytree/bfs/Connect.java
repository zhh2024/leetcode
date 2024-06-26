package tree.binarytree.bfs;

import java.util.LinkedList;

/**
 * @Desc: 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
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

    /**
     * 思路: 层次遍历,初始化一个哑节点,指向root, 遍历这个链表,取出left和right节点。重新初始话哑节点，指向left和right,构成新链表。将这个新链表的next就是头
     * 传给head,继续遍历这个新链表，直至新链表为null。
     * @param root
     * @return
     */
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
