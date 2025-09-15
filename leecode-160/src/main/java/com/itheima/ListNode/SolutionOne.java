package com.itheima.ListNode;


import java.util.HashSet;
import java.util.Set;

public class SolutionOne{
    public ListNode removeElements(ListNode headA, ListNode headB) {
        // 创建单列集合存储结点
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            // 存储结点到集合中
            set.add(headA);
            // 每次遍历将下一个节点的地址存储在上一个结点中
            headA = headA.next;
        }
        // 遍历另外一个链表
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        // 不存在相交链表就返回null
        return null;
    }
}
