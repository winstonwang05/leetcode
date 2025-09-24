# 101.对称二叉树

## 方法一：递归（镜像比较）

思想：判断左子树和右子树是否互为镜像。对两个节点 p,q：

- 若都为 null -> 镜像（true）
- 若只有一个为 null 或 值不相等 -> 不是镜像（false）
- 否则递归比较 p.left vs q.right 且 p.right vs q.left

```
public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    return isMirror(root.left, root.right);
}

private boolean isMirror(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    if (p.val != q.val) return false;
    return isMirror(p.left, q.right) && isMirror(p.right, q.left);
}
```

时间复杂度：O(n)（每个节点访问一次）
 空间复杂度：O(h) 递归栈，h 为树高（最坏 O(n)）

#### 从最开始的根节点左右孩子的根节点开始递归

## 方法二 — 迭代（队列 / BFS 模拟镜像比较）

​	

```
import java.util.ArrayDeque;
import java.util.Deque;

public boolean isSymmetricIterative(TreeNode root) {
    if (root == null) return true;
    Deque<TreeNode> dq = new ArrayDeque<>();
    dq.addLast(root.left);
    dq.addLast(root.right);

    while (!dq.isEmpty()) {
        TreeNode p = dq.removeFirst();
        TreeNode q = dq.removeFirst();

        if (p == null && q == null) continue;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        // 按镜像顺序加入
        dq.addLast(p.left);
        dq.addLast(q.right);

        dq.addLast(p.right);
        dq.addLast(q.left);
    }
    return true;
}

```

如果为空树，那么直接返回true；

然后new一个链表结构来表示队列

添加左右孩子结点到队列中

先进队列的先出队列，通过removeFirst，然后赋值给一个随机变量，以便后续判断它的左右孩子结点对称

## 细节：

#### 1.如果结点都是null，不能直接返回true，而是continue；和递归逻辑处理上不同。

递归属于是深度搜索：

```
 return isMirror(left.left, right.right)  // 先深入检查这一侧
    && isMirror(left.right, right.left); // 等左侧完全结束后才检查右侧
```

因为是&&要同时成立，所以递归是完成了左右孩子的遍历

迭代是广度搜索：

```
dq.addLast(p.left);    // 左子树的左孩子
dq.addLast(q.right);   // 镜像位置的右子树的右孩子

dq.addLast(p.right);   // 左子树的右孩子  
dq.addLast(q.left);    // 镜像位置的右子树的左孩子
```

先遍历完左孩子，但是右孩子可能还没有遍历完全，递归是都遍历完了 

#### 例如：

**情况分析：**

text

```
    1
   / \
  2   2
 /   /
3   3
```



队列处理过程：

1. 初始：`[2, 2]`
2. 比较2和2 → 值相等，加入子节点：`[3, null]`, `[null, 3]`
3. 比较3和null → 直接返回false（不对称）
4. **如果之前遇到 `(null, null)` 就返回true，会错过后面的不对称情况**

2.

## 2. 三者关系总结

| 类名           | 角色          | 底层结构 | 是否推荐           |
| -------------- | ------------- | -------- | ------------------ |
| **Stack**      | 栈            | Vector   | ❌ 不推荐（老旧）   |
| **LinkedList** | 双端队列/链表 | 双链表   | ✅ 可用，但性能一般 |
| **ArrayDeque** | 双端队列/栈   | 循环数组 | ✅ 推荐（现代写法） |

### 1. 定义方式

以后写 **栈 / 队列**，一般都推荐用接口 `Deque<Type>` 来声明，灵活性最高：

```
Deque<Integer> stack = new ArrayDeque<>();   // 当栈用
Deque<Integer> queue = new ArrayDeque<>();   // 当队列用
```

同样也可以用 `LinkedList<>`：

```
Deque<Integer> stack = new LinkedList<>();
Deque<Integer> queue = new LinkedList<>();
```
