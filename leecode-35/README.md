# 35.搜索插入的位置

## 解法 1：暴力遍历

一个一个遍历，最坏的情况就是在最右边，遍历n次，时间复杂度O（n）

```
class Solution {
    public int searchInsert(int[] nums, int target) {
       for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
```

没找到就插入到数组最后一个位置

### 解法 2：二分查找（推荐）

### 🧩 代码实现

```
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // 防止溢出
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left; // 插入位置
    }
}
<img width="565" height="1682" alt="二分查找" src="https://github.com/user-attachments/assets/fd2e387a-5ac2-4f67-b889-56a44b92cb80" />


```

### 🎯 理解总结

1. **核心思想**：不断缩小范围。
   - 用两个指针：`left` 和 `right` 表示当前搜索区间。
   - 每次取中点 `mid = (left + right) / 2`。
2. **判断逻辑**：
   - `nums[mid] == target` → 直接返回 `mid`。
   - `nums[mid] > target` → 说明目标在左边，所以 `right = mid - 1`。
     👉 注意这里是 `mid - 1` 而不是 `mid`，因为 `mid` 已经比较过，不等于目标，就没必要再包含。
   - `nums[mid] < target` → 说明目标在右边，所以 `left = mid + 1`。
     👉 同理，`mid` 已经比较过，不等于目标，就不用再包含。
3. **结束条件**：当 `left > right` 时，循环结束。
   - 如果没找到目标，`left` 就是应该插入的位置。

------

## 🔑 为什么是 `mid - 1` / `mid + 1`

关键点：

> 为何不直接赋值为 middle 位置？

因为：

- 当 `nums[mid] > target` 时，我们已经知道 `mid` 位置的值大于目标，所以目标一定不在 `mid`，必须丢掉它 → `right = mid - 1`。
- 当 `nums[mid] < target` 时，我们已经知道 `mid` 位置的值小于目标，所以目标一定不在 `mid`，必须丢掉它 → `left = mid + 1`。

这就是二分查找能“不断缩小范围”的根本原因。

------

## 📌 一个完整示例推演

数组：`[1, 3, 4, 6, 7, 9]`，目标：`5`

1. 初始：`left = 0, right = 5`
   mid = (0+5)/2 = 2 → `nums[2] = 4 < 5`
   → left = 3
2. 第二次：`left = 3, right = 5`
   mid = (3+5)/2 = 4 → `nums[4] = 7 > 5`
   → right = 3
3. 第三次：`left = 3, right = 3`
   mid = 3 → `nums[3] = 6 > 5`
   → right = 2
4. 此时：`left = 3, right = 2` → 循环结束

👉 答案是 `left = 3`，说明目标应该插入下标 3 的位置。

------

## ✅ 结论

- 关键点就是 **排除掉 mid 本身**（因为 mid 已经检查过，不等于目标）。
- 最终返回 `left` 就能保证正确性：
  - 如果找到了，直接返回 `mid`；
  - 如果没找到，`left` 就是插入位置。

首先二分查找就是不断缩小范围的，通过左指针和右指针之和除以2得到middle索引位置，通过middle索引位置和目标数比较，如果是比目标数大，说明在左边，所以缩小范围，将right赋值为middle减一的位置，为何不直接赋值为middle位置，因为我们还有判断如果middle等于我们的目标数，那么就直接返回该middle索引，所以如果是比目标树大，那么right变为middle -1索引位置，比目标数小，需要将left 赋值为middle + 1位置

注意：在得到middle时候需防止整数溢出问题

✅ 结论

- `(left + right) / 2` **可能溢出**
- `left + (right - left) / 2` **不会溢出**，因为 `(right - left)` 永远在数组范围内
- 所以推荐使用 `left + (right - left) / 2`

