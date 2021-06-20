package com.leetcode.random5;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

public class FlipEqBinTree {

  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 != null || (root1 != null && root2 == null)) {
      return false;
    }
    if (root1 == null && root2 == null) {
      return true;
    }
    if (root1.val != root2.val) {
      return false;
    }
    TreeNode left1 = root1.left;
    TreeNode right1 = root1.right;
    TreeNode left2 = root2.left;
    TreeNode right2 = root2.right;
    if (equalsNode(left1, left2) && equalsNode(right1, right2)) {
      return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    }

    if (left1 == null && right1 != null && left2 != null && right2 == null && right1.val == left2.val) {
      swap(root1);
      return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    } else if (left1 != null && right1 == null && left2 == null && right2 != null && left1.val == right2.val) {
      swap(root1);
      return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    } else if (root1.left != null && root2.left != null && root1.right != null && root2.right != null && root1.left.val == root2.left.val
        && root1.right.val == root2.right.val) {
      return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    } else if (root1.left != null && root2.left != null && root1.right != null && root2.right != null && root1.left.val == root2.right.val
        && root1.right.val == root2.left.val) {
      swap(root1);
      return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    } else if (left1 == null && right1 == null && left2 == null && right2 == null) {
      return true;
    }

    return false;
  }

  private boolean equalsNode(TreeNode a, TreeNode b) {
    if (a != null && b != null) {
      return a.val == b.val;
    }
    if (a == null) {
      return b == null;
    }
    return false;
  }

  private void swap(TreeNode root1) {
    TreeNode left = root1.left;
    root1.left = root1.right;
    root1.right = left;
  }

  public static void main(String args[]) {
    TreeNode root1 = new Codec().deserialize("0,null,1");
    TreeNode root2 = new Codec().deserialize("0,null,1");
    FlipEqBinTree f = new FlipEqBinTree();
    System.out.println(f.flipEquiv(root1, root2));
  }
}
