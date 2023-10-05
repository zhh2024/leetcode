package string;

/**
 * 最长公共前缀
 * @Author：zhh
 * @Date：2023/9/20 20:41
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"ss","s","sa"};
        System.out.println(longestCommonPrefix(strs));
    }

    /**
     * 最长公共前缀
     * 思路: 矩阵二维数组思想, 字符串长度看成行, 字符串数组元素看成列
     *       1.既然是公共前缀,取第一个字符串必然符合。
     *       2.然后字符串数组n 和 第一个字符串长度m组成二维数组, 遍历m,取字符 循环与n比较,如果全部一致说明符合条件
     *         不符合,返回至这个下标的字符串,但不包含当前字符
     *
     * 需要考虑问题: 1.下标越界 ,随便取的字符串不一定是最短字符串,遍历数组n时,需要判断字符串长度-1 是否大于循环下标
     *               2. 符合条件的字符如何处理,字符串从0-遍历下标截取即可
     * @param strs
     */
    public static String longestCommonPrefix(String[] strs){
        String str = strs[0];
        int line = str.length();
        int row = strs.length;
        for (int i = 0; i < line; i++) {
            char c = str.charAt(i);
            for (int j = 1; j < row; j++) {
                if(strs[j].length()-1 < i || strs[j].charAt(i) != c){
                    //不符合,返回至这个下标的字符串,但不包含当前字符
                    return str.substring(0,i);
                }
            }
        }
        //走到最后说明全部符合,不会中途返回了
        return str;
    }
}
