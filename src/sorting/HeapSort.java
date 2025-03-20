package sorting;


import java.util.Arrays;

/**
 * @Desc:
 * 推论一下,最后一个非叶子结点为什么是 n/2 -1 。
 * 首先由于数组紧凑所以可以构成一个完全二叉树,叶子结点在数组的最末尾。
 * 那么对于完全二叉树, 得考虑是否有单分支。 不管是否有单分支,叶子结点 = 内部结点(度为2) +1
 * 假设总结点为n,非叶子结点个数为x ,非叶子结点下标为y
 * 1. 如果没有单分支
 * 叶子结点+内部结点 = 总结点
 *  x+ 1 + x = n   y = (n-1)/2 -1   y是最后一个非叶子结点下标
 * 2. 如果有单分支
 * 叶子结点+内部结点 +单分支结点(只能是一个) = 总结点  (x-1) + x +1 =n     y= n/2 -1  y是最后一个非叶子结点下标
 *
 * 如果都是双分支情况,总结点个数必然是奇数(多一个根结点), (n-1)/2 的结果与 n/2的结果是一样的。所以最后一个非叶子结点是 n/2 -1
 * n/2 -1 就是最后一个非叶子结点
 *
 *
 * 假设非叶子结点下标为i
 * 左结点下标 l = 2 * i + 1
 * 右结点下标 r = 2 * i + 2
 * 非叶子结点范围是(0 到 n/2-1)
 *
 *
 * 一 思想:
 * 1. 利用数组紧凑所以可以构成一个完全二叉树,从最后一个非叶子结点开始,从底向上,使得每一个非叶子结点值比左结点和右结点都大
 * 2. 构成这样的结构,每个非叶子结点都比左右结点大,这样根就是最大值，这样的结构叫大根堆
 * 3. 在此结构上,将根与最后一个叶子结点交换,在从上往下调整大根堆(排除掉最后一个结点,当前最大值已选择出来了)
 *    调整过程只需要O(logn),因为其他路径都符合此前的大根堆，只有交换后的结点这条路径需要调换,路径长度就是树的高度。
 * 4. 循环调整大根堆过程,堆排序结束时，数组是升序排列 
 * 
 * 二 代码实现:
 * 1. 定位最后一个非叶子结点为 n/2 -1 , 非叶子结点的左结点下标 l = 2 * i + 1 ,右结点下标 r = 2 * i + 2
 * 2. 循环每个非叶子结点
 * 3. 非叶子结点与左右结点比较,如果左右节点比较大,就将其与根替换, 但是发生替换,就代表原左或者右子树结构发生了变化,需要递归发生变化的左或者右子树
 * 4. 上面三个步骤构成了大根堆
 * 5. 循环数组长度-1,因为大根堆根节点就是最大值,减少一次循环。
 * 6. 每次循环,将根节点与最后一个节点互换,树结构发生了变化重新递归构成大根堆,继续循环,循环长度-1,最后节点下标前移。
 * 7. 上面两个步骤为调整大根堆
 * 8. 调整结束,最后的数组就是升序数组
 *
 *
 * 三 时间复杂度:
 * 1. 构造大根堆
 * 构造大根堆,时间是不稳定的
 * 2. 调整大根堆
 * 调整过程只需要O(logn),因为其他路径都符合此前的大根堆，只有交换后的结点这条路径需要调换,路径长度就是树的高度。
 * @Author：zhh
 * @Date：2025/2/27 11:11
 */
public class HeapSort {

    // 测试代码
    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        heapSort(arr);
        System.out.println("排序结果: " + Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        if (arr == null || arr.length <= 1) return;
        //1. 创建大根堆

        //1.1 定位到最后一个非叶子结点下标
        int lastNonLeafIndex = arr.length/2 - 1;
        //1.2 遍历非叶子结点,从下往上构造大根堆
        for (; lastNonLeafIndex >= 0; lastNonLeafIndex--) {
            creat(arr,arr.length,lastNonLeafIndex);
        }

        //2. 替换根节点,调整大根堆,从最后一个下标开始
        for (int i =  arr.length-1 ; i > 0; i--) {
            // 2.1 尾结点与根结点替换
            swap(arr,0,i);
            // 2.2 根结点发生变化,调整大根堆
            creat(arr,i,0);
        }
    }

    /**
     * 创建大根堆不需要maxLength,调整大根堆需要maxLength,因为每次调整元素个数减少一个。
     * @param arr 数组
     * @param maxLength 目前可获取的最大长度
     * @param nonLeafIndex 非叶子结点下标
     */
    public static void creat(int[] arr,int maxLength, int nonLeafIndex){
        //不需要出递归,因为进入递归是if条件

        //根左右最大值下标
        int largeIndex = nonLeafIndex;
        //左结点下标
        int leftIndex = 2 * nonLeafIndex + 1;
        //右结点下标
        int rightIndex = 2 * nonLeafIndex + 2;
        //定位最大值下标
        if(leftIndex < maxLength && arr[leftIndex] > arr[largeIndex]){
            largeIndex = leftIndex;
        }
        if(rightIndex < maxLength && arr[rightIndex] > arr[largeIndex]){
            largeIndex = rightIndex;
        }
        //如果发生了变化就交换
        if(largeIndex != nonLeafIndex){
            swap(arr,largeIndex,nonLeafIndex);
            //largeIndex所在的子树结构发生了变化,子树根结点被换成了noNodeIndex的值,既然结构发生了变化,就需要递归保证结构的一致性
            creat(arr,maxLength,largeIndex);
        }
    }

    public static void swap(int[] arr,int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}
