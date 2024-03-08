package linkedList;

/**
 * @Desc:给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * @Author：zhh
 * @Date：2024/3/7 17:20
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int high =0;
        ListNode head = null;
        ListNode tail = null;
        while (l1!=null || l2!=null){
            int sum;
            if(l1 == null){
                sum = high + l2.val;
                l2 = l2.next;
            }else if(l2 == null){
                sum = high + l1.val;
                l1 = l1.next;
            }else {
                sum = l1.val + l2.val + high;
                l1 = l1.next;
                l2 = l2.next;
            }
            int yushu;
            if(sum >= 10){
                high = 1;
                yushu = sum % 10;
            }else {
                high = 0;
                yushu= sum;
            }
            if(head == null){
                head = tail = new ListNode(yushu);
            }else {
                tail.next = new ListNode(yushu);
                tail = tail.next;
            }
        }
        if(high !=0){
            tail.next = new ListNode(high);
        }
        return head;
    }

    /**
     * 未优化版本
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers02(ListNode l1, ListNode l2) {
        int high =0;
        ListNode head = null;
        ListNode tail = null;
        ListNode temp;
        while (l1!=null && l2!=null){
            int sum = l1.val + l2.val + high;
            int yushu;
            if(sum >=10){
                high=1;
                yushu = sum % 10;
            }else {
                high = 0;
                yushu= sum;
            }
            if(head == null){
                head = new ListNode(yushu);
                tail = head;
            }else {
                temp = new ListNode(yushu);
                tail.next = temp;
                tail = temp;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 !=null){
            int sum = l1.val + high;
            int yushu;
            if(sum >=10){
                high=1;
                yushu = sum % 10;
            }else {
                high = 0;
                yushu= sum;
            }
            temp = new ListNode(yushu);
            tail.next = temp;
            tail = temp;
            l1 = l1.next;
        }
        while (l2 != null){
            int sum = l2.val + high;
            int yushu;
            if(sum >=10){
                high=1;
                yushu = sum % 10;
            }else {
                high = 0;
                yushu= sum;
            }
            temp = new ListNode(yushu);
            tail.next = temp;
            tail = temp;
            l2 = l2.next;
        }
        if(high !=0){
            temp = new ListNode(high);
            tail.next = temp;
        }
        return head;
    }

}
