package linkedList;

import java.util.HashMap;
import java.util.HashSet;

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

    }

    /**
     * 时间复杂度 O(n) 空间复杂度O(n)
     * 解题思路:
     *  1. 进行深拷贝,难点就是有个random引用,遍历过程中无法获取random指向的节点
     *     而且新的node节点也没有random值
     *  2. 循环遍历,尾插法构成新的链表,在此过程中,新老节点顺序一一对应,此时可以借助下标来做映射
     *  3. 为什么要做映射,因为新节点没有random引用,random引用只在老节点有
     *     所以得通过老节点获取random对应的下标
     *     此时这个下标映射到新链表就是新节点的random引用指向。
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        //用下标来做映射关系
        HashMap<Node, Integer> oldMap = new HashMap<>();
        HashMap<Integer, Node> newMap = new HashMap<>();
        Node newHead = null;
        Node newTail = null;
        int index = 0;
        Node curr = head;
        //构成新链表
        while (head!=null){
            Node node;
            if(newHead == null){
                node = new Node(head.val);
                newHead = newTail = node;
            }else {
                node = new Node(head.val);
                newTail.next = node;
                newTail = node;
            }
            oldMap.put(head,index);
            newMap.put(index,node);
            head = head.next;
            index++;
        }
        //新链表进行random赋值,与存放random的老节点进行下标映射
        Node newCurr = newHead;
        while (curr!=null){
            Node random = curr.random;
            Integer i = oldMap.get(random);
            newCurr.random = newMap.get(i);
            newCurr = newCurr.next;
            curr = curr.next;
        }
        return newHead;
    }


}
