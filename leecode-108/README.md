# 108.将有序数组转化为二叉搜索树



## 递归

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    
    /**
     * 主函数，调用递归辅助函数
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // 处理边界情况
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        // 调用辅助函数，传入数组和完整的索引范围
        return buildTree(nums, 0, nums.length - 1);
    }

    /**
     * 递归辅助函数
     * @param nums  完整的有序数组
     * @param left  当前子数组的左边界（包含）
     * @param right 当前子数组的右边界（包含）
     * @return      构建好的子树的根节点
     */
    private TreeNode buildTree(int[] nums, int left, int right) {
        // 1. 递归终止条件
        // 当左指针 > 右指针时，说明这个范围的子数组为空，返回 null
        if (left > right) {
            return null;
        }

        // 2. 找到中间点
        // 这是 Java 中防止整数溢出的标准写法
        int mid = left + (right - left) / 2;

        // 3. 创建根节点
        // 用中间点的值创建根节点
        TreeNode root = new TreeNode(nums[mid]);

        // 4. 递归构建左右子树
        // 递归构建左子树，范围是 [left, mid - 1]
        root.left = buildTree(nums, left, mid - 1);
        
        // 递归构建右子树，范围是 [mid + 1, right]
        root.right = buildTree(nums, mid + 1, right);

        // 5. 返回当前构建好的根节点
        return root;
    }
}
```

**1. 核心目标与策略**

- **目标：** 构建一棵**高度平衡**的二叉搜索树（BST）。
- **核心：** “平衡”是第一要务。
- **策略：** 采用**分治法**。

**2. 分治法的“分”（Divide）**

- **如何“分”：** 选取有序数组的**中间索引位置**的元素作为当前子树的根节点。
- **结果：** 数组被一分为二。根节点左侧的元素（都更小）用于构建左子树，右侧的元素（都更大）用于构建右子树。

**3. 实现细节（面试关键点）**

- **① 健壮性（空指针异常）：**
    - 在主函数中，必须**先检查 `nums == null`**，**然后再检查 `nums.length == 0`**。
    - 这个顺序是强制性的，否则会在 `nums` 为 `null` 时触发 `NullPointerException`。
- **② 递归终止条件（Base Case）：**
    - 在递归辅助函数中，终止条件是 `left > right`。
    - 这表示当前分配的区间为空，没有元素可构建，因此返回 `null`。
- **③ 中点计算（整数溢出）：**
    - 找中点 `mid` 时，不使用 `(left + right) / 2`。
    - 而是使用 `int mid = left + (right - left) / 2;`
    - 这是为了防止 `left` 和 `right` 都非常大时，`left + right` 的结果超过 `Integer.MAX_VALUE` 导致**整数溢出**。
- **④ 递归构建（Conquer）：**
    - **左子树：** 递归调用 `buildTree(nums, left, mid - 1)`。区间是 `[left, mid - 1]`。
    - **右子树：** 递归调用 `buildTree(nums, mid + 1, right)`。区间是 `[mid + 1, right]`。
    - **返回：** 返回当前创建的 `root` 节点。

**4. 平衡性保证（The "Why"）**

- **奇数长度：** 左右子树的节点数完美平分。
- **偶数长度：**
    - 你使用的 `left + (right - left) / 2` 公式会取到**“左中点”**。
    - 这会导致右子树的节点数可能比左子树多 1 个。
    - **结论：** 无论选择左中点还是右中点，两边子树的节点数之差最多为 1。这个平衡性在**每一层递归**中都得以保持，因此最终整棵树的高度差绝对不会超过 1，满足“高度平衡”的定义

首先题目的要求就是将数组转化成BST二叉搜索树，要求是的重点就是平衡，因此此题 的核心就是平衡，我们该如何保证平衡呢？  因此我们可以将数组中处于中间索引位置的元素作为根节点，以这个点作为分治，左右分开，左边就用来表示当前递归到节点的左子树的结构，右边同理；实现：首先需要代码的健壮性判断，也就是如果数组为空或者长度为 0直接返回一个空树，注意：一定要先判断是否为空再检查长度为=，避免空指针异常，然后创建一个方法用来将数组生成二叉搜索树，递归的结束的条件就是如果递归到的左节点大于右节点，说明左右分治的区间已经递归完毕了，所以直接返回空节点就行，然后就是找中间节点，这里有一个细节就是，如果两个数足够大时候可能会超过Integer的最大返回，就会出现整数溢出问题，所以需要left + （right - left） /2能够避免这种情况发生，不可能超过Integer最大值；然后就是递归方法，构建左子树，我们取的是闭区间，所以传入的区间范围就是[left, mid - 1];同理 构建右子树的区间就是[mid + 1, right]；最后返回头结点，通过递归会最终返回头结点。这里保证平衡就是通过分治，一次分治之后，左右两边通过递归继续分治保证左右数量差不多，不会失衡，但是如果是偶数个数组元素，我们该如何分治呢？这里其实分治左还是右都是一样的都能保证平衡，如果分治左，那么构建节点就会在其右边，如果分治右，那么构建节点就在左边