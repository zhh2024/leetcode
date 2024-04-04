package linkedList;

/**
 * @Desc: 分隔链表
 * 给你一个链表的头节点head和一个特定值x,请你对链表进行分隔,使得所有小于x的节点都出现在大于或等于x的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * @Author：zhh
 * @Date：2024/4/4 13:57
 */
public class Partition {
    public static void main(String[] args) {

    }

    /**
     * 时间复杂度O(n)
     * 解题思路: 定义双指针,pre指针和curr指针
     * 1. 定义一个虚节点指向head,并赋值给pre指针,head赋值给curr指针,初始化完毕
     * 2. curr开始遍历,如果 curr的值小于特定值x,pre往前走
     * 3. 如果curr的值大于或等于特定值x，记录此节点,pre不动,curr继续遍历
     * 4. 记录下来的节点就是分割后链表的pre节点,继续遍历curr
     * 5. 后续还是遇到小于特定值x,将当前节点移到分割前链表指向记录下来的节点,而分割后链表的pre指向当前节点的next节点
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        //分割前链表的pre
        ListNode pre = dummyNode;
        ListNode curr = head;
        ListNode xNode = null;
        //记录分割节点
        while (curr!=null){
            if(curr.val < x){
                pre = pre.next;
            }else {
                xNode = curr;
                curr = curr.next;
                break;
            }
            curr = curr.next;
        }
        //分割后链表的pre
        ListNode newPre = xNode;
        //继续遍历分割节点后的数据
        while (curr != null){
            if(curr.val < x){
                //当前节点要移动,记录当前节点的下一个节点
                ListNode next = curr.next;
                //分割后链表的pre 指向当前节点的下一个节点
                newPre.next = next;
                //pre指向curr ,curr指向特定值,pre向前走
                curr.next = xNode;
                pre.next = curr;
                pre = pre.next;
                //下次遍历
                curr = next;
            }else {
                curr = curr.next;
                newPre = newPre.next;
            }
        }
        return dummyNode.next;
    }

    /**
     * 解题思路: 新链表
     *    1. 定义两个哨兵节点,新成两个新链表的head节点
     *    2. 遍历原链表,根据条件尾插法放入新链表中
     *    3. 断开新链表的尾节点链接，因为原链表还有next
     *    4. 两个新链表合并
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition02(ListNode head, int x) {
        ListNode small = new ListNode();
        ListNode smallHead = small;
        ListNode large = new ListNode();
        ListNode largeHead = large;
        ListNode curr = head;
        //尾插法分割成两个链表,小链表和大链表
        while (curr!=null){
            if(curr.val < x){
                small.next = curr;
                small = small.next;
            }else {
                large.next = curr;
                large = large.next;
            }
            curr = curr.next;
        }
        //断开small和large的尾节点连接
        small.next = null;
        large.next = null;
        //merge
        small.next = largeHead.next;
        return smallHead.next;
    }
}
