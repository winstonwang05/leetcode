package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    String[] mapping= {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };
    List<String> res;
    StringBuilder sb;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        sb = new StringBuilder();
        backTrack(digits, 0);
        return res;
    }

    private void backTrack(String digits, int startIndex) {

        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }

        char charAt = digits.charAt(startIndex);
        String letter = mapping[charAt - '0'];
        for (int i = 0; i < letter.length(); i++) {
            sb.append(letter.charAt(i));
            backTrack(digits, startIndex + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
