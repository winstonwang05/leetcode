package com.leetcode;

import java.util.HashMap;
import java.util.Map;

// 哈希表存储
public class SolutionTwo {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        // 候选人
        int candidate = 0;
        int n = nums.length;
        for (int nm : nums) {
            map.put(nm, map.getOrDefault(nm, 0) + 1);
            // 方式一如果超过总元素一半
            if (map.get(nm) > n / 2) {
                return nm;
            }
            // 方式二通过遍历Map来找出现最多的
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    candidate = entry.getKey();
                }
            }
            return  candidate;
        }
        // 理论上不会实现
        return -1;

    }
}
