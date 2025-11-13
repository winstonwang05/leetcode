package com.leetcode;


/**
 * @ClassName: SolutionTwo
 * @Description: TODO (这里描述这个类的作用)
 * @Author: Winston
 * @Date: 2025/11/13 10:24
 * @Version: 1.0
 */
public class SolutionTwo {

    private TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;



    }
}