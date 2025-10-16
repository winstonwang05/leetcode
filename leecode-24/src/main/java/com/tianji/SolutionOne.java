package com.tianji;
// 迭代
public class SolutionOne {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode first = pre.next;
            ListNode second = pre.next.next; // first.next

            // 交换
            first.next = second.next;
            second.next = first;
            pre.next = second;

            // 移动pre
            pre = first;
        }
        return dummy.next;
    }
}
