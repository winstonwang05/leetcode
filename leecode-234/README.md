# 234.回文链表

## 方法一：数组辅助（简单直观）

- 遍历链表，把所有值存入数组。
- 用双指针比较数组是否回文。
  时间复杂度 **O(n)**，空间复杂度 **O(n)**。

```
class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

### ArrayList 动态数组法实现步骤（方法一）

1. **遍历链表 → 存入动态数组**
   - 从头结点开始，逐个访问节点的 `val`。
   - 每访问一个节点，就把 `val` 存入 `ArrayList`。
   - 遍历结束条件：当前节点的 `next == null`（说明到达最后一个节点）。
   - 这样就把链表的线性结构转换成了数组结构。
2. **双指针判断回文**
   - 定义两个指针：`left = 0`（指向数组开头），`right = size - 1`（指向数组末尾）。
   - 每轮比较：检查 `list.get(left)` 是否等于 `list.get(right)`。
   - 如果有一次不相等 → 直接返回 `false`。
   - 比较结束条件：
     - 当 `left >= right` 时，说明已经完成比较：
       - **偶数长度链表**：最终会走到 `left > right`。
       - **奇数长度链表**：最终会走到 `left == right`。
   - 如果都相等 → 返回 `true`。

ArrayList动态数组的实现我的理解：首先遍历整个链表，循环结束就是节点指向下一个节点为null说明就是最后一个节点，循环逻辑就是添加到动态数组中，然后不断指向下一个节点指针，遍于下一次遍历，然后就是遍历动态数组判断是否是回文，也就是从第一个索引和最后一个索引比较大小，也就是双指针来指向遍历，结束条件就是两个指针指向位置相同也就是奇数个，左指针索引大于右索引说明偶数个遍历完，然后就按反逻辑来判断简单，每一次判断完，左指针右移，右指针左移，知道循环结束，就返回true

### 方法二：快慢指针 + 链表反转（最优解）

1. 使用快慢指针找到链表的中点。
   - 快指针一次走两步，慢指针一次走一步。
   - 当快指针到尾部时，慢指针在中点。
2. 将后半部分链表反转。
3. 前半部分与反转后的后半部分逐个比较。
4. （可选）恢复链表结构。

时间复杂度 **O(n)**，空间复杂度 **O(1)**。

```
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // 1. 找到中点（奇数取中点，偶数取左中点）
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转后半部分
        ListNode secondHalf = reverse(slow.next);

        // 3. 比较前后半部分
        ListNode p1 = head, p2 = secondHalf;
        boolean result = true;
        while (p2 != null) {
            if (p1.val != p2.val) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. 可选：恢复链表
        slow.next = reverse(secondHalf);

        return result;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}
```

### 快慢指针 + 反转链表解法步骤（O(1) 空间）

1. **判空**

   - 如果链表为空（`head == null`），或只有一个节点（`head.next == null`），那么直接返回 `true`，因为肯定是回文。

2. **快慢指针找中点**

   - 定义 `fast` 和 `slow` 指针：

     - `fast` 一次走两步。
     - `slow` 一次走一步。

   - 循环条件：

     ```
     while (fast.next != null && fast.next.next != null) {
         slow = slow.next;
         fast = fast.next.next;
     }
     ```

   - 结束时：

     - **奇数节点**：`fast` 指向最后一个节点（因为走两步时提前停下），`slow` 指向 **中点**。
     - **偶数节点**：`fast` 指向倒数第一个节点，`slow` 指向 **前半部分最后一个节点**。

3. **反转后半部分链表**

   - 从 `slow.next` 开始反转链表，得到后半部分的逆序链表。
   - 此时链表结构变成：前半部分（正序） + 后半部分（逆序）。

4. **比较前后两部分**

   - 设置两个指针：
     - `p1 = head`（从链表头开始）。
     - `p2 = reversedHead`（从反转后的后半部分头开始）。
   - 逐个比较 `p1.val` 和 `p2.val`，如果有不相等，返回 `false`。
   - 遍历结束（`p2 == null`）则说明前后半部分完全相等，返回 `true`。

5. **可选：恢复链表**

   - 为了不改变原始链表结构，可以把后半部分再反转一次，接回 `slow.next`。
   - 这在面试中经常加分，证明你考虑到链表结构不被破坏。

首先判空，如果是空链表或者一个节点那么就是回文链表，接着，定义一个快指针，一个慢指针，快指针一次右移两个节点，慢指针一次右移一个节点，不断迭代，循环结束条件就是奇数个节点，快指针指向的下一个再下一个为null，相当于指向倒数第二个时候，下一次就结束（fast.next.next），此时慢指针就会指向中间的位置，如果是偶数个节点，也就是快指针到达了最后一个节点，下一次就会退出循环(fast.next)，此时慢指针就会指向前半部分的最后一个节点，通过反转此时慢指针指向下一个节点的部分，通过迭代比较val是否一致，可选恢复链表位置
<img width="820" height="1950" alt="快慢指针 + 反转链表 drawio" src="https://github.com/user-attachments/assets/566a6780-2349-4b41-9521-58ba171edd01" />


