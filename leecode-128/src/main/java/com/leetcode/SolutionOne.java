package com.leetcode;
// 排序法
public class SolutionOne {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int curLen = 1; // 当前
        int maxLen = 1; // 全局
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            } else if (nums[i] == nums[i - 1] + 1) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = 1;
            }
        }
        return Math.max(maxLen, curLen);
    }
}
