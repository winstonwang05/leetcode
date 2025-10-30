package com.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (String word : words) {
                int length = word.length();
                if (i >= length) {
                    if (dp[i - length] && s.substring(i - length, i).equals(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[n];

    }
}
