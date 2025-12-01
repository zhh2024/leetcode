package backTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * @Author：zhh
 * @Date：2025/5/29 9:44
 */
public class GenerateParenthesis {


    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> strings = generateParenthesis.generateParenthesis(4);
        System.out.println(strings.toString());
    }

    public List<String> generateParenthesis(int n) {
        backtrack(new StringBuilder(),0,0,n);
        return ans;
    }


    ArrayList<String> ans = new ArrayList<>();


    /**
     * 从最大左括号,开始逐渐减少,获取全部组合
     * 如果左括号数量不大于 n，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
     * @param cur
     * @param open
     * @param close
     * @param max
     */
    public void backtrack(StringBuilder cur, int open, int close, int max) {
        //不可用open == close作为退出条件。 比如出现()就不继续了
        if(cur.length() == max * 2){
            ans.add(cur.toString());
            return;
        }
        if(open < max){
            cur.append("(");
            backtrack(cur,open+1,close,max);
            cur.deleteCharAt(cur.length()-1);
        }
        if(close < open){
            cur.append(")");
            backtrack(cur,open,close+1,max);
            cur.deleteCharAt(cur.length()-1);
        }
    }



}
