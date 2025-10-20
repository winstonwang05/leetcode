package com.tianji;
// 暴力解法
public class SolutionOne {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];
            // 整体不断右移
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }
}
