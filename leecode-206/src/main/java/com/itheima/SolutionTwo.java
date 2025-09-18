package com.itheima;

public class SolutionTwo {
    public ListNode reverseList(ListNode head) {
        // 空链表以及只有一个结点包括最后一个结点
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);// 递归
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
