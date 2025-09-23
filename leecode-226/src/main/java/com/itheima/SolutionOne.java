package com.itheima;
// 递归处理
public class SolutionOne {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        // 左右交换
        TreeNode temp = root.left;
        root.left  = root.right;
        root.right = temp;
        // 递归
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
