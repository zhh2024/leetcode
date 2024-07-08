package linkedList.doublePoint;

import linkedList.ListNode;

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

    /**
     * 时间复杂度O(m+n) 空间复杂度O(1)
     * 解题思路:
     * 1. 使用数组双指针思想,链表与数组不一样的是,插入到原数组中，原数组后面的数据往后移动即可
     * 2. 链表插入到原链表中,需要pre节点指向当前插入的节点,当前插入节点再指向原链表当前节点,需要pre指针
     * 3. pre指针有可能是null,因为一开始就插入头节点呢？所以需要虚节点
     * @param list1
     * @param list2
     * @return
     */
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
                //list1大 ,list2右移
                list2 = list2.next;
            }else {
                //list1小,插入到list2前面, list1指向list2,list1右移 ,所以需要list2的pre节点
                ListNode ln = list1.next;
                pre.next = list1;
                list1.next = list2;
                list1 = ln;
            }
            //不管是否插入,每次遍历,pre都是要往前走一步的
            pre = pre.next;
        }
        pre.next = list1 == null?list2:list1;
        return dummyNode.next;
    }

    /**
     * 上面的代码是会跳过该节点继续遍历,pre是平行的
     * 思路: 遍历一直往前,pre上下穿梭,更改遍历前的指向。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists03(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode();
        ListNode pre = dummyNode;
        while (list1!=null && list2!=null){
            if(list1.val < list2.val){
                pre.next = list1;
                list1 = list1.next;
            }else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }
        pre.next = list1 ==null?list2:list1;
        return dummyNode.next;
    }

    public static ListNode mergeTwoLists02(ListNode list1, ListNode list2){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        if (list1.val > list2.val) {
            list2.next = mergeTwoLists02(list1,list2.next);
            return list2;
        }else {
            list1.next = mergeTwoLists02(list1.next,list2);
            return list1;
        }
    }


}
