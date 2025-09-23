package com.itheima;

import java.util.LinkedList;
import java.util.Queue;

// BFS层序遍历
public class SolutionTwo {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        // 队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 进队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
