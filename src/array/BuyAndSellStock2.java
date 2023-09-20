package array;

/**
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * @Author：zhh
 * @Date：2023/8/14 22:21
 */
public class BuyAndSellStock2 {
    public static void main(String[] args) {

       int[] nums = {2,6,5,8,1,2,6};
    }

    /**
     *  思路: 把数组抽象看成一个X轴是时间,Y轴是当天股市价格,随着时间上升下降的曲线图.
     *  只要是上升曲线就持股,一出现下降就抛出。再买入新股,循环往复。因为当天也能抛出,这样计算总数结果就不会出现负值
     *
     *  比如 第一天买入10元,第二天股票下降5元,第三天股票15元。如果不在第一天抛出,只能挣15-10 =5元
     *  如果一下降就抛出.10-10 + 15-5 = 10元
     *
     *  进阶:贪心算法
     *
     */
    public static int buyAndSell(int[] prices){
        int min =Integer.MAX_VALUE;
        int max =Integer.MAX_VALUE;
        int differenceCount =0;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i]<max){
                //一下降就抛出上次买的并且统计总数
                differenceCount = max-min+differenceCount ;
                //买入新股
                min = prices[i];
                max = prices[i];
            }else {
                //一直直线上升,无需抛出,替换最大值即可
                max = prices[i];
                //因一直上升没机会卖,最后一天卖出去
                if(i == prices.length-1){
                    differenceCount = max-min +differenceCount;
                }
            }
        }
        return differenceCount;
    }

    public static int buyAndSell2(int[] prices){
        int count =0;
        for (int i = 0; i < prices.length-1; i++) {
            //上一天比今天如果大,则抛出,如果不大就是0当天抛出
            int max = Math.max(0, prices[i + 1]-prices[i]);
            count = count+max;
        }
        return count;
    }
}
