package com.xuecheng;

import java.util.HashSet;
import java.util.Set;

// 哈希set
public class SolutionOne {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            }
            set.add(cur);
            cur = cur.next;
        }
        return false;
    }
}
