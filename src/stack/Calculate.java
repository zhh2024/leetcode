package stack;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Desc: 基本计算器
 * 双栈解决通用「表达式计算」问题
 * @Author：zhh
 * @Date：2024/7/11 17:57
 */
public class Calculate {
    public static void main(String[] args) {
        Calculate cal = new Calculate();
        System.out.println(cal.calculate("1+2*3/2*(-9)"));
    }

    /**
     * 思路: 双栈,操作数栈和运算符栈,运算符用map记录优先级,当遍历字符是运算符的时候触发计算。
     * 踩坑点:
     *        1. 需要提前字符串去空
     *        2. - 有可能是运算符也有可能是负数符号,需要判断,如果是负数符号补零，将零压入操作数栈方便计算。
     *        3. 12345 是1万两千三百四十五 而不是五个操作符,需要在遍历过程中,遇到操作数,继续往后,计算数值，直至遇到运算符。
     *        4. 当遇到运算符时触发计算,而不是操作数(因为要判断优先级是否前面的运算符允许进行计算),这样的话,当前运算符无法计算，遍历结束还需要再计算一次。
     *        5. (  )括号,遇到)括号,循环计算完毕，直至,栈顶为(为止。
     * @param s
     * @return
     */
    public  int calculate(String s) {
        //处理字符串
        s = s.replaceAll(" ","");
        //使用map记录运算符的优先级
        HashMap<Character, Integer> operatorMap = new HashMap<>();
        operatorMap.put('+',1);
        operatorMap.put('-',1);
        operatorMap.put('*',2);
        operatorMap.put('/',2);
        operatorMap.put('%',2);
        operatorMap.put('^',3);
        //运算符栈 + - * /
        LinkedList<Character> operatorStack = new LinkedList<>();
        //操作数栈
        LinkedList<Integer> operandStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if('(' == c){
                operatorStack.push(c);
            }else if(')' == c){
                while (!operatorStack.isEmpty()){
                    if ('(' == operatorStack.peek()) {
                        operatorStack.pop();
                        break;
                    }else {
                        cal(operatorStack,operandStack);
                    }
                }
            }else if(operatorMap.containsKey(c)){
                if('-' == c){
                   //需要判断 ‘-’ 是运算符还是符号,如果是操作数符号,压入0方便计算
                   if(i == 0 || '(' == s.charAt(i-1) ){
                       operandStack.push(0);
                   }
                }
                //判断优先级,是否进行前置计算
                while (!operatorStack.isEmpty() && '(' != operatorStack.peek()){
                    if (operatorMap.get(c) <= operatorMap.get(operatorStack.peek())) {
                        cal(operatorStack,operandStack);
                    }else {
                        //此时优先级大,操作数不能进行运算
                        break;
                    }
                }
                operatorStack.push(c);
            }else {
                //操作数有可能是连续的,从后开始读取,直到遇到运算符
                int num = Integer.parseInt(String.valueOf(c));
                while (i < s.length() -1){
                    char c1 = s.charAt(i+1);
                    if(operatorMap.containsKey(c1) || '(' == c1 || ')' == c1){
                        break;
                    }
                    num = num * 10 + Integer.parseInt(String.valueOf(c1));
                    i++;
                }
                operandStack.push(num);
            }
        }
        //因为是触发运算符进行前面的操作,当前运算符还没计算。
        while (!operatorStack.isEmpty()){
            cal(operatorStack,operandStack);
        }
        return operandStack.pop();
    }

    /**
     * 进行计算
     * @param operatorStack
     * @param operandStack
     */
    public void cal(LinkedList<Character> operatorStack, LinkedList<Integer> operandStack) {
        //操作数 ,运算符就该出栈了
        if (!operatorStack.isEmpty()) {
            char operator = operatorStack.pop();
            Integer p1;
            Integer p2;
            switch (operator) {
                case '+':
                    p1 = operandStack.pop();
                    p2 = operandStack.pop();
                    operandStack.push(p2 + p1);
                    break;
                case '-':
                    p1 = operandStack.pop();
                    p2 = operandStack.pop();
                    operandStack.push(p2 - p1);
                    break;
                case '*':
                    p1 = operandStack.pop();
                    p2 = operandStack.pop();
                    operandStack.push(p2 * p1);
                    break;
                case '/':
                    p1 = operandStack.pop();
                    p2 = operandStack.pop();
                    operandStack.push(p2 / p1);
                    break;
                default:
                    operatorStack.push(operator);
                    break;
            }
        }
    }



}
