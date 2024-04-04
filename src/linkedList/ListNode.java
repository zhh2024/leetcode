package linkedList;

/**
 * @Desc: 链表节点
 * @Author：zhh
 * @Date：2024/3/7 17:27
 */
 public  class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next;
    }
}
