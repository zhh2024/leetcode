package linkedList.dummyNode;

import linkedList.ListNode;

/**
 * @Desc: 删除排序链表中的重复元素 II
 *
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * @Author：zhh
 * @Date：2024/3/31 18:00
 */
public class DeleteDuplicates {
    public static void main(String[] args) {

    }

    /**
     * 时间复杂度O(n) 空间复杂度O(1)
     * 解题思路: 双指针,定义 pre 和 curr两个指针
     * 1. pre在head就被去重的情况下,pre是null,所以需要在head前定义一个虚节点,pre赋值虚节点,curr为head节点,双指针初始化完毕。
     * 1. curr与 curr.next 比较 如果不一样,两个指针同时走
     * 2. curr与 curr.next 比较 如果一样,需要curr开始往后遍历去重,直至遇到不一样的值,pre指向curr
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head){
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            if(next == null){
                break;
            }
            if(next.val != curr.val){
                //下一个值与当前值不一样,往前走一步
                curr = curr.next;
                pre = pre.next;
            }else {
                //下一个值与当前值一样去重,找到不一样的新值
                while (curr != null && curr.val == next.val){
                    curr = curr.next;
                }
                //重新指向新值
                pre.next = curr;
            }
        }
        return dummyNode.next;
    }
}
