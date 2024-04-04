package linkedList;

/**
 * @Desc: 链表节点
 * @Author：zhh
 * @Date：2024/3/7 17:27
 */
 public  class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next;
    }
}
