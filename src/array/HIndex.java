package array;

import java.util.Arrays;

/**
 * @Desc: H 指数
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h 有多种可能的值，h 指数 是其中最大的那个。
 *
 * 白话: 假设指数为K,至少数组中有k个的值>=k。选择最大的k,就是h指数
 *
 *
 * 解题思路: 遍历，记录当前的最佳h指数，下标右移，代表多发布了一遍论文，此论文如果大于目前最佳h指数，就+1，因为要求至少 有 h 篇论文被引用次数大于等于 h
 * @Author：zhh
 * @Date：2025/4/23 15:05
 */
public class HIndex {

    public static void main(String[] args) {
        int[] citations = {0,0,2};
        HIndex hIndex = new HIndex();
        int i = hIndex.hIndex02(citations);
        System.out.println(i);
    }

    /**
     * 直接排序，遍历求h指数就可以
     */
    public int hIndex(int[] citations) {

        Arrays.sort(citations);
        int h = 0;
        for (int i = citations.length-1; i >=0 ; i++) {
            if (citations[i] > h) {
                h++;
            }
        }
        return h;
    }

    /**
     * 计数排序法
     * 1. 能够对引用次数进行分组求和放入数组中，这是因为次数是有最高上限的，所以数组是有界的。因为该论文的引用次数就算大于了论文个数，也不可能取该引用次数，最大的引用次数必然小于等于论文个数
     *    所以可以将超过论文个数的引用次数看错论文个数。数组有界了，就可以通用引用次数进行分组求和，然后遍历取得最终h指数了。
     */
    public int hIndex02(int[] citations) {
        //1. 按照引用次数进行分组求和，放入数组中
        int[] ints = new int[citations.length + 1];
        for (int i = 0; i < citations.length; i++) {
            //h肯定低于论文个数，所以引用次数大于论文个数的，可以看成论文最大个数
            if (citations[i] > citations.length) {
                ints[citations.length]++;
            }else {
                ints[citations[i]]++;
            }
        }
        //2. 从大到小遍历该数组，引用次数>=论文个数，就是该最大h
        int number =0;
        for (int i = citations.length; i >= 0 ; i--) {
            if (i <= ints[i] + number) {
                return i;
            }
            number = ints[i] + number;
        }
        return 0;

    }
}
