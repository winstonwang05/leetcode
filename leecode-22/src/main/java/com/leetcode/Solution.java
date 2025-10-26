package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backTrace(sb, 0, 0, n);
        return res;
    }

    private void backTrace(StringBuilder path, int left, int right, int n) {
        if (path.length() == n * 2) {
            res.add(path.toString());
            return;
        }

        // 左括号生成
        if (left < n) {
            path.append("(");
            backTrace(path, left + 1, right, n);
            path.deleteCharAt(path.length() - 1);
        }
        // 右括号生成
        if (right < left) {
            path.append(")");
            backTrace(path, left, right + 1, n);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
