package com.itheima;

public class SolutionOne {
    public ListNode reverseList(ListNode head) {
        // 记录头节点
        ListNode previous = null;
        // 当前结点
        ListNode current = head;
        // 一旦迭代到null循环结束
        while (current != null) {
            // 记录原链表下一个指向
            ListNode next = current.next;
            // 设置当前迭代结点指向
            current.next = previous;
            // 将当前迭代结点信息赋值给pre，方便下一个迭代结点设置
            previous = current;
            // 从下一个迭代结点开始
            current = next;
        }
        // 返回反转链表的头节点
        return previous;
    }
}
