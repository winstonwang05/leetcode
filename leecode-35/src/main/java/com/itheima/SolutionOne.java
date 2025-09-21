package com.itheima;

public class SolutionOne {
    public int searchInsert(int[] nums, int target) {
        // 暴力解法（遍历）
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        // 不存在，放在数组最后面
        return nums.length;
    }
}
