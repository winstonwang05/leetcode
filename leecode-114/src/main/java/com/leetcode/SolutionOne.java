package com.leetcode;


/**
 * @ClassName: SolutionOne
 * @Description: TODO (这里描述这个类的作用)
 * @Author: Winston
 * @Date: 2025/11/13 10:22
 * @Version: 1.0
 */
public class SolutionOne {
    public void flatten(TreeNode node) {
        if (node == null) {
            return;
        }

        flatten(node.left);
        flatten(node.right);

        TreeNode tempRight = node.right;

        node.right = node.left;
        node.left = null;

        while (node.right != null) {
            node = node.right;
        }

        node.right = tempRight;
    }
}