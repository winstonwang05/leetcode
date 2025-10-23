package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SolutionTwo {

    List<List<Integer>> res;
    List<Integer> path;
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        used = new boolean[nums.length];
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backTrack(nums);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
