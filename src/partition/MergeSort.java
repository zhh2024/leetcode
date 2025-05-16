package partition;

/**
 * @Desc: 归并排序
 * @Author：zhh
 * @Date：2025/5/16 13:48
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
        int[] arr1 = mergeSort(arr,0,arr.length-1);
        for (int i : arr1) {
            System.out.println(i);
        }
    }

    public static int[] mergeSort(int[] arr, int left ,int right ){
        //通用分治模板
        if(left >= right){
            return new int[]{arr[left]};
        }
        int mid = left + (right - left)/2;
        //mid+1 保证了右子数组的起始位置正确，避免重复包含左子数组的末尾元素。
        //mid-1 会导致子数组范围重叠或越界，破坏递归的收敛性。
        int[] leftArr = mergeSort(arr, left, mid);
        int[] rightArr = mergeSort(arr, mid+1, right);
        //两个有序数组合并模板
        return mergeArr(leftArr,rightArr);
    }

    public static int[] mergeArr(int[] leftArr,int[] rightArr){
        int[] newArr = new int[leftArr.length+ rightArr.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int newIndex = 0;
        while (leftIndex < leftArr.length && rightIndex < rightArr.length){
            if(leftArr[leftIndex] <= rightArr[rightIndex]){
                newArr[newIndex++] = leftArr[leftIndex++];
            }else {
                newArr[newIndex++] = rightArr[rightIndex++];
            }
        }
        while (leftIndex < leftArr.length){
            newArr[newIndex++] = leftArr[leftIndex++];
        }
        while (rightIndex < rightArr.length){
            newArr[newIndex++] = rightArr[rightIndex++];
        }
        return newArr;
    }
}
