package com.itheima;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // new一个哈希表，键是数组的值，值是数组的索引（拿来返回满足条件的）
        Map <Integer, Integer> map = new HashMap<>();
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 得到需要的数字
            int need = target - nums[i];
            // 如果哈希表中存在就直接返回
            if (map.containsKey(need)) {
                return new int[]{map.get(need), i};
            }
            // 没有就存储
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
