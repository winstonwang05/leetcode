# 142.环形链表

## 思路一：哈希表

```
public ListNode detectCycle(ListNode head) {
    Set<ListNode> visited = new HashSet<>();
    while (head != null) {
        if (visited.contains(head)) return head;
        visited.add(head);
        head = head.next;
    }
    return null;
}

```

## 思路二：快慢指针

```
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // 1. 判断是否有环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // 2. 找入口
                ListNode index1 = head;
                ListNode index2 = slow;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1; // 环的入口
            }
        }
        return null;
    }
}

```

1. **判空处理**

    - 如果 `head == null || head.next == null`，说明无环，直接返回 `null`。
    - 这是为了防止空指针异常，同时提前排除最简单的情况。

2. **快慢指针判断是否有环**

    - 初始化：`slow = head`, `fast = head`。
    - 循环中：`slow = slow.next`, `fast = fast.next.next`。
    - 如果 `fast` 或 `fast.next` 为 `null`，说明链表无环；
    - 如果 `slow == fast`，说明两者相遇，链表有环。

3. **数学推导（核心部分）**
   设：

    - `a` = 从头节点到环入口的距离
    - `b` = 环入口到相遇点的距离
    - `c` = 相遇点到环入口的距离（环的剩余长度）

   则：

   ```
   慢指针路程 = a + b
   快指针路程 = a + b + n(b + c)
   因为快指针速度是慢指针的两倍：
   2(a + b) = a + b + n(b + c)
   => a = n(b + c) - b
   => a = (n - 1)(b + c) + c
   ```

   🔍 解释：

    - 从相遇点出发再走 `c` 步，就能回到环入口；
    - 同时从头节点出发走 `a` 步，也能到达环入口；
    - 所以两个指针从 **头节点** 和 **相遇点** 同时出发，步速相同，最终会在环入口相遇。

4. **实现寻找环的入口**

    - 相遇后，保留一个指针在相遇点；
    - 另一个从头节点重新出发；
    - 两者以相同速度移动；
    - 相遇时的位置即为环入口。

## 🎯 总结关键点

| 步骤     | 目的         | 核心思想             |
| -------- | ------------ | -------------------- |
| 判空     | 防止空指针   | 链表长度不足以形成环 |
| 快慢指针 | 判断有无环   | 速度差导致必相遇     |
| 数学推导 | 建立路径关系 | `a = (n-1)(b+c) + c` |
| 再次相遇 | 定位环入口   | 从头和相遇点同步前进 |

首先进行判空处理，针对的是无环链表，然后初始化快慢指针，都从头节点出发，快指针的速度是慢指针的速度的两倍，所以其实快指针一般都会走n次环长度最终和慢指针相遇，n不太可能为0，相遇就表明存在环，现在知道有环了，但是需要返回环的起点索引；a表示头节点到环起点，b表示环起点到两个指针相遇的位置，c表示相遇位置到环起点，所以说b+c就是整个环的长度，快指针的速度是慢指针的两倍，接着我们通过数学计算得到a = n倍环长度  + c ，从等式右边看，也就是从相遇那时候开始，走c步就到达了环起点，然后加上n次环长度等于头节点到环起点，根据这个等式，我们将快慢指针中其中一个指针留在相遇点，一个指针放置在头节点位置，速度都相同，所以最终根据这个等式会相遇（也就是相等时候），那么此时指向的相等的位置就是环起点返回就行

