package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        path = new ArrayList<>();

        backTrack(candidates, target, 0);

        return res;
    }
    private void backTrack(int[] candidates, int target, int startIndex) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        };
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);

            backTrack(candidates, target - candidates[i], i);

            path.remove(path.size() - 1);
        }
    }

}
