package hashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Desc: 存在重复元素 II
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。abs()表示绝对值
 * 如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 * @Author：zhh
 * @Date：2024/10/24 15:00
 */
public class ContainsNearbyDuplicate {
    public static void main(String[] args) {
        ContainsNearbyDuplicate containsNearbyDuplicate = new ContainsNearbyDuplicate();
        int[] ints = {1,2,3,1,2,3};
        containsNearbyDuplicate.containsNearbyDuplicate02(ints,2);
    }

    /**
     * 思路: 哈希表
     * key存储值,value存储最新的下标 ,因为下标随着遍历是逐渐增大的,且差值 <=k,显然求小值
     * 当前下标肯定不需要减去之前的下标,减去最新的下标就是当前最优解，符合条件即可退出,不用求全局最优解。
     *
     * 问题就是
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num) &&  i - map.get(num) <= k) {
                return true;
            }
            map.put(num,i);
        }
        return false;
    }

    /**
     * 思路: 滑动窗口,窗口大小最大是k+1,因为右边界-左边界<=k 才有意义,如果此时左边界值没有找到符合条件的,就可以pass掉了,因为再滑右边界，也不会符合<=k。
     * 问题就是在窗口内部,需要额外判断是否存在重复值,可以利用set ,如果存在重复值就可以退出,符合<=k,无需继续滑动了
     * 注意:左边界滑动的时候,需要移除set中左边界下标的值,再移动,始终保持set中存储当前窗口内的值。
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate02(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int left = 0;
        int right = 0;
        while (right < nums.length ){
            if(right - left > k){
                set.remove(nums[left]);
                left ++;
            }
            int num = nums[right];
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
            right ++;
        }
        return false;
    }
}
