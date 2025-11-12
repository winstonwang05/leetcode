package com.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: SolutionOne
 * @Description: BFS
 * @Author: Winston
 * @Date: 2025/11/12 10:19
 * @Version: 1.0
 */
public class SolutionOne {
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> node = new LinkedList<>();

        node.offer(root);

        while (!node.isEmpty()) {
            int size = node.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = node.poll();
                if (i == size - 1) {
                    res.add(cur.val);
                }

                if (cur.left != null) {
                    node.offer(cur.left);
                }
                if (cur.right != null) {
                    node.offer(cur.right);
                }
            }

        }

        return res;

    }
}