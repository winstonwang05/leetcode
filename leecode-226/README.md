# 226.翻转二叉树

## 	方法一：递归

```
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        // 交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 递归处理子树
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}

```

## 方法二：迭代-BFS层序遍历（队列）

```
public TreeNode invertTree(TreeNode root) {
    // 边界情况：如果树为空，直接返回null
    if (root == null) return null;
    
    // 创建队列用于BFS遍历
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);  // 将根节点加入队列
    
    // 开始BFS遍历
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();  // 取出队列头部的节点
        
        // 交换当前节点的左右子树
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        
        // 如果左子树不为空，将其加入队列以待后续处理
        if (node.left != null) queue.offer(node.left);
        // 如果右子树不为空，将其加入队列以待后续处理
        if (node.right != null) queue.offer(node.right);
    }
    
    return root;  // 返回翻转后的树根节点
}
```

## 执行过程示例

以二叉树 `[4,2,7,1,3,6,9]` 为例：

**原始树结构：**

text

```
    4
   / \
  2   7
 / \ / \
1  3 6  9
```



**执行过程：**

1. 处理节点4：交换2和7 → `[4,7,2,1,3,6,9]`
2. 处理节点7：交换6和9 → `[4,7,2,1,3,9,6]`
3. 处理节点2：交换1和3 → `[4,7,2,3,1,9,6]`

**翻转后结果：**

text

```
    4
   / \
  7   2
 / \ / \
9  6 3  1
```



## 算法特点

- **时间复杂度**：O(n)，每个节点只被访问一次
- **空间复杂度**：O(n)，最坏情况下队列需要存储最后一层的所有节点
- **遍历顺序**：按层级从上到下，从左到右

## 方法三：迭代-DFS栈

##  

```
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 交换左右子树
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return root;
    }
}

```

### 通过迭代的方法有一个注意点，每次迭代将当前执行的结点赋值给一个TreeNode node，也就是node，以便之后每次迭代到其他结点就不会混乱

