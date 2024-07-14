package stack;

import java.util.LinkedList;

/**
 * @Desc: 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * <p>
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 * 提示：
 * -231 <= val <= 231 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 104 次
 * @Author：zhh
 * @Date：2024/7/14 17:38
 */
public class MinStack {

    Node head;
    Node tail;
    int min = Integer.MAX_VALUE;

    /**
     * 手写一个双向链表模拟栈,每次进栈记录最小值,出栈时如果该元素是最小值,重新计算最小值。
     */
    public MinStack() {

    }

    public void push(int val) {
        //记录最小值
        min = Math.min(min, val);
        //入栈
        Node node = new Node(null, null, val);
        if (tail == null) {
            tail = head = node;
        } else {
            node.pre = tail;
            tail.next = node;
            tail = node;
        }
    }

    public void pop() {
        int var = tail.var;
        //出栈
        Node pre = tail.pre;
        tail.pre = null;
        if(pre != null){
            pre.next = null;
            tail = pre;
        }else {
            head = tail = null;
        }
        //重新计算最小值
        if(min == var){
            Node tmp = head;
            min = Integer.MAX_VALUE;
            while (tmp!=null){
                min = Math.min(min, tmp.var);
                tmp = tmp.next;
            }
        }

    }

    public int top() {
        return tail.var;
    }

    public int getMin() {
        return min;
    }

    class Node {
        Node next;
        Node pre;
        int var;

        public Node(Node next, Node pre, int var) {
            this.next = next;
            this.pre = pre;
            this.var = var;
        }
    }
}
