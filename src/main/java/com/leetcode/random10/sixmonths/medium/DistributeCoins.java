package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 7/25/20.
 */
public class DistributeCoins {

  private int ans = 0;

  public int distributeCoins(TreeNode root) {
    dfs(root);
    return ans;
  }

  private int[] dfs(TreeNode root) {
    if (root == null) {
      return new int[]{0, 0};
    }

    int[] left = dfs(root.left);

    int[] right = dfs(root.right);
    int numCoinsLeft = left[0], numCoinsRight = right[0];
    int numNodesLeft = left[1], numNodesRight = right[1];

    if (numCoinsLeft == numCoinsRight) {
      if (root.val != 1) {
        ans += root.val - 1;
      }
    }

    return new int[]{numCoinsLeft + numCoinsRight + root.val, numNodesLeft + numNodesRight + 1};
  }

}
