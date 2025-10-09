package com.leetcode;

import java.util.HashSet;
import java.util.Set;

// 哈希表
public class SolutionTwo {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int cur = num;
                int curLen = 1;
                while (set.contains(cur + 1)) {
                    curLen++;
                    cur++;
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }
}
