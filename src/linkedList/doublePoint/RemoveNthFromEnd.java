package linkedList.doublePoint;

import linkedList.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Desc: 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * @Author：zhh
 * @Date：2024/3/24 17:29
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {

    }

    /**
     * 时间复杂度O(n)+O(n)
     * 解题思路:
     * 1. 遍历一次求出size
     * 2. 再遍历一次,size--,等size == n的时候，当前节点就是要删除的节点
     * 3. 需要pre节点来指向删除节点的next节点,所以需要dummyNode节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode curr = head;
        while (curr!=null){
            curr = curr.next;
            size++;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        while (head!=null){
            if(size == n){
                pre.next = head.next;
                head.next = null;
            }else {
                pre = head;
            }
            head = head.next;
            size--;
        }
        return dummyNode.next;
    }

    /**
     * 时间复杂度O(n)+O(n) 空间复杂度O(n)
     * 解题思路: 利用栈的先进后出,倒数就变成了正数
     * 1. 先将node放入栈中,因为有可能第一个节点被删除(此时栈中就是null,会空指针异常)，所以需要虚节点也入栈
     * 2. 进行正序遍历,将n前面的都出栈,此时栈中first节点就是被删除节点的pre节点
     * 3. 找到被删除节点的pre节点后,此时pre.next = pre.next.next 就完成了删除
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        Deque<ListNode> stack = new LinkedList<>();
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode curr = dummyNode;
        //虚节点也入栈
        while (curr!=null){
            stack.push(curr);
            curr = curr.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode peek = stack.peek();
        //peek就不可能为null,如果是第一个节点被删除,peek此时就是虚节点。
        peek.next = peek.next.next;
        //如果是第一个节点被删除,peek此时就是虚节点。dummyNode.next 就是第二个节点，如果不是第一个节点被删除，dummyNode.next就是head
        return dummyNode.next;
    }

    /**
     * 时间复杂度O(n)
     * 解题思路: 使用快慢指针,删除链表的倒数第 n 个结点,快指针 = 慢指针 + n ,这样快指针遍历结束，慢指针所指的下标就是被删除节点的前一个节点
     * 1. 利用快指针 = 慢指针 + n,先遍历初始化快指针
     * 2. 循环快指针,循环中慢指针和快指针一起走,直至快指针遍历结束
     * 3. 此时最终慢指针的值就是删除节点的前一个节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n){
        ListNode fastCurr = head;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode lowCurr = dummyNode;
        //先定位到fastCurr的位置
        for (int i = 0; i < n; i++) {
            fastCurr =fastCurr.next;
        }
        //然后快慢指针一起走
        while (fastCurr!=null){
            fastCurr = fastCurr.next;
            lowCurr = lowCurr.next;
        }
        //此时lowCurr就是要删除的节点的前一个节点
        lowCurr.next = lowCurr.next.next;
        return dummyNode.next;
    }
}
