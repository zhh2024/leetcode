package sorting;


/**
 * @Desc: 归并排序
 * 一 思想: 拆分与重组
 *  想象你有一副乱序的扑克牌，想要按数字从小到大排列。归并排序的做法是：
 *  拆分（Divide）
 *  把牌分成两叠，直到每叠只剩1张牌（单张牌天然有序）。重复这个过程，将大问题拆解为最小单元。
 *  重组（Conquer）
 *  将两叠已经有序的牌合并成一叠更大的有序牌。每次合并时，比较两叠牌顶部的牌，取较小的放入新叠，直到所有牌合并完成。
 *
 * 二 代码实现:
 * 1. 分治可以看成递归形成二叉树,合并就是回溯过程中将左右子树合并返回的过程，最终返回一个根节点,就是排序后的结果。
 * 2. 二分法找到根,递归左右子树(左右数组),直至递归到叶子节点返回。
 * 3. 将返回的左右子树合并成新的节点,返回。
 *
 * 三 归并为什么快？
 * 归并排序将数组递归拆分为近似平衡的二叉树（非严格完全二叉树），但关键点在于每层递归的 总数据量始终为 O(n)。
 * 例如，一个长度为8的数组分治后：
 * 第0层：8个元素（原始数组）
 * 第1层：拆分后处理两个4元素的子数组 → 总数据量8
 * 第2层：处理四个2元素的子数组 → 总数据量8
 * 第3层：处理八个1元素的子数组 → 总数据量8
 * 总层数：拆分次数为 log₂n 层（以2为底的对数）。
 *
 * 四 归并时间复杂度？
 * 1.分治的时间复杂度
 * 拆分次数为 log₂n 层
 * 2.合并的时间复杂度
 * 每层合并所有子数组的总操作次数为 O(n)（如比较和复制）。
 * 总时间 = 层数 × 每层时间 = O(log n) × O(n) = O(n log n)。
 *
 * 五 稳定性？
 * 归并排序稳定,因为二叉树节点生成从数组中间隔开,所以每个内节点都会有两个度，是完全二叉树,层数不会浮动变化。
 * 快排不稳定,因为快排是替换完后再分,极端情况下全在左边，或者全在右边，这样节点只会有一个度,构不成完全二叉树。层数会浮动变化。
 *
 * @Author：zhh
 * @Date：2025/2/26 15:12
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {5,3,6,9,2,1};
        int[] ins = mergeSort(arr, 0, arr.length - 1);
        for (int in : ins) {
            System.out.println(in);
        }
    }

    public static int[] mergeSort(int[] arr,int left,int right) {
        //代表着没有左右子树了,返回根节点
        if(left == right){
           return new int[]{arr[left]};
        }
        //相当于二叉树根节点,递归左右子树
        int mid = left + (right - left) / 2;
        int[] leftNode = mergeSort(arr, left, mid);
        int[] rightNode = mergeSort(arr, mid + 1, right);
        //合并左右子树为一个根节点并返回
        return merge(leftNode,rightNode);
    }

    public static int[] merge(int[] leftNode,int[] rightNode){
        //双指针法,将两个数组合并到一个新数组且有序
        int[] ans = new int[leftNode.length+rightNode.length];
        int ansIndex=0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < leftNode.length  && rightIndex < rightNode.length ){
            int leftNum = leftNode[leftIndex];
            int rightNum = rightNode[rightIndex];
            if(leftNum <= rightNum){
                ans[ansIndex] = leftNum;
                leftIndex++;
            }else {
                ans[ansIndex] = rightNum;
                rightIndex++;
            }
            ansIndex++;
        }
        while (leftIndex < leftNode.length){
            ans[ansIndex] = leftNode[leftIndex];
            leftIndex++;
            ansIndex++;
        }
        while (rightIndex < rightNode.length){
            ans[ansIndex] = rightNode[rightIndex];
            rightIndex++;
            ansIndex++;
        }
        return ans;
    }


}








