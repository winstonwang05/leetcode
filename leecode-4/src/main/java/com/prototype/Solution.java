package com.prototype;


/**
 * @ClassName: Solution
 * @Description: TODO (这里描述这个类的作用)
 * @Author: Winston
 * @Date: 2025/11/9 11:50
 * @Version: 1.0
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int totalLength = m + n;
        int leftK = (totalLength + 1) / 2;
        int rightK = (totalLength + 2) / 2;
        double median1 = findKthElement(nums1, 0, nums2, 0, leftK);
        double median2 = findKthElement(nums2, 0, nums1, 0, rightK);
        return (median1 + median2) / 2;

    }

    private double findKthElement(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) {
            return nums2[j + k - 1];
        }
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        int mid1 = i + k / 2 - 1;
        int mid2 = j + k / 2 - 1;
        int num1 = mid1 < nums1.length ? nums1[mid1] : Integer.MAX_VALUE;
        int num2 = mid2 < nums2.length ? nums2[mid2] : Integer.MAX_VALUE;
        if (num1 < num2) {
            return findKthElement(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKthElement(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
}