package array;

import java.util.ArrayDeque;

/**
 * @Desc: 整数转罗马数字
 * 七个不同的符号代表罗马数字，其值如下：
 * 符号	值
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * 罗马数字是通过添加从最高到最低的小数位值的转换而形成的。将小数位值转换为罗马数字有以下规则：
 *
 * 如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。
 * 如果该值以 4 或 9 开头，使用 减法形式，表示从以下符号中减去一个符号，例如 4 是 5 (V) 减 1 (I): IV ，9 是 10 (X) 减 1 (I)：IX。仅使用以下减法形式：4 (IV)，9 (IX)，40 (XL)，90 (XC)，400 (CD) 和 900 (CM)。
 * 只有 10 的次方（I, X, C, M）最多可以连续附加 3 次以代表 10 的倍数。你不能多次附加 5 (V)，50 (L) 或 500 (D)。如果需要将符号附加4次，请使用 减法形式。
 * 给定一个整数，将其转换为罗马数字。
 *
 * 示例 1：
 * 输入：num = 3749
 * 输出： "MMMDCCXLIX"
 * 解释：
 * 3000 = MMM 由于 1000 (M) + 1000 (M) + 1000 (M)
 *  700 = DCC 由于 500 (D) + 100 (C) + 100 (C)
 *   40 = XL 由于 50 (L) 减 10 (X)
 *    9 = IX 由于 10 (X) 减 1 (I)
 * 注意：49 不是 50 (L) 减 1 (I) 因为转换是基于小数位
 *
 * 示例 2：
 * 输入：num = 58
 * 输出："LVIII"
 * 解释：
 * 50 = L
 *  8 = VIII
 *
 * 示例 3：
 * 输入：num = 1994
 * 输出："MCMXCIV"
 * 解释：
 * 1000 = M
 *  900 = CM
 *   90 = XC
 *    4 = IV
 * @Author：zhh
 * @Date：2025/4/23 17:32
 *
 * 考察点: 提取num的 个十百千..位数
 */
public class IntToRoman {
    public static void main(String[] args) {
        IntToRoman intToRoman = new IntToRoman();
        String s = intToRoman.intToRoman(3749);
        System.out.println(s);

    }

    /**
     * 解题思路:
     * 1. 提取num的 个十百千位数
     * 2. 然后根据1-9和不同的位数做case
     *
     * 符号	值
     * I	1
     * V	5
     * X	10
     * L	50
     * C	100
     * D	500
     * M	1000
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        ArrayDeque<String> deque = new ArrayDeque<>();
        int index = 0;
        while (num > 0){
            index++;
            int digit = num % 10;
            switch (digit){
                case 1:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("I");
                    }else if(index == 2){
                        deque.offerFirst("X");
                    }else if(index == 3){
                        deque.offerFirst("C");
                    }else if(index == 4){
                        deque.offerFirst("M");
                    }
                    break;
                case 2:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("II");
                    }else if(index == 2){
                        deque.offerFirst("XX");
                    }else if(index == 3){
                        deque.offerFirst("CC");
                    }else if(index == 4){
                        deque.offerFirst("MM");
                    }
                    break;
                case 3:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("III");
                    }else if(index == 2){
                        deque.offerFirst("XXX");
                    }else if(index == 3){
                        deque.offerFirst("CCC");
                    }else if(index == 4){
                        deque.offerFirst("MMM");
                    }
                    break;
                case 4:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("IV");
                    }else if(index == 2){
                        deque.offerFirst("XL");
                    }else if(index == 3){
                        deque.offerFirst("CD");
                    }
                    break;
                case 5:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("V");
                    }else if(index == 2){
                        deque.offerFirst("L");
                    }else if(index == 3){
                        deque.offerFirst("D");
                    }
                    break;
                case 6:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("VI");
                    }else if(index == 2){
                        deque.offerFirst("LX");
                    }else if(index == 3){
                        deque.offerFirst("DC");
                    }
                    break;
                case 7:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("VII");
                    }else if(index == 2){
                        deque.offerFirst("LXX");
                    }else if(index == 3){
                        deque.offerFirst("DCC");
                    }
                    break;
                case 8:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("VIII");
                    }else if(index == 2){
                        deque.offerFirst("LXXX");
                    }else if(index == 3){
                        deque.offerFirst("DCCC");
                    }
                    break;
                case 9:
                    if(index == 1){
                        //个位数
                        deque.offerFirst("IX");
                    }else if(index == 2){
                        deque.offerFirst("XC");
                    }else if(index == 3){
                        deque.offerFirst("CM");
                    }
                    break;
            }
            num= num /10;
        }
        return  String.join("", deque);
    }


    /**
     * 优化思路
     */
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman02(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num = num - value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }
}
