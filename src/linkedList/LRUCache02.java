package linkedList;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Desc: 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 *       实现 LRUCache 类：
         LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
         int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
         void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
         如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，
         则应该 逐出 最久未使用的关键字。
         函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。


      解题思路: 哈希表+双向链表 来实现
               一开始想的是 哈希表存数据, linekedList用来更改优先级,每get一次 删除掉并放到head,达到容量移除掉尾节点
              缺点: 使用linkedList,remove时间复杂度是O(n)
              优化: 直接remove(Node) 作为双向链表 时间复杂度就是O(1)
                   1. linkedList封装太深,需要自定义双向链表
                   2. 自定义Node存储 key,value ,哈希表存储 key,Node
                   3. 这样删除时,就可以通过key获取Node并删除 时间复杂度O(1)
                   4. 获取值的时候,通过哈希表获取Node,再获取value 时间复杂度O(1)
                   5. 添加值的时候,通过哈希表判断是否存在,存在链表remove。如果达到容量,remove到tail节点,通过tail
                      节点中存储的key,再移除掉哈希表中的key。最终添加到head,并更新哈希表
                   6. 重置优先级,通过key找到Node,删除Node并添加到head,还需要更新map中的value(因为node时新对象)

 * @Author：zhh
 * @Date：2024/4/4 15:32
 */
class LRUCache02 {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }
    class Node<E,R> {
        E key;
        R value;
        Node<E,R> next;
        Node<E,R> prev;
        Node(Node<E,R> prev, E key,R value, Node<E,R> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private HashMap<Integer, Node> map;
    private int size;
    private int max;
    private Node<Integer,Integer> head;
    private Node<Integer,Integer> tail;
    public LRUCache02(int capacity) {
        this.map = new HashMap<>();
        this.max = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            //重新排序,此时key优先级第一
            Node node = map.get(key);
            int value = (int)node.value;
            remove(node);
            //新对象需要重新赋值
            map.put(key,addFirst(key, value));
            return value;
        }
        return -1;
    }

    /**
     * map value存Node,删除时复杂度就是O(1)
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if(map.containsKey(key)){
           //key重复了,双向链表需要将Node移出去,重新添加
            remove(map.get(key));
        }
        if(size > 0 && max == size){
            //移出去最先进去的key
            map.remove(tail.key);
            remove(tail);
        }
        //头插法添加到head
        Node node = addFirst(key,value);
        map.put(key,node);
    }

    /**
     * 双向链表 头插法
     * @param key
     * @param value
     * @return
     */
    public Node addFirst(int key, int value){
        Node<Integer,Integer> node;
        if(head == null){
            node = head = tail = new Node<>(null, key,value, null);
        }else {
            node = new Node<>(null, key,value, head);
            head.prev = node;
            head = node;
        }
        size++;
        return node;
    }

    /**
     * 双向链表 删除node原地删除即可 复杂度是O(1)
     * @param node
     * @return
     */
    public void remove(Node node){
        Node prev = node.prev;
        Node next = node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.key = null;
        node.value = null;
        size--;
    }

}
