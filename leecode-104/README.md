# 104.二叉树的最大深度

### 方法一：递归思想

​	通过不断递归左右子树，一旦结点为null，递归终止，并且每一层递归数量加1

```
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(l, r) + 1;
    }
}
```

### 1. **递归终止条件**

- 当递归到某个结点是 `null` 时，说明已经越过了叶子结点，没有深度可言，所以返回 `0`。
- 这是所有递归的「出口」。

------

### 2. **递归返回值的含义**

- `maxDepth(node)` 的返回值就是：**以这个结点为根的子树的最大深度**。
- 所以当我们递归调用 `maxDepth(root.left)` 时，它会返回「左子树的最大深度」。同理右子树。

------

### 3. **取最大值 +1**

- `Math.max(maxDepth(root.left), maxDepth(root.right)) + 1`：
  - `Math.max(...)` 取的是左右子树的 **更大深度**。
  - `+1` 表示：当前这一层的根节点也要算进去。
- 你说的「某个子树返回 0，另一个 +1」的理解就是这个意思。**0 相当于「没有深度」**，所以往上一层的时候，父节点深度 = 子树最大深度 + 1。

------

### 4. **关于 `Math.max` 的细节**

- 如果左右子树深度不同，就取更大的那个。
- 如果左右子树深度一样，就随便取一个（反正相等），结果就是这个深度 +1。
  👉 所以你说的「如果一致就取一样大的结果」完全正确。

------

### 5. **直观例子**

树结构：

```
      1
     / \
    2   3
   / 
  4
```

- `maxDepth(4) = 1` （左右子树都是 0，取 max=0，再 +1）
- `maxDepth(2) = max(1, 0) + 1 = 2`
- `maxDepth(3) = 1`
- `maxDepth(1) = max(2, 1) + 1 = 3`

所以最大深度是 3。

------

✅ 总结：

- 终止条件：节点为空返回 0。
- 返回值：以当前节点为根的子树的最大深度。
- `Math.max`：比较左右子树深度，取大的那个。
- `+1`：算上当前节点。

递归的终止的条件是递归到某节点为null，首先我们递归左子树和递归右子树的所有结点，然后每次递归的结果返回取某个结点左右树长度最大的然后+1，+1目的是递归每一层该节点左右子树其中一个碰到0，终止后是0然后在这层结点+1，以表示深度，反正就是每一次递归有左右子树，左右子树下的结点也是相同情况，一层套一层，我对Math.max方法理解是不是如果某节点左右子树深度都是一致的那么，最大值就取一样大的结果

## 方法二：迭代-BFS（层序遍历）

```
import java.util.ArrayDeque;
import java.util.Deque;

public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    Deque<TreeNode> queue = new ArrayDeque<>(); // ArrayDeque 推荐，比 LinkedList 快
    queue.offer(root);
    int depth = 0;
    while (!queue.isEmpty()) {
        int size = queue.size(); // 当前层节点数
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (node.left != null)  queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        depth++; // 这一层处理完，深度 +1
    }
    return depth;
}

```

