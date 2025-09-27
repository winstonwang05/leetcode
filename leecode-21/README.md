# 21.合并两个有序链表

## 思路一：递归

### 递归的核心思想

递归方法就是：
 **“谁小，谁就当头，然后它的 `next` 指向合并后的剩余部分。”**

```
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 基准情况1：如果list1为空，直接返回list2
        if (list1 == null) return list2;
        // 基准情况2：如果list2为空，直接返回list1
        if (list2 == null) return list1;

        // 比较两个链表当前节点的值
        if (list1.val <= list2.val) {
            // 如果list1的值较小，将list1作为当前节点
            // 递归合并list1.next和list2
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;  // 返回较小的节点
        } else {
            // 如果list2的值较小，将list2作为当前节点
            // 递归合并list1和list2.next
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;  // 返回较小的节点
        }
    }
}
```

1. **递归终止条件**
   - 如果 `list1 == null`，说明 `list1` 已经走完了，直接返回 `list2` 剩余部分；
   - 如果 `list2 == null`，说明 `list2` 已经走完了，直接返回 `list1` 剩余部分。
     👉 返回的是 **整条剩余链表**，而不是“最后一个节点”。
2. **递归递推逻辑**
   - 比较 `list1.val` 和 `list2.val`；
   - 如果 `list1.val <= list2.val`：
     - 说明 `list1` 当前节点更小；
     - 把 `list1` 作为当前结果链表的头结点；
     - 然后递归处理 `(list1.next, list2)`，并把返回结果接到 `list1.next` 上；
     - 最终 `return list1`。
   - 如果 `list2.val < list1.val`：逻辑对称。
3. **最终结果**
   - 谁的头结点值小，谁就是 **最终合并后链表的头指针**；
   - 整个递归返回的不是单个 `value`，而是 **拼接好的链表头指针**；
   - 通过逐层 `return`，链表会一步步被串起来。

------

## 🔄 举个例子

```
list1: 1 -> 3 -> 5
list2: 2 -> 4 -> 6
```

递归展开：

- 比较 1 和 2 → 取 1，`1.next = merge(3->5, 2->4->6)`
- 比较 3 和 2 → 取 2，`2.next = merge(3->5, 4->6)`
- 比较 3 和 4 → 取 3，`3.next = merge(5, 4->6)`
- 比较 5 和 4 → 取 4，`4.next = merge(5, 6)`
- 比较 5 和 6 → 取 5，`5.next = merge(null, 6)`
- `list1 == null` → 返回 `6`

逐层返回拼接结果：

```
5 -> 6
4 -> 5 -> 6
3 -> 4 -> 5 -> 6
2 -> 3 -> 4 -> 5 -> 6
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

------

## ✨ 总结修正版（理解 + 精炼）

- **递归的本质**：每次选出两个链表中较小的头节点，把它作为当前结果链表的头，并递归处理剩余部分；
- **终止条件**：某条链表为空时，直接返回另一条链表的剩余部分；
- **返回值**：递归函数返回的是 **新链表的头指针**，通过逐层 return，把整个有序链表拼接起来；
- **头结点的确定**：谁的第一个节点值小，谁就是最终合并后链表的头指针。

首先判断递归到某个节点时候，如果为null就会返回另外一个链表剩余节点指针，其实就是其中一个链表递归到尾节点时候，另外一个链表还剩下剩余节点，为什么？因为题目给的链表是按照升序来处理的，也就是每一个节点的value都是升序的，每次递归进入到判断语句中，会不断return 得到下一个升序的节点value，一旦其中一个链表返回null，另外一个链表剩下节点value都要大，并且链表是按照升序的，直接返回剩余节点指针；在判断语句中，就是判断两个链表的当前递归到的节点value如果是小于等于的一方就要从当前节点指向下一个开始递归，并返回当前节点value，所以两个链表中当前节点value小的那个链表需要指向下一个节点继续递归得到比当前节点value大于或等于的，一旦其中一个链表走完了，递归中指向下一个是null，刚开始的if判断就会返回另外一个链表的最后一个。所以说，从两个链表的第一个节点开始判断value时候，就决定最终是返回合并后的链表是在那个链表中，第一个链表第一个节点比第二个节点第一个节点小，那么合并后的链表就是第一个链表，也就是谁的头最小，最终就是整体合并后的头节点，最终得到的递归返回是头指针

## 思路二：迭代（推荐）

```
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 定义虚拟头结点，方便返回
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        // 其中一个链表还有剩余节点，直接接上
        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }

        return dummy.next;
    }
}
```

## 🎯 核心思想

**虚拟头节点提供统一起点，工作指针负责实际操作，最终返回纯净的合并结果**

## 🔄 算法流程

### 1. **初始化阶段**

java

```
ListNode dummy = new ListNode(-1);  // 创建虚拟头节点
ListNode cur = dummy;               // 工作指针指向虚拟节点
```



- 虚拟节点值可任意（通常用-1），`next`自动初始化为`null`
- 工作指针`cur`负责实际连接操作，`dummy`保持不动

### 2. **迭代比较阶段**

java

```
while (list1 != null && list2 != null) {
    if (list1.val <= list2.val) {
        cur.next = list1;    // 连接较小节点
        list1 = list1.next;  // 移动链表指针
    } else {
        cur.next = list2;
        list2 = list2.next;
    }
    cur = cur.next;          // 移动工作指针
}
```



- 比较两链表当前节点值，连接较小者
- 移动对应链表指针和工作指针
- 循环直至任一链表遍历完

### 3. **收尾阶段**

java

```
cur.next = (list1 != null) ? list1 : list2;
return dummy.next;
```



- 直接连接剩余有序节点
- 返回虚拟节点的`next`（真正的合并链表头）

## 💡 设计亮点

### **虚拟节点的妙用**

- ✅ 统一处理空链表边界情况
- ✅ 避免复杂的头节点特殊判断
- ✅ 保持代码简洁清晰

### **工作指针的作用**

- ✅ `cur`负责"脏活累活"连接节点
- ✅ `dummy`保持纯净用于最终返回
- ✅ 职责分离，逻辑清晰

## 🚀 算法优势

- **时间复杂度**：O(m+n) - 线性遍历两个链表
- **空间复杂度**：O(1) - 只使用常数额外空间
- **代码简洁性**：逻辑统一，边界处理优雅

## 📝 关键理解

虚拟节点不是"相当于null"，而是**创建一个安全的非空起点**，让算法逻辑能够统一处理所有情况，这正是该解法优雅之处！

我的理解：首先new一个虚拟节点，虚拟节点提供了统一的起点，工作指针负责具体操作，最终返回纯净的结果。通过构造方法初始化val为任意数，也就是ListNode(int val) { this.val = val; }，java虚拟机会自动将虚拟节点下一个节点next指向为null，和构造方法ListNode(int val, ListNode next) { this.val = val; this.next = next; }一样，这种可以初始化两个参数，一个是val（任意值），另外一个是null指向。但是第二种看起比较冗余。并将虚拟节点赋值给一个变量，保证纯净的虚拟节点，这个随机变量就相当于是打工的，不断合并链表；通过迭代，结束条件就是其中一个链表中节点指针为null，也就是到达了链尾，然后判断两个链表当前节点哪个小，小的一方就要接在我们定义的变量的指针，并将这个节点所在的链表指向下一个指针；迭代结束，肯定是还有一个链表剩余部分节点指针，所以不为null的就接在我们定义的变量指针；最后返回虚拟节点的指针；虚拟节点就是初始化为null，所以我们迭代之前就不需要在判断头节点是否为null，返回结果就是从虚拟节点指针，得到合并链表
