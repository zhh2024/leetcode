import java.util.HashMap;

/**
 * @Author：zhh
 * @Date：2023/9/9 21:11
 */
public class ReviewTest {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};

        //merge(nums1,3,nums2,3);
        System.out.println("04");

        System.out.println("01");

        int[] nums = {1,2,3,4,2,2};
        System.out.println(removeElement(nums,2));
    }

    /**
     *  合并两个有序数组 m,n   默认从小到大
     *  思路1: 后双指针
     *  (1)初始化指针位置为最后一个,两个数组进行比较， 谁大谁挪动位置,指针移动
     * （2）继续比较，谁大谁再挪动位置,直到其中一个走到头了,剩下的就原封不动的挪位置就可以
     * （3）挪的新地方为原数组(m+n)以此从后往前添加
     *  时间复杂度O(n+m),空间复杂度O(1)
     *
     *  思路2: 前双指针
     *  (1)初始化指针位置为第一个,两个数组进行比较， 谁大谁挪动位置,指针移动
     *  （2）继续比较，谁大谁再挪动位置,直到其中一个走到头了,剩下的就原封不动的挪位置就可以
     *  （3）需要建立一个新数组,放到新数组里面,从前往后添加
     *  注意:前双指针不能在原数组上，是因为会覆盖原数组内容 比如 m={1,3, } n={2} 2比1大 ,2挪位置，放哪呢？后面不能放，放3位置就会覆盖
     *  时间复杂度O(m+n),空间复杂度O(m+n)
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length-1;
        int mStart = m - 1,nStart = n-1;
        while (mStart>=0 || nStart>=0){
            //先判断是否还能走,再比较 ,而不是先比较
            if(mStart < 0){
                //m走到头了,n还在
                nums1[index] = nums2[nStart];
                nStart--;
            }else if(nStart < 0){
                //n走到头了,m还在
                nums1[index] = nums1[mStart];
                mStart--;
            }else if (nums1[mStart]>nums2[nStart]) {
                nums1[index] = nums1[mStart];
                mStart--;
            }else {
                nums1[index] = nums2[nStart];
                nStart--;
            }
            index--;
        }
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 思路(1): 前后指针法
     * (1) 初始化后指针,后指针值先判断是否=val,不等于不动,等于往前移动,直到遇到不等于val值的停下来
     * (2) 后指针停住后,前指针移动,找到等于val值的,进行前后指针互换。
     * (3) 以此往复,知道前后指针相遇
     *  后指针先走,需要后指针判断
     *  时间复杂度O(n) ,空间复杂度O(1)
     *
     *  思路(2): 快慢指针法
     *  (1) 快指针从左往右走,遇到！= val的值 就将该值放入慢指针下标
     *  (2) 满指针下标被放值了,就移动一位,满指针本质存的就是过滤后的数组
     *  时间复杂度O(n)，空间复杂度O(1)
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int hou = nums.length-1;
        int qian =0;
        while (hou >= qian){
            if (nums[hou] != val) {
                if(nums[qian] == val ){
                    //互换
                    int num = nums[hou];
                    nums[hou] = nums[qian];
                    nums[qian] = num;
                }
                qian++;
            }else {
                hou--;
            }
        }
        //因为hou是下标 所以需要+1,返回的是长度
        return hou +1;
    }

    /**
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，
     * 返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数
     *
     * 思路: 快慢指针法
     * (1)有序的重复项必然是连在一起的,所以前后比较就可以区分。
     *（2）快指针从前往后遍历,前后比较，遇到不同的就放入慢指针,相同的不管 。
     * (3)因为前后比较,第一个值必然没问题,所以快慢指针初始化下标为1
     *
     *  注意思考？无序的情况，这种思路 前后比较显然是不可以的！！！ 比如 1，2，1，2 这样按照上面算法依然还是1，2，1，2
     * 时间复杂度O(n)，空间复杂度O(1)
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int low = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast]!=nums[fast-1]) {
                nums[low] = nums[fast];
                low++;
            }
        }
        return low;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     *
     * 思路:
     * (1) 重复数组允许出现2次,也就是n次,在removeDuplicates中分析知道,重复数据必然是连在一起的
     * (2) 假设允许出现n次,本质就是慢指针可以容纳最多n个相同的
     * (3) 快指针始遍历比较数组, 快指针的值与(慢指针的长度-n)下标的值比较
     * (4) 如果不同就可以添加到慢指针下,如果相同说明容纳不了了,已经是n了
     *（5）快慢指针初始化下标为n。因为n是慢指针可容纳的最多n个相同的。
     * 本质思想：慢指针可容纳n个相同的,快指针的值应该与慢指针的长度-n)下标的值比较.看是否可容纳
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public static int removeDuplicatesMore(int[] nums) {
        //dup的值可以随意更改,想容纳几个重复的都可以,1就是removeDuplicates
        int dup = 2,low = dup;
        for (int fast = 2; fast < nums.length ; fast++) {
            if (nums[fast] != nums[low- dup]) {
                nums[low] = nums[fast];
                low++;
            }
        }
        return low;
    }

    /**
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 思路1：因为多数元素>n/2 ,那么排序后取中间值就是多数元素。
     * 快排的时间复杂度O(nlogn)
     *
     * 思路2：将数组循环放入map中，利用map的key唯一性,value记录重复次数，最后遍历map，找出最大的值。
     * 时间复杂度O(2n),空间复杂度O(n)
     * 注意:这种思路没有利用>n/2的条件,说明这种思路显然不是最优的
     *
     * 思路3：摩尔投票法
     * 因为>n/2 ,比一半多,假设一群人去抢占山头,最后抢占山头的必然是多数元素，因为总是多一个人,两两抵消剩余就是胜者。
     * 按照这个思路出发代码分析如下:
     * (1) 从前往后循环遍历数组,一个一个去抢山头,记录山头人数
     * (2) 如果遇到不一样的，山头人数-1 直到0说明山头没人了，下一个来的就重立山头
     * (3) 遍历完绝对至少山头有一个人，人数为1，这个人就是多数元素
     * (4) 第二个开始抢,默认第一个占有，初始化循环下标为1
     *
     * 时间复杂度为O(n),空间复杂度为O(1)
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int count =1 ,num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(count==0){
                //重立山头
                num = nums[i];
                count++;
                continue;
            }
            if(nums[i]!=num){
                count --;
            }else {
                count++;
            }
        }
        return num;
    }


}
