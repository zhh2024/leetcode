package array;

/**
 * @Desc: 除自身以外数组的乘积
 *
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 * 思路:
 * 1. 现将后缀乘积放在数组中，进行初始化。
 * 2. 然后遍历原数组，累积前面的乘积 * 后缀乘积数组中的值就是最终结果。
 * 3. 复用后缀乘积数组，计算结果进行覆盖即可。
 * @Author：zhh
 * @Date：2025/12/15 21:54
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        ProductExceptSelf productExceptSelf = new ProductExceptSelf();
        int[] s = {1,2,3,4};
        productExceptSelf.productExceptSelf(s);
    }

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] ints = new int[length];
        //1. 初始化后缀乘积
        ints[length-1] = nums[length-1];
        for (int i = nums.length -2 ; i >= 0; i--) {
            ints[i] = nums[i] * ints[i+1];
        }
        //2. 遍历过程中，计算乘积，再加上后面的乘积就是最终结果了。
        int currentSum = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            ints[i] = currentSum * ints[i+1];
            currentSum = currentSum * nums[i];
        }
        ints[nums.length-1] = currentSum;
        return ints;
    }
}
