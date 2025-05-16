package partition;

import linkedList.ListNode;

/**
 * @Desc: 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * @Author：zhh
 * @Date：2025/3/19 10:15
 */
public class SortList {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(3,null);
        ListNode listNode2 = new ListNode(1,listNode1);
        ListNode listNode3 = new ListNode(2,listNode2);
        ListNode listNode4 = new ListNode(4,listNode3);
        SortList sortList = new SortList();
        ListNode listNode = sortList.sortList(listNode4);
        System.out.println(listNode);
    }

    /**
     * 实现思路:
     * 与数组归并排序是一样的,
     * 不一样的点
     * 1. 就是链表二分不需要下标,只传入头结点和尾节点，即可确定mid节点
     * 2. 递归结束条件也不一样,需要判断头尾节点
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return sortList(head,null);
    }

    public ListNode sortList(ListNode head, ListNode tail){
        //递归的终止条件是链表的节点个数小于或等于 1，即当链表为空或者链表只包含 1 个节点时，不需要对链表进行拆分和排序。
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        //找mid
        ListNode tmp = head;
        int length = 1;
        while (tmp != null){
            tmp = tmp.next;
            if(tmp == tail){
                break;
            }
            length++;
        }
        int mid = length/2;
        ListNode midHead = head;
        for (int i = 0; i < mid; i++) {
            midHead = midHead.next;
        }
        //二分递归
        ListNode head1 = sortList(head, midHead);
        ListNode head2 = sortList(midHead, tail);
        return merge(head1,head2);
    }

    /**
     * 优化找mid,快慢指针,快指针速度是慢指针速度的二倍,快指针到了,慢指针就是mid
     * @param head
     * @param tail
     * @return
     */
    public ListNode sortList02(ListNode head, ListNode tail){
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        //找mid
        ListNode low =  head;
        ListNode fast = head;
        while (fast != tail){
            low = low.next;
            fast = fast.next;
            if(fast != tail){
                fast = fast.next;
            }
        }
        //二分递归
        ListNode head1 = sortList(head, low);
        ListNode head2 = sortList(low, tail);
        return merge(head1,head2);
    }

    public ListNode merge(ListNode head1,ListNode head2){
        ListNode dummyNode = new ListNode();
        ListNode pre = dummyNode;
        while (head1!=null && head2!=null){
            if(head1.val < head2.val){
                pre.next = head1;
                head1 = head1.next;
            }else {
                pre.next = head2;
                head2 = head2.next;
            }
            pre = pre.next;
        }
        pre.next = head1 ==null?head2:head1;
        return dummyNode.next;
    }
}
