package stack;

import java.util.LinkedList;

/**
 * @Desc: 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 *
 * ({[)
 *
 * {[]}()" true ({)} false
 * @Author：zhh
 * @Date：2024/7/10 17:06
 */
public class IsValid {

    /**
     * 当前元素是 ),},] ,出栈顶元素,判断能否与其匹配,不能就不符合规范.匹配了这一对就消除了(因为 ),},] 不进栈,匹配到的(,{,[ 会出栈。消除后不影响后续判断)
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '}'){
                if(stack.isEmpty() ||  stack.pop() != '{'){
                     return false;
                }
            }else if(c == ')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
            }else if(c == ']'){
                if(stack.isEmpty() ||  stack.pop() != '['){
                    return false;
                }
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
