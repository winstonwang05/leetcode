# 102.二叉树的层序遍历

## 思路：BFS队列实现广度搜索

```
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 1. 初始化结果列表
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;  // 空树直接返回

        // 2. 创建队列，用于BFS遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);  // 根节点入队

        // 3. 开始层序遍历
        while (!queue.isEmpty()) {
            int size = queue.size(); // 关键：记录当前层的节点个数
            List<Integer> level = new ArrayList<>();  // 存储当前层的节点值

            // 4. 处理当前层的所有节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();  // 出队一个节点
                level.add(node.val);  // 将节点值加入当前层列表

                // 5. 将子节点加入队列（下一层）
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            // 6. 当前层处理完成，加入结果集
            res.add(level);
        }

        return res;
    }
}
```

#### 1. 返回类型理解 ✅

java

```
List<List<Integer>> res = new ArrayList<>();
```

- **最外层List**：存储所有层的结果
- **内层List**：存储每一层节点的val值集合
- **最终结构**：`[[第1层], [第2层], [第3层], ...]`

#### 2. **判空处理**

- 如果根节点 `root == null`，直接返回 `res`。

#### 3. **队列初始化**

- 用 `Queue<TreeNode> queue = new LinkedList<>();` 存储节点；
- 先把根节点入队。

#### 4. **外层 while 循环**

- 条件：队列不为空；
- 作用：保证整个树被逐层遍历。

#### 5. **获取层大小并处理一层**

- 用 `int size = queue.size();` 得到当前层节点数；
- 新建 `List<Integer> level` 存放该层的值；
- 循环 size 次：依次出队节点，把值加入 `level`；
- 如果节点有左右孩子，则入队，等待下一层处理。

#### 6. **存储层结果**

- 内层循环结束后，把 `level` 加入 `res`。

#### 7. **终止条件**

- 当队列为空，所有节点都被遍历，返回 `res`。

总结：

理解返回类型，List集合嵌套，最外层的List集合包裹的是每一层遍历的节点val合集，所以它的泛型就是每一层节点val合集，最里面的List集合，就是包裹的是每一层节点的val实现：首先List<List<Integer>> res = new ArrayList<>(); 用来存储每一层的结果集，然后返回的，接着判断根节点是否为null，以便后续的迭代循环，然后再new一个队列，使用LinkedList，首先先将根节点入队，进入循环，循环的结束条件就是队列为空，表明队列中的元素都出队列了，然后需要一个变量表示队列中的元素个数，有几个元素就代表这一层有几个节点，因为后面需要出队列的，所以每次size就是那一层的节点个数，接着用List<Integer> level = new ArrayList<>();通过循环来存储每一层得到元素val并add到集合中，循环次数就是队列中的元素个数，然后并一一出队，然后还要将这一次的节点左右孩子不为null的进入队列中，最外层的while循环是保证队列进队和出队，里面的循环是得到每一层的节点val，然后里面的循环结束，将每一层的集合放入res 中，一旦队列为空，那么就返回res

###  各组件作用理解 ✅

- **外层while循环**：确保遍历所有层，直到队列为空
- **size变量**：关键！锁定当前层的节点数量，防止处理到下一层的节点
- **内层for循环**：处理当前层的所有节点
- **level列表**：临时存储当前层的节点值
- **队列操作**：
    - `poll()`：处理当前节点
    - `offer()`：准备下一层节点

### 📊 size的动态变化

- **size = 当前层的节点个数**
- **每一轮外层循环开始时重新计算**
- **size决定了内层循环的执行次数**

### 🔄 处理流程中的size变化

text

```
初始：队列 = [3], size = 1
├── 处理节点3 → level = [3]
├── 加入子节点9,20 → 队列 = [9, 20]
└── res = [[3]]

下一轮：队列 = [9, 20], size = 2
├── 处理节点9 → level = [9]
├── 处理节点20 → level = [9, 20]  
├── 加入子节点15,7 → 队列 = [15, 7]
└── res = [[3], [9,20]]

下一轮：队列 = [15, 7], size = 2
├── 处理节点15,7 → level = [15, 7]
└── res = [[3], [9,20], [15,7]]
```