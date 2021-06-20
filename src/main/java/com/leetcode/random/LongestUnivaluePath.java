package com.leetcode.random;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

public class LongestUnivaluePath {

  int max = 1;

  public int longestUnivaluePath(TreeNode root) {
    int val[] = dfs(root);
    return max;
  }

  private int[] dfs(TreeNode root) {
    if (root == null) {
      return new int[]{0, 0};
    }
    int[] left = dfs(root.left);
    int[] right = dfs(root.right);
    int left1, right1;
    if (root.left != null && root.left.val == root.val) {
      left1 = 1 + Math.max(left[0], left[1]);
    } else {
      left1 = 0;
    }
    if (root.right != null && root.right.val == root.val) {
      right1 = 1 + Math.max(right[0], right[1]);
    } else {
      right1 = 0;
    }
    if (root.left != null && root.right != null && root.val == root.left.val && root.val == root.right.val) {
      max = Math.max(max, left1 + right1 + 1);
    }
    max = Math.max(max, Math.max(left1, right1));

    return new int[]{left1, right1};
  }


  public static void main(String args[]) {
    LongestUnivaluePath l = new LongestUnivaluePath();
    TreeNode root = new Codec().deserialize("1");
    System.out.println(l.longestUnivaluePath(root));
  }
}
