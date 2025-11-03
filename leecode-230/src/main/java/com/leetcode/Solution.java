package com.leetcode;

import java.util.Stack;

/**
 * @author Winston
 */
public class Solution {
    /**
     * 寻找二叉搜索树中第 k 小的元素
     *
     * @param root 二叉搜索树的根节点
     * @param k    要寻找的第 k 小的元素
     * @return 第 k 小的元素的值，如果树为空或 k 无效，则返回 -1
     */
    public int kthSmallest (TreeNode root, int k) {
        // 使用迭代方法进行中序遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 将所有左子节点压入栈中
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // 弹出栈顶元素，这是当前最小的元素
            cur = stack.pop();
            k--;

            // 如果 k 减到 0，说明找到了第 k 小的元素
            if (k == 0) {
                return cur.val;
            }

            // 转向右子树
            cur = cur.right;
        }
        // 如果 k 大于树中的节点数，则返回 -1
        return -1;
    }
}
