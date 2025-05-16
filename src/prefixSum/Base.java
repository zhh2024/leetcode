package prefixSum;

/**
 * @Desc:
 * 首先，我得确定前缀和的定义。
 * 1. 前缀和数组prefix中的每个元素prefix[i]表示原数组nums从第0个元素到第i个元素的和。也就是说，prefix[i] = nums[0] + nums[1] + ... + nums[i]。
 * 2. 这样，当我们需要计算区间[i, j]的和时，只需要计算prefix[j] - prefix[i-1]就可以了，前提是i>0。如果i是0的话，直接就是prefix[j]。
 * 3. 这样时间复杂度就从原来的O(n)降到了O(1)，预处理的时间是O(n)，所以对于多次查询的情况非常高效。
 * @Author：zhh
 * @Date：2025/5/7 22:41
 */
public class Base {
}
