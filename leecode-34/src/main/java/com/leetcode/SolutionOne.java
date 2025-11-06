package com.leetcode;

/**
 * SolutionOne provides a straightforward approach to finding the first and last positions of a target element in a sorted array.
 * It iterates through the array to find the start and end indices.
 *
 * @author Winston
 */
public class SolutionOne {

    /**
     * Searches for the starting and ending positions of a given target value.
     *
     * @param nums   The sorted array of integers to search within.
     * @param target The integer value to find.
     * @return An array of two integers representing the starting and ending positions of the target.
     *         Returns {@code [-1, -1]} if the target is not found or the input array is null or empty.
     */
    public int[] searchRange(int[] nums, int target) {
        // Check for null or empty array.
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int start = -1;
        int end = -1;

        // Find the first occurrence of the target.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                start = i;
                break; // Exit loop once the first occurrence is found.
            }
        }

        // Find the last occurrence of the target by searching from the end.
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] == target) {
                end = j;
                break; // Exit loop once the last occurrence is found.
            }
        }

        return new int[]{start, end};
    }
}
