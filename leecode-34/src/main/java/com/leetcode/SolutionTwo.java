package com.leetcode;

/**
 * SolutionTwo provides an optimized approach using binary search to find the first and last positions of a target element.
 * This method is more efficient than a linear scan, especially for large arrays.
 *
 * @author Winston
 */
public class SolutionTwo {

    /**
     * Searches for the starting and ending positions of a given target value using binary search.
     *
     * @param nums   The sorted array of integers to search within.
     * @param target The integer value to find.
     * @return An array of two integers representing the starting and ending positions of the target.
     *         Returns {@code [-1, -1]} if the target is not found or the input array is null or empty.
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int start = findFirst(nums, target);
        int end = findLast(nums, target);

        return new int[]{start, end};
    }

    /**
     * Finds the first occurrence of the target using a modified binary search.
     *
     * @param nums   The sorted array.
     * @param target The value to find.
     * @return The index of the first occurrence of the target, or -1 if not found.
     */
    private int findFirst(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                // Move right pointer to search in the left half.
                right = mid - 1;
            } else {
                // Move left pointer to search in the right half.
                left = mid + 1;
            }
            // When a match is found, store the index and continue searching to the left.
            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    /**
     * Finds the last occurrence of the target using a modified binary search.
     *
     * @param nums   The sorted array.
     * @param target The value to find.
     * @return The index of the last occurrence of the target, or -1 if not found.
     */
    private int findLast(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                // Move left pointer to search in the right half.
                left = mid + 1;
            } else {
                // Move right pointer to search in the left half.
                right = mid - 1;
            }
            // When a match is found, store the index and continue searching to the right.
            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }
}
