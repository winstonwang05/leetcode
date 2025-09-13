package com.itheima;

public class Solution {
    public void moveZeroes(int[] nums) {
        // 指向下一个指针
        int next = 0;
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 只有i遍历的数值不为0才遍历
            if (nums[i] != 0) {
                nums[next] = nums[i];
                // 如果下一个指针和i指针不同，说明遍历到0了
                if (i != next) {
                    nums[i] = 0;
                }
                next++;
            }
        }
    }
}
