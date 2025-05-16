package slideWindow;

/**
 * @Desc: 滑动窗口标准模版
 * @Author：zhh
 * @Date：2025/5/6 16:46
 */
public class Base {

    /**
     * 滑动窗口标准模版
     * @param args
     */
    public static void main(String[] args) {
        String s = "sdsadasfssa";
        int l =0;
        int r = 0;
        //外层循环扩展右边界，内层循环扩展左边界
        for (; r < s.length() ; r++) {
            //当前考虑的元素 与 区间[left,right]不符合题意
            while (l <= r && check()) {
                //扩展左边界
            }
            //区间[left,right]符合题意，统计相关信息
        }

    }
    public static boolean check(){
        return false;
    }



}
