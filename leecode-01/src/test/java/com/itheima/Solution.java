package com.itheima;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 然后再遍历内层数组
            for (int j = i + 1; j < nums.length; j++) {
                // 如果满足条件的就返回
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        // 不满足条件的就返回一个随机，不太可能出现
        return new int[0];
    }
}
