package com.tianji;

public class ListNode {
    // 值
    int val;
    // 每一个结点的地址
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null; // 默认指向下一个结点地址为空，需要手动设置
    }
}
