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

         哈希表存数据, linekedList用来更改优先级,每get一次 删除掉并放到head,达到容量移除掉尾节点
         缺点: 使用linkedList,remove时间复杂度是O(n)
 * @Author：zhh
 * @Date：2024/4/4 15:32
 */
public class LRUCache {
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
    private HashMap<Integer, Integer> map;
    private LinkedList<Integer> linkedList;
    private int size;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        linkedList = new LinkedList<>();
        size = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            //重新排序,此时key优先级第一
            linkedList.remove((Object)key);
            linkedList.addFirst(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            linkedList.remove((Object)key);
        }
        if(linkedList.size() > 0 && linkedList.size() == size){
            //移出去最先进去的key
            Integer removeKey = linkedList.removeLast();
            map.remove(removeKey);
        }
        linkedList.addFirst(key);
        map.put(key,value);
    }
}
