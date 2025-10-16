# 24.两两交换链表中的节点

## 思路一：迭代

```java
public class Solution {
    public ListNode swapPairs(ListNode head) {
        // 虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev 指向每一对节点的前一个节点
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            // 定义要交换的两个节点
            ListNode first = prev.next;
            ListNode second = first.next;

            // 交换节点
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 移动 prev
            prev = first;
        }

        return dummy.next;
    }
}

```

## ✅ 迭代法标准逻辑

**题意理解：**
题目要求的是 **交换相邻节点的指针指向**，不能改变节点的值。
也就是说，原链表节点值的顺序要靠修改 `next` 指针来完成重排。

------

### 🧠 为什么要用虚拟头节点（dummy）

- 因为头节点也可能被交换（例如 `1->2->3->4` → `2->1->4->3`，头节点从 `1` 变成了 `2`）。
- 所以我们引入一个虚拟节点 `dummy` 指向 `head`，方便统一处理“头节点被换掉”的情况。
- 最后返回时直接 `return dummy.next` 即可拿到新的头节点。

------

### ⚙️ 关键指针定义

我们使用三个指针：

- `prev`：指向本轮要交换的两个节点前一个节点；
- `first`：本轮要交换的第一个节点；
- `second`：本轮要交换的第二个节点。

------

### 🔁 迭代过程（以 1→2→3→4 为例）

初始：

```
dummy -> 1 -> 2 -> 3 -> 4
  ↑
 prev
```

循环条件：

```
while (prev.next != null && prev.next.next != null)
```

保证还有成对的节点可以交换。

------

### 🔄 交换步骤（核心3步）

1️⃣ 记录要交换的节点：

```
first = prev.next;      // 第一个节点
second = first.next;    // 第二个节点
```

2️⃣ 改变指针指向（交换核心）：

```
first.next = second.next; // 让第一个节点的 next 指向第二个节点的下一个节点
second.next = first;      // 让第二个节点的 next 指向第一个节点
prev.next = second;       // 让前驱节点指向第二个节点（完成一对交换）
```

此时链表变为：

```
dummy -> 2 -> 1 -> 3 -> 4
```

3️⃣ 移动 prev 到下一对的前驱节点：

```
prev = first;
```

------

### 🧩 继续循环直到无法成对交换

最后返回：

```
return dummy.next;
```

------

### ✅ 完整示意图

| 交换前                | 交换后                | prev 位置     |
| --------------------- | --------------------- | ------------- |
| dummy → 1 → 2 → 3 → 4 | dummy → 2 → 1 → 3 → 4 | prev 移动到 1 |
| dummy → 2 → 1 → 3 → 4 | dummy → 2 → 1 → 4 → 3 | prev 移动到 3 |

### ⚖️ 复杂度分析

- **时间复杂度：** O(n)（遍历一次链表）
- **空间复杂度：** O(1)（只使用常量额外空间）

------

✅ **总结一句话版本：**

> 使用虚拟头节点统一处理交换逻辑，每次通过 `prev`、`first`、`second` 三个指针完成局部两两交换，迭代更新直到链表末尾。

首先题目的意图就是将相邻的节点指针指向要改变，不能改变节点的值大小；因此我们就要清晰的知道两个交换的节点以及不能使中间跳一个之后，交换所跳过节点后两个节点；这里使用迭代法来遍历链表来实现相邻节点的交换；由于迭代法是从头结点开始遍历，有可能头指针为空等情况，所以需要虚拟节点来帮助，还以便返回一个纯净的新链表；而递归通过是从头结点开始递归到最里层（也就是末尾节点），然后回溯的时候组成新链表，所以头结点通常不用的虚拟节点来帮助；实现：首先需要初始化一个虚拟节点，接着重点就是初始化两个交换节点的前一个结点pre，这个结点就是确认清晰我们是每两个相邻节点交换指针指向，交换后pre指向就是两个交换后的末尾节点；接着需要迭代遍历链表，为了保证始终有两个节点，也就是如果一个链表最后相邻节点交换之后还剩下一个节点，这个节点就不能实现了，所以迭代的结束条件就是指向下一个和下下个不为null；接着需要定义需要交换的两个节点，第一个就是pre下一个指针初始化为first，第二个交换的节点就是first.next初始化为second；然后就是重点交换，，first指针指向变为second.next，然后需要second下一个指针变为first;然后需要将pre指向first，这样就完成了交换，最后还需要将pre位置变为交换后的末尾节点方便下一个相邻节点的交换；遍历结束最后返回虚拟节点下一个指针就行

# 思路二：递归（待解决）

```java
public class Solution {
    public ListNode swapPairs(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}

```

