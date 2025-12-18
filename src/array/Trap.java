package array;

/**
 * @Desc: 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * 解题思路:
 * 本质就是当前下标的储水量取决于左右两侧边界高度的最大值，然后取最小边界的高度-当前下标的高度就是该下标的储水量
 * 最终问题就围绕如何获取当前下标的左右两侧边界高度的最大值,围绕这一点进行优化。
 * @Author：zhh
 * @Date：2025/12/18 16:47
 */
public class Trap {

    public static void main(String[] args) {

    }

    /**
     * 暴力算法,水平方向,计算每一层的储水量，时间复杂度O(n^2)
     */
    public int trap(int[] height) {
        int maxHeight =Integer.MIN_VALUE;
        for (int k : height) {
            maxHeight = Math.max(maxHeight, k);
        }
        int sumTrap = 0;
        for (int i = 1; i <= maxHeight; i++) {
            int left = -1;
            for (int j = 0; j < height.length; j++) {
                if (height[j] >= i ) {
                    if(left != -1 && j - left > 1){
                        sumTrap = sumTrap + (j - left - 1);
                    }
                    left = j;
                }
            }
        }
        return sumTrap;
    }

    /**
     * 为什么不换一种方向呢？垂直方向记录每一个下标的储水量，每一个下标的储水量取决于，左右两侧的最大高度的最小值，然后该最小值减去当前下标的高度，就是该下标的储水量。
     * 1. 在O(n)时间初始化每个下标的左右两侧最大高度
     * 2. 遍历原数组，进行每个下标储水量计算
     * 3. 总时间就是O(n)
     * @param height
     * @return
     */
    public int trap02(int[] height) {
        int length = height.length;
        int[] left = new int[length];
        left[0] = height[0];
        for (int i = 1; i < length; i++) {
            left[i] = Math.max(height[i],left[i-1]);
        }
        int[] right = new int[length];
        right[length-1] = height[length-1];
        for (int i = length-2; i >=0 ; i--) {
            right[i] = Math.max(height[i],right[i+1]);
        }
        int sumTrap = 0;
        //当左右两测最大高度等于本身的时候，自然没法蓄水，不可能为负数，只有可能是0，因为两个数组存的是该下标左右两侧的最大高度，如果不存在不就是自己吗。
        for (int i = 0; i < length; i++) {
            int trap = Math.min(left[i], right[i]) - height[i];
            sumTrap += trap;
        }
        return sumTrap;
    }

    /**
     * 动态规划需要两个数组记录当前下标左右侧的最大高度,需要O(n)的空间复杂度,如何减少空间复杂度呢?
     * 1. 先找到最高点的下标
     * 2. 然后定义两个左右指针，左右指针同时走，直至达到最高点
     * 3. 左指针开始遍历，左指针在最高点的左侧，代表最高点是右边界，动态更新左边界的最大值
     * 4. 右边指针开始遍历，右指针在最高点的右侧，代表最高点是左边界，动态更新右边界的最大值
     * 5. 因为要动态更新左右边界的最大值，所以需要两个指针从头开始走，才可以进行实时更新。
     * @param height
     * @return
     */
    public int trap03(int[] height) {
        int length = height.length;
        int maxLength = 0;
        int maxIndex = 0;
        for (int i = 0; i < length; i++) {
            if(height[i] > maxLength){
                maxLength = height[i];
                maxIndex = i;
            }
        }
        int sumTrap = 0 ,maxLeft = 0,maxRight = 0;
        int left=0, right = length -1;
        while (left <= maxIndex || right >= maxIndex){
            if(left <= maxIndex){
                maxLeft = Math.max(maxLeft,height[left]);
                sumTrap += Math.min(maxLength,maxLeft) - height[left];
                left++;
            }
            if(right >= maxIndex){
                maxRight = Math.max(maxRight,height[right]);
                sumTrap += Math.min(maxLength,maxRight) - height[right];
                right--;
            }
        }
        return sumTrap;
    }

    /**
     * 继续优化双指针,不需要先遍历一次找到最高点。
     * 1. 本质是，当前下标的蓄水量，取决于左右两侧的最高点的最小值
     * 2. 左右两侧最高点需要左右指针去遍历动态更新
     * 3. 不需要找到最高点是因为，先走左指针，左指针的左边界的最大值是可以获取到的，如果该左边界小于右指针的右边界，就可以计算储水量了，因为左边界最大值当前下标已经定型了。
     * 4. 同理，当左边界大于右指针的右边界，右边界还没进行更新，该下标还待定，但是此时可以计算右指针的储水量了，因为当前右指针的右边界最优值已经确定了。
     * @param height
     * @return
     */
    public int trap04(int[] height) {
        int length = height.length;
        int sumTrap = 0 ,maxLeft = 0,maxRight = 0;
        int left=0, right = length -1;
        while (left < right){
            maxLeft = Math.max(maxLeft,height[left]);
            maxRight = Math.max(maxRight,height[right]);
            if(maxLeft <= maxRight){
                sumTrap += maxLeft - height[left];
                left++;
            }else {
                sumTrap += maxRight - height[right];
                right--;
            }
        }
        return sumTrap;
    }



}
