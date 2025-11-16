package com.leetcode;

/**
 * @ClassName: Solution
 * @Description: 寻找二叉树中两个指定节点的最近公共祖先
 * @Author: Winston
 * @Date: 2025/11/16 10:27
 * @Version: 1.0
 */
public class Solution {

    /**
     * 寻找二叉树中两个指定节点的最近公共祖先
     *
     * @param root 二叉树的根节点
     * @param p    第一个指定节点
     * @param q    第二个指定节点
     * @return 两个指定节点的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果根节点为空，或者根节点就是 p 或 q 中的一个，那么根节点就是最近公共祖先
        if (root == null || root == p || root == q) {
            return root;
        }

        // 在左子树中递归寻找 p 和 q 的最近公共祖先
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        // 在右子树中递归寻找 p 和 q 的最近公共祖先
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

        // 如果左子树和右子树都找到了结果，说明 p 和 q 分别在当前节点的两侧，
        // 那么当前节点就是它们的最近公共祖先
        if (leftResult != null && rightResult != null) {
            return root;
        }

        // 如果只有左子树找到了结果，说明 p 和 q 都在左子树中，
        // 那么左子树的查找结果就是它们的最近公共祖先
        // 如果只有右子树找到了结果，说明 p 和 q 都在右子树中，
        // 那么右子树的查找结果就是它们的最近公共祖先
        return leftResult != null ? leftResult : rightResult;
    }
}