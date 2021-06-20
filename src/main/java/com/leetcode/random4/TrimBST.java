package com.leetcode.random4;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

public class TrimBST {

  public TreeNode trimBST(TreeNode root, int low, int high) {
    return dfs(root, low, high, null, -1);
  }

  private TreeNode dfs(TreeNode root, int low, int high, TreeNode parent, int dir) {
    if (root == null) {
      return null;
    }
    if (root.val < low) {
      if (dir == 0) {
        if (parent != null) {
          parent.left = root.right;
          return dfs(parent, low, high, parent, 1);
        }
      } else if (dir == 1) {
        if (parent != null) {
          parent.right = root.right;
          return dfs(parent, low, high, parent, 1);
        }
      } else if (dir == -1) {
        root.left = null;
        root = root.right;
        return dfs(root, low, high, parent, -1);
      }
    } else if (root.val > high) {
      if (dir == 0) {
        if (parent != null) {
          parent.left = root.left;
          return dfs(parent, low, high, parent, 0);
        }
      } else if (dir == 1) {
        if (parent != null) {
          parent.right = root.left;
          return dfs(parent, low, high, parent, 0);
        }
      } else if (dir == -1) {
        root.right = null;
        root = root.left;
        return dfs(root, low, high, parent, -1);
      }
    }
    dfs(root.left, low, high, root, 0);
    dfs(root.right, low, high, root, 1);
    return root;
  }

  public static void main(String args[]) {
    TreeNode node = new Codec().deserialize("3,2,4,1");
    TrimBST t = new TrimBST();
    System.out.println(new Codec().serialize(t.trimBST(node, 1, 1)));
  }


}
