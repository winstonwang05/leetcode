package com.tianji;

public class SolutionTwo {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // 先反转整体
        reverse(nums, 0 , n - 1);
        // 反转前k个元素
        reverse(nums, 0, k - 1);
        // 反转后k个元素
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
