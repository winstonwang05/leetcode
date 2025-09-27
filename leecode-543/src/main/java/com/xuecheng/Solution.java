package com.xuecheng;

// 递归
public class Solution {
    // 全局max
    private int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        getMaxDepth(root);
        return maxDiameter;
    }
    private int getMaxDepth(TreeNode node) {
        if (node == null) return 0;
        int leftDepth = getMaxDepth(node.left);
        int rightDepth = getMaxDepth(node.right);
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth); //更新最大
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
