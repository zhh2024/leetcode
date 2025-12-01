package bitwiseOperations;

/**
 * @Desc: 在一个数组中,有一个数只出现奇数次,其他数字都出现了偶数次,请找出这个数字.
 * @Author：zhh
 * @Date：2025/7/8 21:49
 */
public class OddNumbers {
    public static void main(String[] args) {
        int[] nums = {3,3,5,6,6,6,5};
        OddNumbers oddNumbers = new OddNumbers();
        int i = oddNumbers.oddNumbers(nums);
        System.out.println(i);
    }

    public int oddNumbers(int[] nums){
        int odd = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            odd = odd ^ num;
        }
        return odd;
    }

}
