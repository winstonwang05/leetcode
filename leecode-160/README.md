# 160.相交链表

## 链表的结构

![image-20250915103348889](C:\Users\Winston\AppData\Roaming\Typora\typora-user-images\image-20250915103348889.png)

```
package com.itheima.ListNode;

public class ListNode {
    // 值
    int val;
    // 每一个结点的地址
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null; // 默认指向下一个结点地址为空，需要手动设置
    }
}
```

## 链表之间通过代码是如何连接的

#### 	首先是每一个结点的构造	

```
ListNode node1 = new ListNode(10);
ListNode node2 = new ListNode(20);

node1.next = node2; // 把 node2 的地址放进 node1 的 next 属性里

```

#### 	通过循环遍历连接

​	

```
ListNode cur = node1;   // 从头节点开始
while (cur != null) {
    System.out.println(cur.val);
    cur = cur.next;     // 移动指针，走到下一个节点
}

```

首先理解每一个结点是如何构造的，所以将下一个结点的地址赋值到上一个结点的next属性中，所以next属性就是下一个结点的地址，遍历时候，遍历到某一个结点时候，通过next赋值给当前结点，这样就连起来了，所以说是单个结点是如何构造的，然后再循环连接赋值

#### 🚩 题目要求

给你两个链表 `headA` 和 `headB`，找出它们**第一个相交的节点**，如果没有相交返回 `null`。
 **注意：** 相交指的是 **引用相同**，不是值相同。

------

## 🔑 思路一：哈希表

- 用一个集合存储链表 A 的所有节点引用；
- 遍历链表 B，检查它的节点是否在集合中；
- 第一个命中的就是交点。

#### 核心：指向的地址相同（引用地址相同），自然内存中的位置相同

比较方法：通过 == 

区分 = ，== ，equals，

​	1️⃣ =  是赋值

​	2️⃣ ==  如果是基本类型比较的是值；如果是引用类型是比较地址值

​	3️⃣ `.equals()` 方法

- 默认实现跟 `==` 一样，也是比较引用。
- 但一些类（比如 `String`）重写了 `.equals()`，变成了比较内容。

## 实现

```
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    Set<ListNode> set = new HashSet<>();
    while (headA != null) {
        set.add(headA);
        headA = headA.next;
    }
    while (headB != null) {
        if (set.contains(headB)) {
            return headB;
        }
        headB = headB.next;
    }
    return null;
}

```

## 🔑 思路二（最优解）：双指针

```
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;

    ListNode pA = headA, pB = headB;

    while (pA != pB) {
        pA = (pA == null) ? headB : pA.next;
        pB = (pB == null) ? headA : pB.next;
    }
    return pA; // 这里返回 pB 也是一样的
}

```

### 实现思路

## 核心思路（一句话）

用两个指针 `pA`、`pB` 分别从 `headA` 和 `headB` 开始走。走到链表末尾时，指针跳到另一个链表的头继续走。若存在交点，两个指针最终会在第一个相交节点相遇；否则都会同时到达 `null`。

------

## 为什么能相遇（形式化证明）

设：

- 链表 A 在交点之前的长度为 `a`（不含交点部分），
- 链表 B 在交点之前的长度为 `b`，
- 交点之后（包含交点）的长度为 `c`。
   即 A 总长度 = `a + c`，B 总长度 = `b + c`。

当 `pA` 先走完 A 后再走 B 的非交部分，它走的总路径是：`a + c + b`。
 当 `pB` 先走完 B 后再走 A 的非交部分，它走的总路径是：`b + c + a`。
 两者路径长度相同，所以如果存在交点，它们必然在交点处同时到达（即指向同一个节点引用）。
 如果没有交点，则 `c = 0`，两指针最终都会变为 `null`，并相等，从而返回 `null`。

如果链表相交，它们尾部的路径是相同的（同一段内存引用）。

双指针各自走完自己链表后，再切换到对方链表，最终一定会在交点相遇。

如果链表不相交，那么两个指针都会走到 `null`，自然退出循环。

------

## 算法（伪代码 / 关键循环）

```
ListNode pA = headA;
ListNode pB = headB;

while (pA != pB) {
    pA = (pA == null) ? headB : pA.next;
    pB = (pB == null) ? headA : pB.next;
}
return pA; // 相交点或 null
```

**要点**：

- 用 `==` 比较引用（不是比较值）。

- 当 `pA` 到 `null` 时，改为指向 `headB`，同理 `pB` 到 `null` 时改为 `headA`。

- 循环最多进行 `m + n` 次（m、n 为两链表长度），一定终止。

  ## 为何需要自由变量当头结点

```
ListNode pA = headA;
ListNode pB = headB;
```

### 1. 链表入口的重要性

链表不像数组那样可以随机访问。

- **唯一的入口就是头节点**（`head`）。
- 如果在遍历过程中直接改了 `head`，那就等于把入口挪走了，遍历完第一个链表后，你就找不到它了。去找的时候就会发现头结点被修改，无法去走另外一个链表
