package com.leetcode;
// 投票算法
public class SolutionThree {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = nums[0];
        for (int num : nums) {
            if (count == 0) {
                // 如果上一个遍历元素抵消计数为0，更新候选人
                candidate = num;
            }
            count += (num == candidate ? 1 : -1);
        }
        return candidate;
    }
}
