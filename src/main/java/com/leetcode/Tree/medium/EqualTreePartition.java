package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

public class EqualTreePartition {

  /**
   * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val =
   * val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; } }
   */


  boolean ans = false;

  public boolean checkEqualTree(TreeNode root) {
    int total = dfs(root);
    if (total % 2 != 0) {
      return false;
    }
    dfs(root, total);
    return ans;
  }

  private int dfs(TreeNode root, int total) {
    if (root == null) {
      return 0;
    }
    int left = dfs(root.left, total);
    if (root.left != null) {
      if (total / 2 == left) {
        ans = true;
      }
    }
    int right = dfs(root.right, total);
    if (root.right != null) {
      if (total / 2 == right) {
        ans = true;
      }
    }
    return left + right + root.val;

  }

  private int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = root.val + dfs(root.left);
    int right = root.val + dfs(root.right);

    return left + right - root.val;
  }


  public static void main(String args[]) {
    TreeNode root = new Codec().deserialize("1,-1");
    EqualTreePartition e = new EqualTreePartition();
    System.out.println(e.checkEqualTree(root));
  }

}
