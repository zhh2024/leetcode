package hashMap;

/**
 * @Desc: 基础 1.记录字符串中每个字符出现的字符个数
 * 这种单字符场景,字符有26个,从小到大, a- 'a' 就是字符a在cns的起始下标,后面26个字母就是累加
 * 这种场景下比hashMap计算hash值更快 因为字符串只存在a-z,因为每个操作相比数组都有哈希运算,而a-z本身就具有唯一性,所以得用数组
 *
 * @Author：zhh
 * @Date：2025/5/6 15:46
 */
public class Base {

    public static void main(String[] args) {
        String s = "sdfsdgedgggdds";
        int[] cns = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ++cns[s.charAt(i) - 'a'];
        }
    }

}
