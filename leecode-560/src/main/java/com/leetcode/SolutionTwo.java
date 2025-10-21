package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class SolutionTwo {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            preSum += num;
            if (map.containsKey(preSum - k)){
                count += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
