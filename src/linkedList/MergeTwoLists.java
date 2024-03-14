package linkedList;

/**
 * @Desc: 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * @Author：zhh
 * @Date：2024/3/14 22:07
 */
public class MergeTwoLists {
    public static void main(String[] args) {

    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2){
        //选定list2作为被插入链表 插入规则list1小,插入到list2前面, list1指向list2,list1右移 ,所以需要list2的pre节点
        //但pre节点有可能为null,所以需要虚节点
        ListNode dummyNode = new ListNode(-1);
        //被覆盖的场景,在于list1头节点>list2头节点
        dummyNode.next = list2;
        //被覆盖的场景,list2右移,pre重置
        ListNode pre = dummyNode;
        while (list1!=null && list2!=null){
            if (list1.val > list2.val) {
                pre = list2;
                //list1大 ,list2右移
                list2 = list2.next;
            }else {
                //list1小,插入到list2前面, list1指向list2,list1右移 ,所以需要list2的pre节点
                ListNode ln = list1.next;
                pre.next = list1;
                pre = list1;
                list1.next = list2;
                list1 = ln;
            }
        }
        pre.next = list1 == null?list2:list1;
        //第一种情况,list2为null,此时 ListNode pre = dummyNode;  pre.next = list1; 返回的就是list1头节点
        //第二种情况，list2遍历结束,pre被重新赋值,返回的应该是list2头节点  dummyNode.next = list2;
        //第三种情况，一开始list1>list2,应该返回list1头节点，ListNode pre = dummyNode; pre.next = list1;
        //          此时dummyNode的next被赋值为list1头节点 后pre被赋值，dummyNode.next就是 list1头节点
        //第四种情况，一开始list1<list2,返回list2头节点,    pre = list2;pre被赋值, dummyNode.next = list2;
        return dummyNode.next;
    }
}
