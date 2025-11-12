package com.leecode;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SolutionTwo
 * @Description: DFS
 * @Author: Winston
 * @Date: 2025/11/12 10:19
 * @Version: 1.0
 */
public class SolutionTwo {
    List<Integer> res;
    public List<Integer> rightSizeView(TreeNode root) {
        res = new ArrayList<>();

        dfs(root, 0);

        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == res.size()) {
            res.add(root.val);
        }

        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);

    }
}