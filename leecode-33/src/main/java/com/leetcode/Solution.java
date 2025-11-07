package com.leetcode;

/**
 * This class provides a solution for searching a target value in a rotated sorted array.
 * The implementation uses a modified binary search algorithm to achieve a time complexity of O(log n).
 *
 * @author Winston
 */
public class Solution {

    /**
     * Searches for a target value in a rotated sorted array.
     *
     * @param nums   The rotated sorted array (e.g., {4, 5, 6, 7, 0, 1, 2}).
     * @param target The integer value to search for.
     * @return The index of the target if it is found; otherwise, returns -1.
     */
    public int search(int[] nums, int target) {
        // Handle edge case of a null or empty array.
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If the middle element is the target, we've found it.
            if (nums[mid] == target) {
                return mid;
            }

            // Check if the left half of the array (from left to mid) is sorted.
            if (nums[left] <= nums[mid]) {
                // If the left half is sorted, check if the target is within this range.
                if (target >= nums[left] && target < nums[mid]) {
                    // If the target is in the left half, narrow the search to that half.
                    right = mid - 1;
                } else {
                    // Otherwise, the target must be in the right half.
                    left = mid + 1;
                }
            } else {
                // If the left half is not sorted, then the right half (from mid to right) must be sorted.
                // Check if the target is within the range of the sorted right half.
                if (target > nums[mid] && target <= nums[right]) {
                    // If the target is in the right half, narrow the search to that half.
                    left = mid + 1;
                } else {
                    // Otherwise, the target must be in the left half.
                    right = mid - 1;
                }
            }
        }

        // If the loop completes without finding the target, it's not in the array.
        return -1;
    }
}
