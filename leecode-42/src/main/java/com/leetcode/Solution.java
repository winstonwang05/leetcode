package com.leetcode;


/**
 * @ClassName: Solution
 * @Description: TODO (这里描述这个类的作用)
 * @Author: Winston
 * @Date: 2025/11/11 11:56
 * @Version: 1.0
 */
public class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0) {return 0;}
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    res += leftMax - height[left];
                }
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += rightMax - height[right];

                }
            }
        }
        return res;


    }
}