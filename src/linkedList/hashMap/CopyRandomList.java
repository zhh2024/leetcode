package linkedList.hashMap;

import linkedList.ListNode;
import linkedList.Node;

import java.util.HashMap;

/**
 * @Desc: 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 *
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 *
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 *
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 *
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 * @Author：zhh
 * @Date：2024/3/11 21:15
 */
public class CopyRandomList {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node head = node1;
        for (int i = 2; i < 4; i++) {
            Node p = new Node(i);
            node1.next = p;
            node1 = p;
        }
        Node node = copyRandomList04(head);
        System.out.println(node);
    }


    /**
     * 不能将val作为key,因为val不唯一,需要寻找唯一的值来做映射
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        HashMap<Integer, Node> map = new HashMap<>();

        Node dummyNode = new Node(-1);
        Node tail = dummyNode;
        Node curr = head;
        while (curr != null){
            int val = curr.val;
            Node newNode = new Node(val);
            tail.next = newNode;
            tail = newNode;
            map.put(curr.val,newNode);
            curr = curr.next;
        }

        while (head != null){
            Node node = map.get(head.val);
            Node random = head.random;
            if(random != null){
                node.random = map.get(random.val);
            }else {
                node.random = null;
            }
            head = head.next;
        }
        return dummyNode.next;
    }

    /**
     * 时间复杂度 O(n) 空间复杂度O(n)
     * 解题思路: 老Node作为key
     *  1. 进行深拷贝,难点就是有个random引用,遍历过程中无法获取random指向的节点
     *     而且新的node节点也没有random值
     *  2. 循环遍历,尾插法构成新的链表,在此过程中,新老节点一一对应
     *  3. 为什么要做映射,因为新节点没有random引用,random引用只在老节点有,所以得通过老节点获取random
     *     老random也是老节点,map中映射对应新的节点
     *
     *  难点: 以下标作为唯一key映射,因为两个链表,所以需要map做映射,老Node作为key
     * @param head
     * @return
     */
    public Node copyRandomList02(Node head) {
        HashMap<Node, Node> map = new HashMap<>();

        Node dummyNode = new Node(-1);
        Node tail = dummyNode;
        Node curr = head;
        while (curr != null){
            int val = curr.val;
            Node newNode = new Node(val);
            tail.next = newNode;
            tail = newNode;
            map.put(curr,newNode);

            curr = curr.next;
        }

        while (head != null){
            Node node = map.get(head);
            Node random = head.random;
            if(random != null){
                node.random = map.get(random);
            }else {
                node.random = null;
            }
            head = head.next;
        }
        return dummyNode.next;
    }

    /**
     * 简化copyRandomList02,取消掉尾插法,构成next放到第二个循环
     * @param head
     * @return
     */
    public Node copyRandomList03(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null){
            Node newNode = new Node(curr.val);
            map.put(curr,newNode);
            curr = curr.next;
        }
        curr = head;
        while (curr != null){
            Node node = map.get(curr);
            node.next = map.get(curr.next);

            Node random = curr.random;
            if(random != null){
                node.random = map.get(random);
            }else {
                node.random = null;
            }
            curr = curr.next;
        }
        return map.get(head);
    }


    /**
     * 本质和copyRandomList03一样,第一次递归构成新链表,并且放入map 。第二次递归赋值random
     * 时间复杂度 O(n) 空间复杂度O(n)
     * 解题思路: 回溯+哈希表
     */
    static HashMap<Node, Node> map = new HashMap<>();
    public static Node copyRandomList04(Node head) {
        if(head == null){
            return null;
        }
        if (!map.containsKey(head)) {
            Node newNode = new Node(head.val);
            map.put(head,newNode);
            //先进入next递归,相当于遍历了一次,将新链表节点都创建完毕
            newNode.next = copyRandomList04(head.next);
            //此时是在新链表的尾节点往头节点进行遍历,所以是回溯,此时对象都已经创建,就不能再进入next递归,直接获取节点即可
            //但是传入的是老节点,需要的是新链表节点
            //所以需要定义一个全局变量的map来存储新老节点映射关系
            newNode.random = copyRandomList04(head.random);
        }
        return map.get(head);
    }





























}
