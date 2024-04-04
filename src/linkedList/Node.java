package linkedList;

/**
 * @Desc: 链表节点
 * @Author：zhh
 * @Date：2024/3/11 21:18
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
