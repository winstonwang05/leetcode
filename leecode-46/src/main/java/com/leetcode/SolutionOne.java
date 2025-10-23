package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SolutionOne {

    // 初始化结果集
    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();

        backTrack(nums, 0);

        return res;
    }

    /**
     * 回溯
     * @param nums 数组
     * @param startIndex 当前固定到的索引
     */
    private void backTrack(int[] nums, int startIndex) {

        if (startIndex == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        // 遍历固定第一个索引
        for (int i = startIndex; i < nums.length; i++) {
            swap(nums, startIndex, i);

            // 递归固定下一个索引
            backTrack(nums, startIndex + 1);

            // 回溯 撤销
            swap(nums, startIndex, i);
        }
    }

    /**
     * 交换 元素下的值
     * @param nums 数组
     * @param startIndex 被置换的元素
     * @param i 被置换成的元素
     */
    private void swap(int[] nums, int startIndex, int i) {
        int temp = nums[startIndex];
        nums[startIndex] = nums[i];
        nums[i] = temp;
    }


}
