package linkedList;

/**
 * @Desc: 链表基础操作
 * @Author：zhh
 * @Date：2024/4/4 21:25
 */
public class ListBasicOperation {
    public static void main(String[] args) {
        ListNode n5 = new ListNode(5,null);
        ListNode n4 = new ListNode(4,n5);
        ListNode n3 = new ListNode(3,n4);
        ListNode n2 = new ListNode(2,n3);
        ListNode n1 = new ListNode(1,n2);
        ListNode newHead = reverse02(n1);
    }

    private ListNode head;
    private ListNode tail;
    /**
     * 尾插法
     * 先判断是否存在头节点不存在就初始化head
     * 新节点指向旧尾,新节点成为新尾
     *
     * @param value
     */
    public  void addLast(int value){
        if(head == null){
            head = tail = new ListNode(value);
        }else {
            ListNode listNode = new ListNode(value);
            tail.next = listNode;
            tail = listNode;
        }
    }

    /**
     * 头插法
     * 先判断是否存在头节点不存在就初始化head
     * 新节点指向旧头,新节点成为新头
     * @param value
     */
    public  void addFirst(int value){
        if(head == null){
            head = tail = new ListNode(value);
        }else {
            ListNode listNode = new ListNode(value);
            listNode.next = head;
            head = listNode;
        }
    }

    /**
     * 递归遍历链表
     * @param node
     * @return
     */
    public  ListNode digui(ListNode node){
        if(node ==null){
            return null;
        }
        node.next = digui(node.next);
        return node;
    }

    /**
     * 迭代器遍历
     * @param head
     */
    public  void testFor(ListNode head){
        ListNode curr = head;
        while (curr!=null){
            curr = curr.next;
        }
    }

    /**
     * 移除 需要考虑是否是头尾节点
     */
    public void remove(){

    }

    public void dummyNode(ListNode node){
        ListNode dummyNode = new ListNode();
        dummyNode.next = node;
        ListNode pre = dummyNode;
    }

    /**
     * 定义一个哨兵节点,遍历原链表
     * 如果满足条件就构成新链表,head是哨兵节点
     * @param node
     */
    public void sentry(ListNode node){
        ListNode sentry = new ListNode();
        ListNode curr = head;
        while (curr!=null){
            if(curr.val >5){
                sentry.next = curr;
                sentry = sentry.next;
            }
            curr = curr.next;
        }
    }

    /**
     * 链表翻转,普通翻转,只针对一个节点
     */
    public static ListNode reverse01(ListNode node){
        //pre是一直在变化的
        ListNode pre = null;
        while (node != null){
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 链表翻转,穿针引线,至少两个节点
     */
    public static ListNode reverse02(ListNode node){
        //pre在此时是不可变的,就不需要dummyNode
        ListNode pre = new ListNode();
        pre.next = node;
        ListNode curr = pre.next;
        while (curr != null && curr.next != null){
            //抽出next作为head。curr不变,curr.next一直在变化变化
            ListNode newHead = curr.next;
            curr.next = newHead.next;
            //pre指向新head
            newHead.next = pre.next;
            pre.next = newHead;
        }
        return pre.next;
    }
}
