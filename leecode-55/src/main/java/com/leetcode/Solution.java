package com.leetcode;
// 贪心
public class Solution {
    public boolean canJump(int[] nums) {
        // 初始化最远
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前遍历位置大于最远，返回false
            if (i > maxReach) return false;
            // 在可达范围内更新最远
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) return true;
        }
        return true;
    }
}
