package com.itheima;
// 快慢指针 + 反转链表
public class SolutionTwo {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        // 一旦慢指针指向中间位置循环结束
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p = head;
        // 反转后半部分链表
        ListNode secPart = reverse(slow.next);
        ListNode q = secPart;
        boolean res = true;
        while (q != null) {
            if (p.val != q.val) {
                res = false;
                break;
            }
            q = q.next;
            p = p.next;

        }
        // 恢复后半部分链表
        slow.next = reverse(secPart);
        return res;

    }
    // 反转链表
    private ListNode reverse(ListNode head) {
        ListNode prev = null,curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;

    }
}
