package binarySearch;

/**
 * @Desc: 搜索二维矩阵
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 *
 * 两种方法殊途同归，都利用了二分查找，在二维矩阵上寻找目标值。值得注意的是，
 * 若二维数组中的一维数组的元素个数不一，方法二将会失效(无法求得正确的商和余数)，而方法一(使用的每行首元素做的比较)则能正确处理。
 * @Author：zhh
 * @Date：2025/5/18 18:20
 */
public class SearchMatrix {
    public static void main(String[] args) {
        int [][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        SearchMatrix searchMatrix = new SearchMatrix();
        searchMatrix.searchMatrix(matrix,3);

    }

    /**
     * 思路：
     * 1. 二分查找,对列进行二分,找到所在的行
     * 2. 再对行进行二分查找,找到具体的值
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix[0].length;
        int col = matrix.length;
        int left = 0,right = col-1;
        while (left <= right){
            int mid = ((right - left ) >> 1) + left;
            if (matrix[mid][0] == target) {
                return true;
            }else if (matrix[mid][0] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        if(right < 0){
            return false;
        }
        int rowLeft = 0;
        int rowRight = row-1;
        while (rowLeft <= rowRight){
            int mid = ((rowRight-rowLeft) >> 1) + rowLeft;
            if (matrix[right][mid] == target) {
                return true;
            }else if (matrix[right][mid] < target){
                rowLeft = mid + 1;
            }else {
                rowRight = mid -1;
            }
        }
        return false;
    }

    /**
     * 思想: 一次二分,将二维矩阵的元素看成单行元素
     * 然后利用矩阵规则,可以通过行列,获取对应的下标
     * num / width 商就代表所在行
     * num % width 余数就代表所在列
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix02(int[][] matrix, int target) {
        int length = matrix.length;
        int width = matrix[0].length;
        int left = 0;
        int right = length * width - 1;

        while (left <= right){
            int mid = left + (right- left)/2;
            int midNum = matrix[mid / width][mid % width];
            if(midNum == target){
                return true;
            }else if(midNum < target){
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return false;
    }




}
