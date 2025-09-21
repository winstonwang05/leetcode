package com.itheima;

public class SolutionTwo {
    public int searchInsert(int[] nums, int target) {
        // 二分查找
        int left = 0;
        int right = nums.length - 1;
        // 一旦左指针在右指针右边就结束
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left; // 或者right + 1；
    }
}
