package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

public class InsufficientNodes {


  public TreeNode sufficientSubset(TreeNode root, int limit) {
    dfs(root, 0, limit);
    return root;
  }

  private boolean dfs(TreeNode root, int sum, int limit) {
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null) {
      return sum + root.val < limit;
    }
    boolean left = dfs(root.left, sum + root.val, limit);
    boolean right = dfs(root.right, sum + root.val, limit);

    if (left && right) {
      root.left = null;
      root.right = null;
      return true;
    }
    if (left && !right) {
      root.left = null;
      return false;
    } else if (!left && right) {
      root.right = null;
      return false;
    } else {
      return false;
    }
  }

  public static void main(String args[]) {
    TreeNode node = new Codec().deserialize("1,2,-3,-5,null,4,null");
    InsufficientNodes i = new InsufficientNodes();

    System.out.println(i.sufficientSubset(node, -1));
  }

}
