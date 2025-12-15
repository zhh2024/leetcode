package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @Desc: O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * 示例：
 * <p>
 * 输入
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * 解释
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 *
 *
 * 思路: 主要是删除需要O(1),但是又是相同概率返回也是O(1) ,卡在这里很矛盾，用双向链表去做删除的话，没法random返回。用数组做容器，可以通过坐标相同随机返回，但是删除如何O(1)
 *       1. 确定了用数组来做容器，就需要解决删除的问题，如果知道了要删除元素的下标，然后将最后一个元素赋值给要删除元素的下标，然后再将最后一个元素空间释放，不就做到了O(1)删除了吗
 *       2. 所以需要哈希表存储值和下标的关系，这样就可以O(1)删除
 * @Author：zhh
 * @Date：2025/12/15 21:01
 */
public class RandomizedSet {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.remove(2);
        randomizedSet.insert(2);
        randomizedSet.getRandom();
        randomizedSet.remove(1);
        randomizedSet.insert(2);
        randomizedSet.getRandom();

    }

    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int size = list.size();
        Integer lastVal = list.get(size - 1);
        list.remove(size - 1);
        Integer index = map.get(val);
        if (index != size - 1) {
            list.set(index, lastVal);
            map.put(lastVal, index);
        }
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(new Random().nextInt(list.size()));
    }
}
