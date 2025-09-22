package com.itheima;

public class Solution {
    public int climbStairs(int n) {
        // 递归结束条件遇到0阶或者1阶
        if (n <= 1) return 1;
        // new数组来存储某一阶的方法数
        int[] memo = new int[n + 1];
        // 初始化0阶和1阶
        memo[0] = 1;
        memo[1] = 1;
        return helper(n, memo);
    }

    private int helper(int n, int[] memo) {
        // 记忆检查
        if (memo[n] != 0) return memo[n];
        // 递推条件
        memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
        return memo[n];
    }
}
