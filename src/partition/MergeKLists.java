package partition;

import linkedList.ListNode;

/**
 * @Desc: 合并 K 个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 * @Author：zhh
 * @Date：2025/3/20 14:03
 */
public class MergeKLists {

    public static void main(String[] args) {

    }

    /*
     * 实现思路: 归并排序
     * 与数组不一样的是,两个数组合并 ,而链表只需要两个head就可以代表两组链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        return mergeKLists(lists,0,lists.length-1);
    }

    public ListNode mergeKLists(ListNode[] lists , int left  ,int right) {
        if(left >= right){
            return lists[right];
        }
        int mid = left + (right - left) / 2;
        ListNode head1 = mergeKLists(lists, left, mid);
        ListNode head2 = mergeKLists(lists, mid + 1, right);
        return merge(head1,head2);
    }

    public ListNode merge(ListNode head1, ListNode head2){
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
