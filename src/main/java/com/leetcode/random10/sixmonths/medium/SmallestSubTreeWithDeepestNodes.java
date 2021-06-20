package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.TreeNode;
import java.util.HashSet;
import java.util.Set;

/**
 * @author manoji on 8/9/20.
 */
public class SmallestSubTreeWithDeepestNodes {


  public TreeNode subtreeWithAllDeepest(TreeNode root) {

    HashSet<TreeNode> set = new HashSet<>();
    int depth = findDepth(root);
    dfs(root, 1, depth, set);
    return findLCA(root, set);
  }


  private TreeNode findLCA(TreeNode root, Set<TreeNode> set) {
    if (root == null) {
      return null;
    }
    if (set.contains(root)) {
      return root;
    }

    TreeNode left = findLCA(root.left, set);
    TreeNode right = findLCA(root.right, set);

    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }
    return root;
  }

  private void dfs(TreeNode root, int curr, int depth, HashSet<TreeNode> set) {
    if (root == null) {
      return;
    }

    if (curr == depth) {
      set.add(root);
    }
    dfs(root.left, curr + 1, depth, set);
    dfs(root.right, curr + 1, depth, set);

  }

  private int findDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(findDepth(root.left), findDepth(root.right));
  }

}
