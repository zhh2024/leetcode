package linkedList;

/**
 * @Desc: 链表常用操作
 * @Author：zhh
 * @Date：2024/4/4 21:25
 */
public class ListDemo {

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
}
