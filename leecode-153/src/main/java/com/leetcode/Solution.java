package com.leetcode;


/**
 * LeetCode 153: 寻找旋转排序数组中的最小值
 * <p>
 * 假设一个升序排序的数组在一个未知的枢轴上进行了旋转。
 * (例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2])。
 * 你的任务是找出数组中的最小元素。
 * 你可以假设数组中不存在重复元素。
 */
public class Solution {
    /**
     * 使用二分查找在旋转排序数组中寻找最小值。
     * <p>
     * 核心思想是利用旋转数组的特性。数组被旋转后，会形成两个有序的子数组。
     * 最小值是这两个子数组的交界点。
     * 通过比较中间元素 `nums[mid]` 和右边界元素 `nums[right]` 的大小，
     * 我们可以判断最小值位于哪个区间，从而缩小搜索范围。
     *
     * @param nums 一个经过旋转的、不包含重复元素的升序数组。
     * @return 数组中的最小元素。
     */
    public int findMin(int[] nums) {
        // 初始化左右指针
        int left = 0;
        int right = nums.length - 1;

        // 当 left == right 时，循环结束，找到了最小值
        while (left < right) {
            // 计算中间位置，使用 (right - left) / 2 防止整数溢出
            int mid = left + (right - left) / 2;

            // 将中间值与最右边的值进行比较
            if (nums[mid] > nums[right]) {
                // 如果 mid 的值大于 right 的值，说明最小值在 mid 的右侧（不包括 mid）
                // 例如：[4, 5, 6, 7, 0, 1, 2]，mid 在 7，right 在 2。最小值在 [mid+1, right] 区间。
                left = mid + 1;
            } else {
                // 如果 mid 的值小于等于 right 的值，说明最小值在 mid 的左侧，或者 mid 本身就是最小值
                // 例如：[7, 0, 1, 2, 4, 5, 6]，mid 在 2，right 在 6。最小值在 [left, mid] 区间。
                // 例如：[2, 4, 5, 6, 7, 0, 1]，mid 在 6，right 在 1。此时 mid > right，进入上一个 if 分支。
                right = mid;
            }
        }
        // 循环结束时，left 和 right 指向同一个元素，即为数组的最小值
        return nums[left];
    }
}