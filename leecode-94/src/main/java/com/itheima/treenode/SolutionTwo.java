package com.itheima.treenode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 通过栈帧来模拟递归
 */
public class SolutionTwo {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // 整个栈
        Stack<TreeNode> stack = new Stack<>();
        // 将节点赋值为栈帧(模拟)
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                // 进栈
                stack.push(cur);
                // 不断往左走，知道为null
                cur = cur.left;

            }
            // 出栈
             cur = stack.pop();
            // 添加到集合中
            res.add(cur.val);
            // 继续往右
            cur = cur.right;
        }
        return res;
    }
}