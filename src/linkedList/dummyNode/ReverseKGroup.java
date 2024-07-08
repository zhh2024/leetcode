package linkedList.dummyNode;

import linkedList.ListNode;

/**
 * @Desc: K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * @Author：zhh
 * @Date：2024/7/8 18:03
 */
public class ReverseKGroup {
    public static void main(String[] args) {

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode pre = dummyNode;
        //计算长度
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        //计算翻转几组
        int count = length / k;
        for (int i = 0; i < count; i++) {
            ListNode curr = pre.next;
            //开始穿针引线
            for (int j = 1; j < k; j++) {
                //curr不动,更改curr的next指向
                ListNode newHead = curr.next;
                curr.next = newHead.next;
                //重新定义head
                newHead.next = pre.next;
                pre.next = newHead;
            }
            //重新赋值pre
            pre = curr;
        }
        return dummyNode.next;
    }

    /**
     * 优化,无需计算链表长度,在遍历过程中判断。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup02(ListNode head, int k) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while (head != null) {
            //判断节点是否够k个。
            for (int i = 0; i < k; i++) {
                if (head == null) {
                    return dummyNode.next;
                }
                head = head.next;
            }
            ListNode curr = pre.next;
            //开始穿针引线
            for (int j = 1; j < k; j++) {
                //curr不动,更改curr的next指向
                ListNode newHead = curr.next;
                curr.next = newHead.next;
                //重新定义head
                newHead.next = pre.next;
                pre.next = newHead;
            }
            //重新赋值pre
            pre = curr;
        }
        return dummyNode.next;
    }
}
