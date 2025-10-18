# 98.验证二叉搜索树

## 思路一：递归 + 收缩区间

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return helper(node.left, min, node.val) 
            && helper(node.right, node.val, max);
    }
}

```

## ✅ 验证二叉搜索树 —— 递归 + 收缩区间法总结

1. **核心思想**
   整棵树要满足二叉搜索树（BST）性质：

   > 左子树所有节点值 < 根节点值 < 右子树所有节点值。

   因此，每个节点都必须位于一个动态变化的「合法区间」中，这个区间会随着递归不断**收缩**。

------

1. **初始区间**
   初始时没有任何限制，可以认为范围是：

   ```
   (-∞, +∞)
   ```

   在代码中，为了避免整数边界冲突，用 `Long.MIN_VALUE` 和 `Long.MAX_VALUE` 表示“无限小”和“无限大”，
   这样能确保所有 `int` 类型的节点值都处于这个范围内。

------

1. **递归逻辑**
    - 若当前节点为 `null`，说明已到叶子节点，返回 `true`；
    - 若当前节点的值不在 `(min, max)` 区间内，返回 `false`；
    - 否则继续递归检查左右子树：
        - 左子树的合法区间是 `(min, node.val)`
        - 右子树的合法区间是 `(node.val, max)`

------

1. **区间收缩的意义**
    - 每深入一层递归，当前节点的值就成为下一层的上界或下界；
    - 这样可以保证：
        - 左子树的所有节点都严格小于根节点；
        - 右子树的所有节点都严格大于根节点；
    - 即使子树跨层（例如左子树中的右孩子），它也不能突破最初根节点设下的区间限制。

首先范围需要从无穷小到无穷大区间，如果我们用int的min和max还是不够大，会存在溢出问题，所以使用更大一个范围的，那就是Long类型的；然后递归的思想就是如果是空树，那么直接返回true，如果不在当前节点在区间之内，也就是如果当前节点传入的是左节点大于等于了min或者右节点小于等于了max，那么需要发会false，总之需要的区间范围就是(min, max);递归是传入当前节点，如果是左节点，那么max就是当前节点值，如果是右节点，那么min就是当前节点值，总之就是让左节点小于根节点，右节点大于根节点，比较的是值大小；这种方式就很好的避免了如果递归中，比如左子树，遍历到的右孩子不可能比根节点大，因为传入根节点，会在右子树区间设置最小值为根节点值大小，不满足直接false;总结一下

### ✅ 方法二：中序遍历（Inorder Traversal） - 待做

**核心思想：**

- 二叉搜索树的中序遍历结果一定是 **严格递增** 的序列；
- 只要在中序遍历时发现当前节点值 ≤ 前一个节点值，即可判定为 `false`。

#### 递归实现：

```
class Solution {
    long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (root.val <= prev) return false;
        prev = root.val;
        return isValidBST(root.right);
    }
}
```

#### 迭代实现（中序遍历栈版）：

```
class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        long prev = Long.MIN_VALUE;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (curr.val <= prev) return false;
            prev = curr.val;
            curr = curr.right;
        }
        return true;
    }
}
```

------

## 📚 四、对比总结

| 方法       | 思想                   | 优点               | 缺点                 |
| ---------- | ---------------------- | ------------------ | -------------------- |
| 递归范围法 | 每个节点约束在合法区间 | 清晰、通用、易扩展 | 需要传参上下界       |
| 中序遍历法 | 利用中序递增特性       | 简洁、直观         | 需理解中序序列单调性 |