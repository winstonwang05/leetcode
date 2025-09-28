public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
// 1. 创建虚拟节点（三个作用）
ListNode dummy = new ListNode(0);
ListNode current = dummy;
int carry = 0;

    // 2. 循环条件：l1不为空 OR l2不为空 OR 有进位
    while (l1 != null || l2 != null || carry != 0) {
        // 3. 处理可能为空的节点
        int val1 = (l1 != null) ? l1.val : 0;
        int val2 = (l2 != null) ? l2.val : 0;
        
        // 4. 模拟竖式加法
        int total = val1 + val2 + carry;
        carry = total / 10;  // 更新进位
        int digit = total % 10;  // 当前位数字
        
        // 5. 构建结果链表
        current.next = new ListNode(digit);
        current = current.next;
        
        // 6. 移动指针（只有当前节点不为空时才移动）
        if (l1 != null) l1 = l1.next;
        if (l2 != null) l2 = l2.next;
    }
    
    // 7. 返回纯净的结果链表
    return dummy.next;
}