package linkedList;

import java.util.HashSet;

/**
 * @Desc: 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。
 * 仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * @Author：zhh
 * @Date：2024/3/7 14:42
 */
public class HasCycle {

    public static void main(String[] args) {
        ListNode head = null;
        ListNode tail = null;
        ListNode temp;
        //构造一个链表
        for (int i = 1; i <= 4; i++) {
            if(head == null){
                head = new ListNode(i);
                tail = head;
            }else {
                temp = new ListNode(i);
                tail.next = temp;
                tail = temp;
            }
        }
        //设计一个环
        temp = head;
        for (int i = 0; i < 2; i++) {
            temp = temp.next;
        }
        tail.next = temp;
    }

    /**
     * 解题思路: 利用hashMap做判断是否重复
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> nodes = new HashSet<>();
        while (head != null){
            if (nodes.contains(head.next)) {
                return true;
            }
            nodes.add(head);
            head = head.next;
        }
        return false;
    }
}
