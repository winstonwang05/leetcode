package com.tianji;

public class Solution {
    public int jump(int[] nums) {
        int steps = 0;
        int end = 0;
        int maxPos = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            // 到达边界必须跳一次
            if (i == end) {
                steps++;
                end = maxPos;
            }
        }
        return steps;
    }
}
