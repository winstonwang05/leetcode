package com.xuecheng;

import java.util.HashSet;
import java.util.Set;

// 哈希表存储解决
public class SolutionOne {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}
