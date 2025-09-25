package com.itheima;

import java.util.ArrayList;
import java.util.List;

// 通过动态数组双指针实现
public class SolutionOne {
    public boolean isPalindrome(ListNode head) {
        // new一个动态数组-ArrayList
        List<Integer> list = new ArrayList<>();
        // 遍历链表放入动态数组中
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        // 左指针
        int left = 0;
        // 右指针
        int right = list.size() - 1;
        // 遍历动态数组
        while (left < right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++; // 左指针右移
            right--; // 右指针左移
        }
        return true;
    }
}
