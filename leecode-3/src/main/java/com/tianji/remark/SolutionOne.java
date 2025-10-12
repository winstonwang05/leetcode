package com.tianji.remark;

import java.util.HashSet;
import java.util.Set;

// 滑动窗口
public class SolutionOne {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<Character>();
        int start = 0;
        int end = 0;
        int maxLength = 0;
        while (end < s.length()) {
            char charAt = s.charAt(end);
            // 如果遇到相同元素就移动左指针
            while (window.contains(charAt)) {
                window.remove(s.charAt(start));
                start++;
            }
            window.add(charAt);
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }
}
