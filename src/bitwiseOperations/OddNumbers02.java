package bitwiseOperations;

/**
 * @Desc: 在一个数组中,有两个数只出现奇数次,其他数字都出现了偶数次,请找出其中一个数字.
 *
 *
 * @Author：zhh
 * @Date：2025/7/8 21:58
 */
public class OddNumbers02 {

    public static void main(String[] args) {
        int[] nums = {3,3,5,6,6,6,6,5,5,2,2,1,1,1};
        OddNumbers02 oddNumbers = new OddNumbers02();
        int i = oddNumbers.OddNumbers02(nums);
        System.out.println(i);
    }

    /**
     * 1. 先遍历数组全异或一次,此时结果是两个奇数的异或结果 ,以为本身与本身异或结果是0,偶数个自己就消除了。
     * 2. 两个奇数并不相同,此时的异或结果必然其中一位包含1(这俩奇数之一必然有一个是1,一个是0), 求出这一位的位置
     * 3. 再遍历数组,与这一位是1的数与其异或。这一位是0的数就被排除掉了,相当于拆分数组为两部分,两边各有一个奇数,此时最终就是a ^ b  ^ (a or b),必然能得到一个奇数
     * @param nums
     * @return
     */
    public int OddNumbers02(int[] nums){
        int odd = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            odd = odd ^ num;
        }
        //本身取反 + 1 然后 & 本身 就能得到最右边的1, 因为本身取反 & 本身 必然是0, 此时本身取反+1 就会进位到最右侧一个为1,右0变为1, 原值此为是1,取反+1后此位也是1,就得到了最右侧的1
        int odd2 = odd & (~odd + 1);

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if((num & odd2) > 0 ){
                odd = odd ^ num;
            }
        }
        return odd;
    }
}
