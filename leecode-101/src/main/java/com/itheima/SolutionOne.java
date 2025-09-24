package com.itheima;
// 递归方式-镜像
public class SolutionOne {
    public boolean isSymmetric(TreeNode root) {
        // 如果根节点为null，直接返回true
        if (root == null) return true; // 空树是对称的
        return isMirror(root.left, root.right);
    }
    // 递归镜像方法
    public boolean isMirror(TreeNode p, TreeNode q) {
        // 根节点的左右孩子结点都为null
        if (p == null && q == null) return true;
        // 其中一个为null
        if (p == null || q == null) return false;
        // 如果对应的值不相等
        if (p.val != q.val) return false;
        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }
}
