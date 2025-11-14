package com.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Solution
 * @Description: TODO (这里描述这个类的作用)
 * @Author: Winston
 * @Date: 2025/11/14 11:13
 * @Version: 1.0
 */
public class Solution {
    private Map<Integer, Integer> inorderMap;
    private int preIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        inorderMap = new HashMap<Integer, Integer>();
        preIndex = 0;

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIndex++]);
        if (startIndex == endIndex) {
            return root;
        }
        int rootIndex = inorderMap.get(root.val);
        root.left = build(preorder, startIndex, rootIndex - 1);
        root.right = build(preorder, rootIndex + 1, endIndex);
        return root;

    }
}