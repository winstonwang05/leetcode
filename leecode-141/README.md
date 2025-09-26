# 141.环形链表

## 思路 1 — 哈希表

- 遍历链表，把每个访问过的节点地址放进 `HashSet`。
- 遇到已存在的节点即有环（返回 `true`）。
- 时间 O(n)，空间 O(n)。

```
public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (set.contains(current)) {
                return true;
            }
            set.add(current);
            current = current.next;
        }
        return false;
    }
}
```

## 思路 2 — 快慢指针（Floyd 判圈，推荐）

```
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
```



### 1. 判空处理

- **必要性**：如果 `head == null`，或者链表长度为 1（即 `head.next == null`），那一定没有环，直接返回 `false`。
- 注意这里返回的不是 `null`，而是布尔值 `false`（因为本题要求的是判断有无环）。

------

### 2. 快慢指针起点

- 通常写法是 **快慢指针都从 `head` 出发**，这样逻辑更统一。
- 有些写法让 `fast` 从 `head.next` 出发，也是可行的，但代码要额外注意循环条件。

你说的「两个节点成环不断循环而不会相遇」这种情况不会发生，因为只要有环，快指针相对慢指针速度为 1，最终一定会在环内追上。

------

### 3. 循环结束条件

- **无环情况**：

    - 如果链表节点数是奇数，`fast.next == null` 时退出循环。

    - 如果链表节点数是偶数，`fast == null` 时退出循环。

    - 所以循环条件写作：

      ```
      while (fast != null && fast.next != null)
      ```

      保证不会出现空指针。

------

### 4. 为什么一定会相遇（有环情况）

- **相对速度原理**：慢指针速度 = 1，快指针速度 = 2。相对速度 = 1。
- 在环内，假设慢指针先进入环，快指针落后（或者也在环里），那么他们的距离每次缩小 1。
- 最迟在「环的长度」次移动内，快指针就会追上慢指针。

所以一旦进入环，他们一定会相遇。

------

### 5. 结果判断

- 如果循环中出现 `slow == fast`，就说明有环，直接返回 `true`。
- 如果循环结束（`fast == null` 或 `fast.next == null`），就说明无环，返回 `false`。

#### 总结：快慢指针法就是利用相对速度，在有限环长的空间内，快指针必然追上慢指针；而如果链表没有环，快指针必然先走到空节点，循环结束返回 false。

这道题理解：首先进行头结点判空处理，以便后续循环进行，如果头节点为空直接返回null，将两个快慢指针都从头节点开始，防止在两个节点成环不断循环而不会相遇，因为两个一直会有固定距离，循环结束的条件是：如果是奇数个节点时，fast.next就会为null，如果时偶数个节点时，那么fast就是null；通过慢指针走一步，快指针走两步，如果两个指向相等，那么相遇，循环结束了，那么肯定没相遇；中间遍历过程，快指针虽然比慢指针相对速度上快一步，但是遍历过程中，快指针会不断回环与慢指针相遇，直到相对距离为1时候，下一次循环就会相遇