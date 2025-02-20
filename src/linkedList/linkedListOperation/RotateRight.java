package linkedList.linkedListOperation;

import linkedList.ListNode;

/**
 * @Desc: 给你一个链表的头节点 head ,旋转链表，将链表每个节点向右移动 k 个位置。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 * @Author：zhh
 * @Date：2024/4/3 17:28
 */
public class RotateRight {
    public static void main(String[] args) {
        System.out.println(10%5);
    }

    /**
     * 时间复杂度O(n)
     * 解题思路: 假设长度为n的链表,旋转k次，k%n就是头节点要移动的位置
     * 1. 如果k%n == 0 就代表旋转后还是原位置就不发生旋转
     * 2. 找头节点移动后的位置没用，应该找头节点移动后形成新链表的尾,因为它要断开连接
     * 3. 新链表的尾在原链表的下标就是 n- k%n ,这样移动k%n 正好到达尾部就是新尾
     * 4. n- k%n 此下标的节点就要断开连接，原链表尾部指向原链表头部，形成新的链表，完成旋转
     *
     *
     * 新理解:
     * 原链表尾和头相连形成一个环，然后找到要断开的位置(长度-移动的位置 =  n- k%n),断开即可。
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return null;
        }
        int length = 1;
        ListNode curr = head;
        //先遍历获取头和尾节点和长度
        while (curr.next!=null){
            curr = curr.next;
            length++;
        }
        //要断链接的位置,旋转后的新尾部值就在此下标
        int index = length - (k % length);
        if(index == length){
            return head;
        }
        //找到新尾部的下标值
        ListNode newTail = head;
        for (int i = 1; i < index; i++) {
            newTail = newTail.next;
        }
        //新尾部的下一个值就是旋转后的头
        ListNode newHead = newTail.next;
        newTail.next = null;
        //原尾部再与原头连接起来
        curr.next = head;
        return newHead;
    }
}
