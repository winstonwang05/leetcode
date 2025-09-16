package com.itheima.treenode;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归
 */
public class SolutionOne {
    public List<Integer> inorderTraversal(TreeNode root) {
        // new一个数组来存储按中序遍历之后的数据
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode node, List<Integer> res) {
        if (node == null) return; // 某一个节点为null，递归结束
        // 遍历左子节点
        inorder(node.left, res);
        // 访问某一个节点并添加到集合中（根或子）
        res.add(node.val);
        // 遍历右子节点
        inorder(node.right, res);

    }
}
