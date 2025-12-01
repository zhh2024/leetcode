/**
 * @Desc:
 * @Author：zhh
 * @Date：2025/12/1 20:37
 */
public class Zhh {

    public static void main(String[] args) {
        int[] matrix = {1,3};
        search(matrix,3);
    }

    public static  int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        int rightNum = nums[right];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            if(midNum == target){
                return mid;
            }
            //1. 判断mid 在哪个区间？无非就两个有序区间
            if(midNum <= rightNum){
                //2. 确定在第二个区间,就可以判断取该区间的哪一边了
                if(target < midNum || target > rightNum){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                //2. 确定在第一个区间,就可以判断取该区间的哪一边了
                if(target < midNum && target > rightNum){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }

        }
        return -1;
    }

    public static int findPeakElement02(int[] nums) {
        int left = 0;
        int right = nums.length -1;

        //让left小于right，这样就不用考虑边界问题了。
        while (left < right){
            int mid = left + (right -left)/2;
            int midNum = nums[mid];
            //mid+1 不会越界的原因是，mid计算后逼近的是left。
            if(midNum > nums[mid +1]){
                right = mid ;
            }else {
                left = mid +1;
            }
        }
        return left;
    }
}
