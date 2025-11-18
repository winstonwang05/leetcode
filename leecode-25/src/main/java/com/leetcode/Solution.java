package com.leetcode;


/**
 * @ClassName: Solution
 * @Description: TODO (这里描述这个类的作用)
 * @Author: Winston
 * @Date: 2025/11/18 18:51
 * @Version: 1.0
 */
public class Solution {
    public ListNode reverseGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode end = prev;
        while (true) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = prev.next;
            ListNode move = start.next;
            for (int j = 0; j < k - 1; j++) {
                start.next = move.next;
                move.next = prev.next;
                prev.next = move;
                // 更新移动节点
                move = start.next;
            }
            prev = start;
            end = prev;

        }
        return dummy.next;

    }
}