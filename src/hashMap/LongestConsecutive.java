package hashMap;

import java.util.HashSet;

/**
 * @Desc: 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [100,3,200,1,4,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * @Author：zhh
 * @Date：2024/7/15 10:58
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        int[] ints = {0,0,-1};
        longestConsecutive.longestConsecutive02(ints);
    }

    /**
     * 思路: 利用哈希表去查找 +1的值
     * 1. 先遍历一次,存入哈希表,可以做到去重和查询为O(1)复杂度
     * 2. 遍历集合元素,当前元素在哈希表中循环寻找+1的值,直至找不到,循环的次数就是该元素的序列值。
     * 3. 判断序列值是否超过了最大序列值,超过就替换,返回最终最大序列值
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(!set.contains(num -1)){
                int number = 1;
                while (set.contains(num+1)){
                    number ++;
                    num = num + 1;
                }
                max = Math.max(number,max);
            }
        }
        return max;
    }

    /**
     * 优化思路:
     * 1. 不变
     * 2. 遍历set集合,set集合是去重过的,会节省时间复杂度
     * 3. 寻找+1的值,会造成重复寻找,导致最终复杂度是O(n^2),比如 100,3,2,25,5,4,1 , 3->4—>5, 2->3->4->5, 4->5,1->2->3->4->5 有很多冗余
     * 4. 遍历set集合，当前元素如果不存在比它本身小1的数,就代表当前元素是序列中的最小值,去哈希表循环找序列才有意义。
     *    如果存在了比它本身小1的数,当前元素就没必要去哈希表循环找序列,因为比它小1的数还会回来找它,就重复了没有意义了。
     * 5. 最终复杂度就是序列最小的数去循环找,时间复杂度O(n)
     * @param nums
     * @return
     */
    public int longestConsecutive02(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int max = 0;
        for (Integer num: set){
            if(!set.contains(num-1)){
                int number = 1;
                while (set.contains(num+1)){
                    number ++;
                    num = num + 1;
                }
                max = Math.max(number,max);
            }
        }
        return max;
    }
}
