package linkedList.hashMap;

import java.util.HashMap;

/**
 * @Desc: 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 *       实现 LRUCache 类：
         LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
         int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
         void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
              如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，
              则应该 逐出 最久未使用的关键字。
         函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

   待优化: TODO reHead和插入的时候,都需要考虑是否是头尾节点,防止为null,代码量很多,比较复杂繁琐。
          初始化创造头尾虚节点,就不需要判断是否是头尾节点,就不会出现null了
 * @Author：zhh
 * @Date：2024/4/4 15:32
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        System.out.println(lRUCache.get(1));
        lRUCache.put(3, 3);
        System.out.println(lRUCache.get(2));
        lRUCache.put(4, 4);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
    }
    private Node head;

    private Node tail;

    private int size;

    private int capacity;

    private HashMap<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        //将该node放到头结点,(因为要移动到头结点,所以必须得用双向链表,单链表做不到O(1)移动)因为是最新触发使用的
        reHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            //将该node放到头结点,(因为要移动到头结点,所以必须得用双向链表,单链表做不到O(1)移动)因为是最新触发使用的
            reHead(node);
            return;
        }
        if(size >= capacity){
            //移除最后尾节点
            map.remove(tail.key);
            Node prev = tail.prev;
            tail.prev = null;
            if(prev != null){
                prev.next = null;
                tail = prev;
            }
            size--;
        }
        //插入: 头插法
        Node node = new Node(null, key, value, null);
        if(head == null){
            head = tail = node;
        }else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        map.put(key,node);
        size++;

    }
    public void reHead(Node node){
        if(node != head){
            Node next = node.next;
            Node prev = node.prev;
            //如果是尾节点
            if(node == tail && prev != null){
                tail = prev;
                prev.next = null;
            }else {
                prev.next = next;
                next.prev = prev;
            }
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        Node(Node prev, int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
