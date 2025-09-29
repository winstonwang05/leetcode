package com.xuecheng;
// 动态规划，Kadane算法
public class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int curSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < n; i++) {
            curSum = Math.max(curSum + nums[i], nums[i]); // 状态转移
            maxSum = Math.max(maxSum, curSum); // 更新全局最大
        }
        return maxSum;
    }
}
