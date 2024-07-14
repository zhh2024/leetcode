package stack;

import java.util.LinkedList;

/**
 * @Desc: 逆波兰表达式求值
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * <p>
 * 注意：
 * 有效的算符为 '+'、'-'、'*' 和 '/' 。
 * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 * 两个整数之间的除法总是 向零截断 。
 * 表达式中不含除零运算。
 * 输入是一个根据逆波兰表示法表示的算术表达式。
 * 答案及所有中间计算结果可以用 32 位 整数表示。
 * <p>
 * 示例 1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * <p>
 * 示例 2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * <p>
 * 示例 3：
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * @Author：zhh
 * @Date：2024/7/11 17:22
 */
public class EvalRPN {

    /**
     * 有效的算符为 '+'、'-'、'*' 和 '/' 。
     * 思路: 遇到运算符就将栈顶前两个元素出栈,进行运算，再将运算结果入栈
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            Integer p1;
            Integer p2;
            switch (token) {
                case "+":
                    p1 = stack.pop();
                    p2 = stack.pop();
                    stack.push(p2 + p1);
                    break;
                case "-":
                    p1 = stack.pop();
                    p2 = stack.pop();
                    stack.push(p2 - p1);
                    break;
                case "*":
                    p1 = stack.pop();
                    p2 = stack.pop();
                    stack.push(p2 * p1);
                    break;
                case "/":
                    p1 = stack.pop();
                    p2 = stack.pop();
                    stack.push(p2 / p1);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }
}
