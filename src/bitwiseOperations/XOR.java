package bitwiseOperations;

import java.util.Arrays;

/**
 * @Desc: 异或
 *  1. 两个值转化成二进制, 相同的则为0,不同的则为1
 *  2. 异或满足交换律 a ^ b = b ^ a
 *  3. 异或满足结合律 a ^ (b ^ c) =  (a ^ b) ^ c
 *  4. 本身与本身异或 结果是0  a ^ a = 0
 *  5. 本身与0异或 结果是本身  a ^ 0 = a
 * @Author：zhh
 * @Date：2025/7/8 22:32
 */
public class XOR {

    public static void main(String[] args) {
        int[] nums = {1,5,8,6,7,9,2,4,5,3};
        XOR xor = new XOR();
        xor.insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 不需要一个额外空间,就能进行交换
     * 注意: i,j不能相同,不然此下标的值就会被异或成0。
     * @param nums
     * @param i
     * @param j
     */
    public void swap (int[] nums, int i , int j){
        nums[i] = nums[i] ^ nums[j];
        //此时i = i ^j   j = i ^ j & j = i
        nums[j] = nums[i] ^ nums[j];
        //此时i = i ^j ,j = i,  i = i ^ j ^ i = j
        nums[i] = nums[i] ^ nums[j];
    }

    public void bubbleSort(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length -1 - i; j++) {
                int num = nums[j];
                int num1 = nums[j + 1];
                if(num > num1){
                    swap(nums,j,j+1);
                }
            }
        }
    }

    public void selectSort(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int maxIndex = 0;
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[maxIndex] < nums[j]) {
                    maxIndex  = j;
                }
            }
            if(maxIndex != nums.length - i -1){
                swap(nums,maxIndex,nums.length - i -1);
            }
        }
    }

    public void insertSort(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            int insertNum = nums[i];
            int j = i - 1;
            for (; j >= 0 && nums[j] > insertNum; j--) {
                swap(nums,j,j + 1);
            }
            nums[j + 1] = insertNum;
        }
    }

}
