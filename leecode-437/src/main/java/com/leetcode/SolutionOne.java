package com.leetcode;


/**
 * @ClassName: SolutionOne
 * @Description: TODO (这里描述这个类的作用)
 * @Author: Winston
 * @Date: 2025/11/15 10:15
 * @Version: 1.0
 */
public class SolutionOne {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int pathFromRoot = countPaths(root, targetSum);

        int pathFromLeft = countPaths(root.left, targetSum);

        int pathFromRight = countPaths(root.right, targetSum);

        return pathFromRoot + pathFromLeft + pathFromRight;
    }

    private int countPaths(TreeNode root, long targetSum) {

        if (root == null) {
            return 0;
        }

        int count = 0;

        if (root.val == targetSum) {
            count++;
        }

        count += countPaths(root.left, targetSum - root.val);

        count += countPaths(root.right, targetSum - root.val);

        return count;

    }
}