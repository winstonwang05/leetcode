# 121.买卖股票的最佳时机

### 思路一：贪心（一次遍历）

- 我们只需要在遍历过程中，记录 **历史最低价**。
- 每到一天，计算“如果今天卖出”的利润 = `今天价格 - 历史最低价`。
- 不断更新最大利润。

👉 时间复杂度 O(n)，空间复杂度 O(1)。

------

### 代码（Java）

```
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // 历史最低价
        int maxProfit = 0; // 最大利润
        
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // 更新最低价
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice); // 计算当前利润
            }
        }
        return maxProfit;
    }
}
```

### 贪心思路复述

1. **初始化**
    - 定义 `minPrice = Integer.MAX_VALUE`（相当于一个无穷大），表示历史最低价。
    - 定义 `maxProfit = 0`，表示最大利润。
2. **遍历数组**
    - 对于每一个价格 `price`：
        - **更新最小价格**：如果 `price < minPrice`，说明找到了更低的买入点，就更新 `minPrice`。
        - **计算利润**：如果 `price >= minPrice`，就计算 `price - minPrice` 的利润，并和 `maxProfit` 比较，取更大值。
3. **返回结果**
    - 遍历结束后，`maxProfit` 就是能获取的最大收益。

------

### 为什么 `minPrice` 要初始化为 `Integer.MAX_VALUE`？

- 因为我们要在第一次遍历时就能让 `minPrice` 被覆盖。
- `Integer.MAX_VALUE` 表示一个极大值，任何真实的股票价格一定小于它。
- 这样一来，第一次遍历的价格就一定会替换 `minPrice`，保证逻辑正确。

------

### 举个例子

假设 `prices = [7, 1, 5, 3, 6, 4]`

- 初始：`minPrice = ∞`, `maxProfit = 0`
- 第一天：价格 7 < ∞ → `minPrice = 7`
- 第二天：价格 1 < 7 → `minPrice = 1`
- 第三天：价格 5 - 1 = 4 → `maxProfit = 4`
- 第四天：价格 3 - 1 = 2 → `maxProfit = 4`（不更新）
- 第五天：价格 6 - 1 = 5 → `maxProfit = 5`
- 第六天：价格 4 - 1 = 3 → `maxProfit = 5`

最终结果：最大利润 = **5**（第 2 天买，第 5 天卖）。

首先定义一个变量表示最小值，每一次遍历的数都会和比较，然后定义最大利润初始化为0，贪心算法是不断在局部中收益不断更新（也就是遍历的过程）所定义的最大利润，初始化最小价格为最大值，当前遍历得到的数如果比minPrice要小，需要覆盖，找到最佳买入点，也就是买的价格越低越好，通过遍历不断得到最小价格，不覆盖就会出现亏损状态，只有比minPrice大通过当前price减去得到利润然后与maxProfit比较取最大值（贪心不断更新优化得到max），遍历结束就返回利润；所以说第一个遍历的数一定要比初始化的MAX_VA0LUE小，覆盖得到最小price，因此minprice初始化为最大值，从极限角度看，是一个无限大的数，第一次遍历的数永远需要比他小，必须把第一次遍历的将它覆盖

- ✅ **`Integer.MAX_VALUE` 确实可以看作是"无限大"**
- ✅ **确保第一次比较必然成功，完成初始化**
- ✅ **这是一种防御性编程，处理各种边界情况**

