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
    ArrayList<String> list = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        backtrack(n,0,0,new StringBuilder());
        return list;
    }

    /**
     递归过程中，当前栈快照变量过程中，当右大于左，必然不符合条件。根据这个条件可以编写出递归流程
     */
    public void backtrack(int n , int lNum, int rNum,StringBuilder stringBuilder){
        if(rNum > lNum){
            return;
        }

        if(rNum + lNum == n * 2 ){
            list.add(stringBuilder.toString());
            return;
        }

        if (lNum < n){
            stringBuilder.append("(");
            backtrack(n,lNum+1,rNum ,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        if (rNum < n){
            stringBuilder.append(")");
            backtrack(n,lNum, rNum + 1,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

    }


}
