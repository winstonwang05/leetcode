package com.itheima;

import java.util.ArrayDeque;
import java.util.Deque;

// 迭代-队列
public class SolutionTwo {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        // new 一个双端队列
        Deque<TreeNode> dq = new ArrayDeque<>();
        // 入队
        dq.addLast(root.right);
        dq.addLast(root.left);
        while (!dq.isEmpty()) {
            TreeNode left = dq.removeFirst();
            TreeNode right = dq.removeFirst();
            if (left.val != right.val) return false;
            if (left.left == null && right.right == null) continue;
            if (left.left == null || right.right == null) return false;
            dq.addLast(left.left);
            dq.addLast(right.right);
            dq.addLast(left.right);
            dq.addLast(right.left);
        }
        return true;
    }
}
