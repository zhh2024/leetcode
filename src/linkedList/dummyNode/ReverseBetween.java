package linkedList.dummyNode;


import linkedList.ListNode;

/**
 * @Desc: 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * @Author：zhh
 * @Date：2024/3/8 10:50
 */
public class ReverseBetween {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 1; i < 2; i++) {
            ListNode tmp = new ListNode(i);
            p.next = tmp;
            p = tmp;
        }
        System.out.println(1);
        ListNode listNode = reverseBetween02(head, 1, 2);
        System.out.println(listNode);
    }

    /**
     * 解题思路:
     *  1.定位到要翻转的边界
     *  2.翻转范围进行头插法,定位head和tail
     *  3.再将边界与head和tail拼接起来
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int index = 1;
        ListNode beginNode = head;
        ListNode leftNode = null;
        ListNode rightNode = null;
        ListNode reverseHead = null;
        ListNode reverseTail = null;
        while (head != null){
            ListNode nextNode = head.next;
            if (index+1 == left) {
                leftNode = head;
            }
            if(index-1 == right){
                rightNode = head;
                break;
            }
            if(index >= left && index <= right){
                //reverse
                if(reverseTail == null){
                    reverseTail = reverseHead = head;
                }else {
                    head.next = reverseHead;
                    reverseHead = head;
                }
            }
            head = nextNode;
            index++;
        }
        if (leftNode != null) {
            leftNode.next = reverseHead;
        }
        reverseTail.next = rightNode;
        return leftNode == null?reverseHead:beginNode;
    }

    /**
     * 解题思路: 穿针引线
     * 1. 遍历找到要翻转范围的前一个节点pre
     * 2. 翻转范围的第一个节点就是翻转后的最后一个节点,那么这个节点不应该变化,它的next节点应该在遍历中变化,这个节点为curr节点
     * 3. 遍历这个范围,将curr的下一个节点应为pre.next也就是头节点,curr重新指向curr的下一个节点的下一个节点
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween02(ListNode head, int left, int right) {
        //left==1 的时候,pre是没有的，所以设定一个虚节点。
        ListNode dummyNode  = new ListNode(-1);
        //left==1的时候,并且只有一个节点的情况下,此时pre.next就是null,所以需要初始化赋值,等后续穿针引线再进行覆盖。
        dummyNode.next = head;
        //left>1的时候,pre被覆盖,找到真正的pre
        //left==1的时候,pre就是dummyNode
        ListNode pre = dummyNode;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        //进行穿针引线,pre.next指向头节点,curr重新指向下一个节点,curr始终不变
        ListNode curr = pre.next;
        for (int i = left; i < right; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        //left==1的时候, 分两种情况 节点只有一个,dummyNode.next 就是head
        //                        节点有多个, dummyNode.next 就是翻转后的头节点
        //left>1的时候, dummyNode.next 就是head
        return dummyNode.next;
    }

}
