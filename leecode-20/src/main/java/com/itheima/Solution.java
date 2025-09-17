package com.itheima;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public boolean isValid(String s) {
        // 判断是否为空
        // 判断长度是否为奇数
        if (s == null || s.length() % 2 != 0) return false;
        Deque<Character> stack = new ArrayDeque<>();
        // 遍历数组，遇到左括号就进栈
        for (char c : s.toCharArray()) {
            switch (c) {
                case '{':
                case '(':
                case '[':
                    stack.push(c);
                    break;
                // 一旦遇到右括号就判断栈顶的是否匹配或者栈为空
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                    default:
                        return false;// 非法字符
            }

        }
        // 遍历完判断栈是否为空（为空说明完全匹配，不为空说明还有左括号未被右括号消耗）
        return stack.isEmpty();
    }
}
