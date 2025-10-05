# 56.合并区间

```
import java.util.*;

public class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0];
        
        // 1. 按起点排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        
        // 2. 遍历
        for (int i = 1; i < intervals.length; i++) {
            int[] last = res.get(res.size() - 1);
            int[] curr = intervals[i];
            
            if (curr[0] <= last[1]) { // 有重叠
                last[1] = Math.max(last[1], curr[1]);
            } else {
                res.add(curr);
            }
        }
        
        return res.toArray(new int[res.size()][]);
    }
}

```



### ① 判断特殊情况

在正式处理之前，先判断输入的二维数组 `intervals` 是否为空：

```
if (intervals.length == 0) return new int[0][0];
```

如果为空，说明没有任何区间可合并，直接返回一个空的二维数组。

------

### ② 按区间起点排序

接着，我们需要先把所有区间按起点（即每个子数组的第 0 个元素）进行升序排序：

```
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
```

这里的 `(a, b) -> a[0] - b[0]` 表示按照每个区间的起点进行比较。
排序的目的是：**让所有区间按从左到右的顺序排列，方便后面顺序判断是否重叠。**

**`a` 和 `b` 是二维数组中的两个一维数组元素，然后我们比较这两个一维数组的索引0位置的元素。**

------

### ③ 初始化结果集

然后，我们创建一个结果集合 `res`，并**先将第一个区间加入**到结果中作为初始参考：

```
List<int[]> res = new ArrayList<>();
res.add(intervals[0]);
```

------

### ④ 遍历剩余区间并合并

从第二个区间（索引 `1`）开始遍历：

```
for (int i = 1; i < intervals.length; i++) {
    int[] last = res.get(res.size() - 1); // 结果集中的最后一个区间
    int[] curr = intervals[i];            // 当前遍历的区间
```

然后分两种情况讨论：

#### ✅ 情况 1：重叠

如果当前区间的起点 `curr[0] <= last[1]`，
说明当前区间与上一个区间存在重叠，此时需要**合并区间**：

```
last[1] = Math.max(last[1], curr[1]);
```

这里的含义是：
区间右边界要取两者中较大的那个，保证能覆盖所有被重叠的部分。

#### ❌ 情况 2：不重叠

如果当前区间的起点 `curr[0] > last[1]`，
说明没有重叠关系，直接把当前区间加入结果集即可：

```
res.add(curr);
```

------

### ⑤ 转换结果并返回

遍历结束后，将 `List<int[]>` 转换成 `int[][]` 形式返回：

```
return res.toArray(new int[res.size()][]);
```

------

## ✅ 总结逻辑流程

| 步骤 | 操作               | 目的                 |
| ---- | ------------------ | -------------------- |
| 1️⃣    | 判空               | 避免异常             |
| 2️⃣    | 按起点排序         | 保证区间从左到右有序 |
| 3️⃣    | 初始化结果集       | 先加入第一个区间     |
| 4️⃣    | 遍历并判断是否重叠 | 合并或新增区间       |
| 5️⃣    | 转换为二维数组返回 | 得到最终结果         |

------

## 🧩 小结

- 这题的核心在于“排序 + 顺序遍历 + 判断重叠”。
- `Math.max()` 用于更新右边界，是区间合并的关键。
- 时间复杂度：O(n log n)，主要来自排序。