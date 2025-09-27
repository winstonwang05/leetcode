# 543.二叉树的直径

## 思路：递归

```
class Solution {
    private int maxValue = 0;
    public int diameterOfBinaryTree(TreeNode root) {
      Depth(root);
      return maxValue;
    }
    private int Depth(TreeNode node) {
        if (node == null) return 0;
        int leftDepth = Depth(node.left);
        int rightDepth = Depth(node.right);
        maxValue = Math.max(maxValue, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
```

## 🎯 问题定义

**二叉树的直径**：二叉树中任意两个节点之间最长路径的**边数**（注意不是节点数）。

- 路径可能不经过根节点
- 直径 = 所有节点中「左子树深度 + 右子树深度」的最大值
- 深度 = 从节点到叶子节点的最长路径的边数

## 💡 核心思路

使用**后序遍历**（深度优先搜索），在计算每个节点深度的同时，更新全局最大直径。

## 🔑 关键概念区分

1. **深度**（递归返回值）：从当前节点到最远叶子节点的**边数**
    - 空节点深度 = 0
    - 叶子节点深度 = 0
    - 非叶子节点深度 = max(左深度, 右深度) + 1
2. **直径**（全局变量）：经过当前节点的最长路径**边数**
    - 直径 = 左子树深度 + 右子树深度
    - 全局记录所有节点中的最大值

## ⚙️ 算法流程

java

```
class Solution {
    private int maxDiameter = 0;  // 全局变量记录最大直径
    
    public int diameterOfBinaryTree(TreeNode root) {
        getDepth(root);      // 递归计算深度（顺便更新直径）
        return maxDiameter;  // 返回全局最大值
    }
    
    private int getDepth(TreeNode node) {
        // 1. 递归终止条件
        if (node == null) return 0;
        
        // 2. 递归获取左右子树深度
        int leftDepth = getDepth(node.left);   // 左子树深度
        int rightDepth = getDepth(node.right); // 右子树深度
        
        // 3. ⭐先更新直径（核心逻辑）
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        
        // 4. ⭐后返回深度（给父节点用）
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```



## 🔍 执行过程详解

以二叉树为例：

text

```
      1
     / \
    2   3
   / \     
  4   5
```



### 递归过程：

1. **节点4**：左深度=0, 右深度=0 → 直径=0 → 返回深度=1
2. **节点5**：左深度=0, 右深度=0 → 直径=0 → 返回深度=1
3. **节点2**：
    - 左深度=1(节点4), 右深度=1(节点5)
    - 直径=1+1=2 → 更新maxDiameter=2
    - 返回深度=max(1,1)+1=2
4. **节点3**：左深度=0, 右深度=0 → 直径=0 → 返回深度=1
5. **节点1**：
    - 左深度=2(节点2), 右深度=1(节点3)
    - 直径=2+1=3 → 更新maxDiameter=3
    - 返回深度=max(2,1)+1=3（此返回值未被使用）

**最终结果**：maxDiameter = 3

## 💎 重点总结

1. **两个任务分离**：
    - 更新直径（全局变量）
    - 返回深度（给父节点用）
2. **执行顺序**：先更新直径，后返回深度
3. **根节点返回值**：虽然未被使用，但为了代码统一性而保留
4. **时间复杂度**：O(n)，每个节点访问一次
5. **空间复杂度**：O(h)，递归栈深度（h为树高）

我的理解:定义一个全局的max,所有节点共用,创建递归方法,递归结束条件就是当前节点为null,退出递归,递归的逻辑就是,得到当前节点的左右孩子深度(在递归的返回值就得到左或者右的最大值),然后执行更新全局max,最后返回值就是父节点左孩子或者右孩子中其中一个哪个递归深度最大,就返回谁,并且还有+1,就是当前的父节点也要加上,便这样于更新最大深度max;比如这里的根节点1,递归返回值得到是左孩子的最大深度,因为2 > 1,虽然没什么用,但是在更新全局max时候已经将其左右孩子最大深度相加了  。



#### 区分：👉 “递归返回的是高度” vs “全局变量存的是直径”。



前者是得到父节点左孩子与右孩子的最大深度，后者通过最大深度进行全局max更新， 发生的时机其实是后者先发生

### 1. “后者” —— 更新全局 `max`

- 这是在**当前节点计算出左右子树高度之后**，立刻发生的。
- 因为此时你已经拿到了 `leftHeight` 和 `rightHeight`，就可以用 `leftHeight + rightHeight` 去尝试更新全局直径。
- 所以 **先更新 `max`**。

------

### 2. “前者” —— 返回高度给父节点

- 更新完 `max` 之后，当前节点还要告诉父节点：

  > “我这边的最大高度是多少”

- 这就是 `Math.max(leftHeight, rightHeight) + 1`。

- 父节点拿到这个返回值，才能继续算它自己的直径。