package Sorting;

/**
 * @Author：zhh
 * @Date：2023/6/9 10:26
 *
 * 快速排序: 时间复杂度O(n log n) ,空间复杂度O(log n)
 * 一 思想:
 * 假设你有一堆扑克牌（比如 [5, 3, 8, 1, 2, 7]），现在要从小到大整理。快排的核心动作是：
 * 随便选一张牌当“裁判”（比如选第一张的 3 当基准值）。
 * 把牌分成两堆：
 * 左边堆：所有比 3 小的牌（[1, 2]）
 * 右边堆：所有比 3 大或等于的牌（[5, 8, 7]）
 * 最后裁判自己放在中间（左边小，右边大）。
 * 对左边堆和右边堆重复这个操作，直到每一堆只剩一张牌。
 * 最后，所有小堆按顺序拼起来，就是排好序的牌（[1, 2, 3, 5, 7, 8]）
 *
 * 二 代码实现:
 * 1. 分区
 *  1.1 选第一个元素元素当“分界线” 为基准
 *  1.2 定义双指针,左右指针的值分别与基准比较,直至找到右指针值<基准,左指针值>=基准,然后左右指针值交换
 *  1.3 直至左右指针值相遇,此时再将右指针的值(因为右指针移动条件是右指针值<基准)与基准交换。
 *  最终效果:分开两个子数组,小于等于基准的放在左边,大于基准的在右边。
 * 2. 递归
 * 递归处理基准左右两边两个数组：对左边和右边的子数组，重复选基准和分区的操作，直到每个子数组只剩一个元素。
 *
 * 三 快排为什么快？
 * 每次遍历整个数组的情况下,冒泡和插入排序,只能排除一个元素。而快排指数次增加排除基准元素。
 * 比如第一次排除一个基准,区分出两个子数组。第二次排除两个基准,区分出四个子数组, 第三次排除4个基准,区分出8个子数组, 构成二叉树
 * ⚠️ 注意事项
 * 大部分情况下效率高：如果每次分区大致均匀，时间复杂度是 O(n log n)。
 * 最坏情况：如果每次选的基准都是最大/最小值，时间复杂度退化为 O(n²)。
 *
 * 四 快排时间复杂度？
 * 形成二叉树单层的时间是O(n),取决于层数,一般情况左右子树都有,这样构成的二叉树层数就是O(logn)
 * 总时间就是O(nlogn)
 *
 * 五 稳定性？
 * 快排不稳定,因为快排是替换完后再分,极端情况下全在左边，或者全在右边，这样节点只会有一个度,构不成完全二叉树。树的高度会发生变化。

 * 总结：快排像一场“分组淘汰赛”，不断选裁判、分组、再分组，直到所有元素站对位置。它的核心是“分治思想 + 基准值分区”。
 *
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3,4,7,2,6,1};
        int length = arr.length;
        quickSort02(arr,0,length-1);
        for (int i = 0; i < length; i++) {
            System.out.println(arr[i]);
        }
    }


    /**
     * [5,32,1,3,4,1,21,2,45,7]   5,2,1,3,4,1,21,32,45,7| 1,6,3,4,5
     *
     * 注意: rightBegin > leftBegin 不能写成 rightBegin >= leftBegin ,因为判断完毕后 rightBegin还会++,
     *      leftBegin还会-- ,就会陷入死循环,因为一直不相等
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort( int[] arr,int l,int r){
        if (l >= r){
            return;
        }
        //基准
        int reference = arr[l];
        //左哨兵
        int leftBegin = l;
        //右哨兵
        int rightBegin = r;
        while (rightBegin!=leftBegin){
            //定位右哨兵
            for (; rightBegin > leftBegin ; rightBegin--) {
                if(arr[rightBegin] < reference){
                    break;
                }
            }
            //定位左哨兵
            for (; leftBegin < rightBegin; leftBegin++) {
                if(arr[leftBegin] > reference ){
                    break;
                }
            }
            //左哨兵和右哨兵没有相遇,彼此互换
            int temp = arr[rightBegin];
            arr[rightBegin] = arr[leftBegin];
            arr[leftBegin] = temp;
        }
        //左哨兵i和右哨兵相遇了,基准交换
        int temp = arr[rightBegin];
        arr[rightBegin] = reference;
        arr[l] = temp;
        //开始递归
        quickSort(arr,l,rightBegin-1);
        quickSort(arr,rightBegin+1,r);
    }


    /**
     * quickSort代码优化
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort02(int[] arr, int left,int right){
        if(left >= right){
            return;
        }

        int baseNumber = arr[left];
        int rightIndex = right;
        int leftIndex = left;

        while (leftIndex != rightIndex){
            while (rightIndex > leftIndex && arr[rightIndex] > baseNumber){
                rightIndex--;
            }
            while (rightIndex > leftIndex && arr[leftIndex] <= baseNumber){
                leftIndex++;
            }
            int tmp = arr[rightIndex];
            arr[rightIndex] = arr[leftIndex];
            arr[leftIndex] = tmp;
        }
        arr[left] = arr[rightIndex];
        arr[rightIndex] = baseNumber;

        quickSort02(arr,left,rightIndex-1);
        quickSort02(arr,rightIndex+1,right);
    }
}
