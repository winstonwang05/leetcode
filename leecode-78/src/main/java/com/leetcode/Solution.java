package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        backTrack(nums, 0);
        return res;
    }

    private void backTrack(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backTrack(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
