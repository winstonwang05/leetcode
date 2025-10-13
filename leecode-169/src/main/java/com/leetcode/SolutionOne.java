package com.leetcode;
// 暴力枚举
public class SolutionOne {
    public int majorityElement(int[] nums) {
        // 设置全局最大
        int MaxCount = 0;
        int candidate = nums[0];
        // 遍历最外层
        for (int i = 0; i < nums.length; i++) {
            // 设置当前元素的个数
            int curCount = 1;
            for (int j = i + 1; j < nums.length; j++) {
                // 如果遇到相同则 +1
                if (nums[j] == nums[i]) {
                    curCount++;
                }
            }
            // 遍历结束更新全局最大，并且记录此时最大对应的索引元素
            if (curCount > MaxCount) {
                MaxCount = curCount;
                candidate = nums[i];
            }
        }
        return candidate;
    }
}
