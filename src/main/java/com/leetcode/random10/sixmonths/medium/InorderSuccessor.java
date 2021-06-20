package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 8/5/20.
 */
public class InorderSuccessor {

  TreeNode successor;

  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    dfs(root, null, p);
    return successor;
  }

  private void dfs(TreeNode root, TreeNode parent, TreeNode p) {
    if (root == null || p == null) {
      return;
    }
    if (p.val < root.val) {
      dfs(root.left, root, p);
    } else if (p.val > root.val) {
      dfs(root.right, root, p);
    } else {
      if (root.right == null) {
        successor = parent;
      } else {
        while (root.left != null) {
          root = root.left;
        }
        successor = root;
      }
    }
  }

}

