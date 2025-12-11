package tree.binarytree.bfs;

import java.util.LinkedList;
import java.util.Queue;

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
        connect(node1);
    }

    /**
     * 思路:
     * 1. 层次遍历，首先要想到用双向队列结构去承载，因为先进先出的机制，确保了每一层先执行，然后再将下一层插入进去，是有序的，一直可以循环结束，直至队列为空。
     * 2. 区分层次，获取队当前对列的长度，就是每一层的元素了。然后内部再写个循环，这样就可以隔离添加后的新元素了，可以区分层次。
     * 3. 串联起来, 在内部循环里面，因为这个循环的是当前层的元素，然后next串联起来即可。
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            Node last = null;
            for (int i = 0; i < size; i++) {
                Node f = queue.poll();
                if(f.left!=null){
                    queue.offer(f.left);
                }
                if(f.right != null){
                    queue.offer(f.right);
                }
                if (i != 0) {
                    last.next = f;
                }
                last = f;
            }
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
