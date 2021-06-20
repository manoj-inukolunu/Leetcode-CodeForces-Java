package com.leetcode.random12;

import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoundaryBinaryTree {

  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    if (root == null) {
      return new ArrayList();
    }
    List<Integer> left = new ArrayList<>();
    List<Integer> leaves = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    if (isLeaf(root)) {
      left.add(root.val);
      return left;
    }

    TreeNode temp = root;
    left.add(temp.val);
    if (temp.left != null) {
      temp = temp.left;
      while (temp != null) {
        if (!isLeaf(temp)) {
          left.add(temp.val);
        }
        if (temp.left != null) {
          temp = temp.left;
        } else {
          temp = temp.right;
        }
      }
    }
    temp = root.right;
    while (temp != null) {
      if (!isLeaf(temp)) {
        right.add(temp.val);
      }
      if (temp.right != null) {
        temp = temp.right;
      } else {
        temp = temp.left;
      }
    }

    dfs(root, leaves);

    left.addAll(leaves);
    Collections.reverse(right);
    left.addAll(right);

    return left;
  }

  private boolean isLeaf(TreeNode temp) {
    return temp.left == null && temp.right == null;
  }


  private void dfs(TreeNode root, List<Integer> leaves) {
    if (root == null) {
      return;
    }
    if (isLeaf(root)) {
      leaves.add(root.val);
    }
    dfs(root.left, leaves);
    dfs(root.right, leaves);

  }
}
