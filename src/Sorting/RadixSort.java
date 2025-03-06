package Sorting;


import java.util.Arrays;

/**
 * @Desc:
 *
 * 一 思想:
 * 1. 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。
 * 2. 从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 * @Author：zhh
 * @Date：2025/3/6 16:41
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("排序前: " + Arrays.toString(arr));

        radixSort(arr);

        System.out.println("排序后: " + Arrays.toString(arr));


    }

    // 基数排序主函数
    public static void radixSort(int[] arr) {
        // 获取数组中的最大值
        int max = getMax(arr);

        // 对每一位进行计数排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    // 获取数组中的最大值
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // 对数组按照指定的位进行计数排序
    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // 统计每个位上数字的出现次数
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // 计算累积次数
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 构建输出数组
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // 将输出数组复制回原数组
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }




}
