# 94.二叉树的中序遍历

## 1.递归解法（简单直观）

```
import java.util.*;

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);   // 遍历左子树
        res.add(root.val);         // 访问根节点
        inorder(root.right, res);  // 遍历右子树
    }
}

```

![image-20250916092613567](C:\Users\Winston\AppData\Roaming\Typora\typora-user-images\image-20250916092613567.png)

## 🌳 树结构

```
        1
       / \
      5   6
       \ / \
        4 7  3
```

------

## 📌 中序遍历规则

**左子树 → 根 → 右子树**

所以最终顺序应该是：

```
5 → 4 → 1 → 7 → 6 → 3
```

------

## 🛠 递归过程详细拆解

方法签名：

```
inorder(TreeNode node, List<Integer> res)
```

- `node` 表示当前节点
- `res` 保存遍历结果

------

### Step 1: 入口

调用：

```
inorder(1, res)
```

此时 `node = 1`，不是 null，所以继续。

------

### Step 2: 进入左子树

```
inorder(1.left, res) → inorder(5, res)
```

------

### Step 3: 遍历 5

执行 `inorder(5.left, res)`
 但 5 的左孩子是 `null`，所以立即返回。

接着：

```
res.add(5) → [5]
```

然后递归右子树：

```
inorder(5.right, res) → inorder(4, res)
```

------

### Step 4: 遍历 4

- `inorder(4.left, res)` → `null` 返回
- `res.add(4)` → [5, 4]
- `inorder(4.right, res)` → `null` 返回

所以 4 遍历完毕，返回给 5，5 也结束。

------

### Step 5: 回到根 1

左子树全部完成，继续执行：

```
res.add(1) → [5, 4, 1]
```

然后右子树：

```
inorder(1.right, res) → inorder(6, res)
```

------

### Step 6: 遍历 6

进入 `inorder(6.left, res)` → `inorder(7, res)`

------

### Step 7: 遍历 7

- 左孩子 null → 返回
- `res.add(7)` → [5, 4, 1, 7]
- 右孩子 null → 返回

7 结束，回到 6。

------

### Step 8: 继续 6

- 左子树结束，`res.add(6)` → [5, 4, 1, 7, 6]
- 再递归右子树 → `inorder(3, res)`

------

### Step 9: 遍历 3

- 左 null → 返回
- `res.add(3)` → [5, 4, 1, 7, 6, 3]
- 右 null → 返回

3 完成，6 完成，1 完成。

------

## ✅ 最终结果

```
[5, 4, 1, 7, 6, 3]
```

------

## 🔑 理解对的地方

- 每个节点都要调用 `inorder(node, res)` 进入方法体。
- **递归就是一层套一层的小方法**，小的返回了，外层方法才会继续往下执行。
- 先“走到底”处理左子树，再一层层返回并添加节点，再处理右子树。

创建数组（leecode应该是默认给了节点数据放进去了的，我们只是提供解决方法solution，把数据按中序遍历后的顺序数据返回），创建方法用来遍历二叉树并按照中序遍历方式，方法的形参是某一个节点，第二个形参用来装中序遍历顺序的节点的。首先递归方式遍历，一旦遍历到某节点为null，那么就结束，我按我画的图来理解：首先传入根节点1，然后先去左孩子， inorder(node.left, res);然后遍历5这个左子节点，然后5的这个方法发现左子节点为null，他的左子方法结束，然后1的方法将5添加到集合中，接着 inorder(node.right, res); 4进入方法，发现左为0直接返回，然后4方法将4添加到集合中，再遍历4方法inorder(node.right, res); ，为null结束，所以5的方法结束了，接着1的方法执行到res.add(node.val);，将1添加进去，接着 inorder(node.right, res);  ，又是相同操作，所以我感觉就是每一个节点通过递归都要进入方法，先是最大的根节点方法，然后递归到期左孩子中节点方法，左孩子中节点也要通过不断递归，只有左孩子完成，最大的1方法 inorder(node.left, res);结束，接着添加元素到集合，接着右孩子，一个像是小层面的方法，1是最大层面方法，只有等小层面方法结束，大的其中方法才结束

# 2.迭代 + 栈方式



![image-20250916100456461](C:\Users\Winston\AppData\Roaming\Typora\typora-user-images\image-20250916100456461.png)

![image-20250916100556464](C:\Users\Winston\AppData\Roaming\Typora\typora-user-images\image-20250916100556464.png)

## ✅ 递归 vs 栈模拟的本质

1. **递归**：
   - 每次调用方法时，JVM 会自动把当前节点（局部变量、返回点等）压入「方法调用栈」。
   - 一旦走到空节点（`null`），递归返回，相当于栈帧弹出，回到上一层。
2. **栈模拟**：
   - 我们自己手写 `Stack<TreeNode>`，把“节点的状态”手动压栈、出栈。
   - 内层 `while(cur != null)` 就相当于**递归往左子树不断深入**。
   - `stack.pop()` 相当于**递归返回上一个调用栈帧**。

------

## 🔄流程（优化表述）

- **外层循环条件**：
  `while (cur != null || !stack.isEmpty())`
  - `cur != null`：说明当前还有没走完的左子树，要继续深入。
  - `!stack.isEmpty()`：说明栈里还有没处理的节点，不能结束。
- **内层循环条件**：
  `while (cur != null)`
  - 只要当前节点不是 `null`，就一直入栈（模拟递归不断往左走）。
  - 一旦 `cur == null`，就停止，说明左子树走到头了，该回头了。
- **执行过程**（用你的树举例）：
  1. `1` 入栈，cur → `5`
  2. `5` 入栈，cur → `4`
  3. `4` 入栈，cur → `null`（走到头）
  4. `4` 出栈 → 加入结果 `[4]`，cur → `4.right(null)`
  5. 回到栈顶 `5`，出栈 → 加入结果 `[4, 5]`，cur → `5.right(null)`
  6. 回到栈顶 `1`，出栈 → 加入结果 `[4, 5, 1]`，cur → `1.right(6)`
  7. 继续走右子树，一直重复…

------

## 🔑 关键理解

- **栈帧** 在递归里是由 JVM 自动管理的；在迭代里我们手动 push/pop 节点，效果一样。
- 所以**递归和栈迭代完全等价**，只是写法不同。
- 先进后出保证了「先走到底，再回头」的访问顺序。

------

⚡ 小修正：

- **内层循环不是“出栈”**，而是**不断压栈直到 null**。
- 出栈的动作只发生在 `cur == null` 后，由外层循环控制。

首先将每一个节点（子节点或者根节点）赋值给每一个栈帧，用来模拟栈，最外层循环条件是每一个栈帧为null说明出栈了，以及整个栈空间，说明都出栈了，也就是empty stack；内层循环条件就是每一个栈帧出栈，只要为null就pop出栈，所以首先1方法栈帧进栈，放到栈底，接着进入1的栈帧里不断得到1的左子节点一直赋值走下去，一旦为null，先进入5左子节点，5方法的栈帧放入栈中，在1栈帧的上面，接着对5不断赋值得到为空，栈帧出栈，并将5放入集合中接着走5的右子节点，由于4栈帧并不为空，以及整个栈都不为空，所以4栈帧进栈，发现左为空，直接出栈将4存入集合中，接着走4的右子树，为空结束，所以4栈帧出栈后，5才会跟着出栈，然后最大层面1栈帧走向右孩子，和递归确实本质一样，只是用栈来模拟递归操作
