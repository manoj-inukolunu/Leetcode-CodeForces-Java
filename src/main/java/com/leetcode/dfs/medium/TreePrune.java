package com.leetcode.dfs.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 2020-01-18.
 */
public class TreePrune {

  private TreeNode node = null;

  public TreeNode pruneTree(TreeNode root) {
    containsOne(root);
    return root;
  }

  private boolean containsOne(TreeNode root) {
    if (root == null) {
      return false;
    }

    if (root.left == null && root.right == null) {
      if (root.val == 1) {
        return true;
      } else {
        return false;
      }
    }
    boolean left = containsOne(root.left);
    if (!left) {
      root.left = null;
    }

    boolean right = containsOne(root.right);
    if (!right) {
      root.right = null;
    }
    return left || right;

  }

  public static void main(String args[]) {
    TreePrune treePrune = new TreePrune();
    treePrune.pruneTree(new Codec().deserialize("1,null,0,0,1"));
  }

}
