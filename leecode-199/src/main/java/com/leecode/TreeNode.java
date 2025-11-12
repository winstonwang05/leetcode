package com.leecode;


/**
 * @ClassName: TreeNode
 * @Description: TODO (这里描述这个类的作用)
 * @Author: Winston
 * @Date: 2025/11/12 10:21
 * @Version: 1.0
 */


public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
