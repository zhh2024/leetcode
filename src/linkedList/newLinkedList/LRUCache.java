package linkedList.newLinkedList;

import java.util.HashMap;

/**
 * @Desc: 思维逻辑不难, 就是双向链表 + 哈希。 这样移除和获取都是O(1)
 * 难点是双向链表下 不加头和尾哨兵节点,要考虑各种场景，是否为头，是否为尾，还要考虑空指针，代码逻辑十分复杂。
 * @Author：zhh
 * @Date：2025/12/7 23:04
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);
        lruCache.put(2,2);

        lruCache.put(3,3);
        lruCache.put(4,4);

        lruCache.get(4);

        lruCache.get(3);
        lruCache.get(2);

        lruCache.get(1);
        lruCache.put(5,5);
        lruCache.get(1);
        lruCache.get(2);
        lruCache.get(3);
        lruCache.get(4);
        lruCache.get(5);
    }

    HashMap<Integer, Node> map = new HashMap<>();

    Node head;

    Node tail;

    int size;

    int len;


    public LRUCache(int capacity) {
        len = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            return move(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            move(key);
            return;
        }
        if (size == len) {
            //移除
            map.remove(tail.pre.key);
            Node pre = tail.pre;
            removeNode(pre);
            size--;
        }
        //头插法
        Node newNode = new Node(key, value, null, null);
        addToHead(newNode);
        map.put(key, newNode);
        size++;
    }

    private int move(int key) {
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    //头插法
    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }


    class Node {
        int key;
        int val;
        Node next;
        Node pre;

        public Node() {
        }

        public Node(int key, int val, Node next, Node pre) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.pre = pre;
        }
    }
}
