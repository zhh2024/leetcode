package partition;


/**
 * @Desc: 分治算法标准模板
 * @Author：zhh
 * @Date：2025/5/19 11:28
 */
public class Base {

    public static int[] mergeSort(int[] arr, int left ,int right ){
        //1. 递归中止条件
        if(left >= right){
            return new int[]{arr[left]};
        }
        //2. 找中间
        int mid = left + (right - left)/2;
        //3. 递归拆分,回溯带回来待比较值
        int[] leftArr = mergeSort(arr, left, mid);
        int[] rightArr = mergeSort(arr, mid+1, right);
        //4. 合并
        return mergeArr(leftArr,rightArr);
    }
    public static int[] mergeArr(int[] leftArr,int[] rightArr){
        return new int[leftArr.length+rightArr.length];
    }
}
