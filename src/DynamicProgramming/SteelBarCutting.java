package DynamicProgramming;

/**
 * @Desc: 钢筋切割问题
 * @Author：zhh
 * @Date：2024/4/25 16:45
 */
public class SteelBarCutting {

    public static void main(String[] args) {
        int[][] steelPrices = {{1,1},{2,5},{3,8},{4,9},{5,10}};
        System.out.println(steelBarCutting(steelPrices));
    }

    /**
     * 时间复杂度O(n^2),空间复杂度O(n)
     * @param steelPrices
     * @return
     */
    public static int steelBarCutting(int[][] steelPrices){
        int[] maxs = new int[steelPrices.length];
        maxs[0] = steelPrices[0][1];
        for (int i = 1; i < steelPrices.length; i++) {
            compare(steelPrices,maxs,i);
        }
        return maxs[steelPrices.length-1];
    }

    public static void compare(int[][] steelPrices,int[] maxs, int index){
        int max = steelPrices[index][1];
        int n = index / 2;
        for (int i = 0; i <= n; i++) {
            int sum = maxs[i] + maxs[(index-1) - i];
            max = max>sum?max:sum;
        }
        maxs[index] = max;
    }
}
