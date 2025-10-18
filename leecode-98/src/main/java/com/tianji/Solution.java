package com.tianji;

public class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 递归方法
     * @param root 递归到的当前节点
     * @param minValue 最小值
     * @param maxValue 最大值
     * @return 返回是否满足BST
     */
    private boolean helper(TreeNode root, long minValue, long maxValue) {
        if (root == null) return true;
        if (root.val >= minValue || root.val <= maxValue) return false;
        return helper(root.left, minValue, root.val) && helper(root.right, root.val, maxValue);
    }
}
