package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

public class UnivalueSubTrees {

  int count = 0;

  public int countUnivalSubtrees(TreeNode root) {
    isUnivalue(root);
    return count;
  }

  private boolean isUnivalue(TreeNode node) {
    if (node == null) {
      return true;
    }
    if (node.left == null && node.right == null) {
      count++;
      return true;
    }
    if (node.left == null) {
      if (isUnivalue(node.right) && node.val == node.right.val) {
        count++;
        return true;
      }
    } else if (node.right == null) {
      if (isUnivalue(node.left) && node.val == node.left.val) {
        count++;
        return true;
      }
    } else {
      boolean left = isUnivalue(node.left);
      boolean right = isUnivalue(node.right);
      if (left && right && node.val == node.left.val && node.val == node.right.val) {
        count++;
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    TreeNode node = new Codec().deserialize("1,null,2");
    UnivalueSubTrees u = new UnivalueSubTrees();

    System.out.println(u.countUnivalSubtrees(node));
  }

}
