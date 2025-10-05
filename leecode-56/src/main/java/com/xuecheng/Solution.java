package com.xuecheng;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][0];
        // 初始化结果集
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] last = list.get(list.size() - 1);
            int[] cur = intervals[i];
            if (last[1] >= cur[0]) {
                last[1] = Math.max(cur[1], last[1]);
            } else {
                list.add(cur);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
