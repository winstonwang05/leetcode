package com.itheima;

public class SolutionOne {
    public int maxDepth(TreeNode root) {
        // 递归终止条件
        if (root == null) return 0;
        // 递归左子树
        int left = maxDepth(root.left);
        // 递归右子树
        int right = maxDepth(root.right);
        // 将某一层结点递归的结果+1
        return Math.max(left, right) + 1;
    }
}
