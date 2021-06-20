/*
package com.leetcode.dfs.medium;

import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author manoji on 2020-01-09.
 *//*

public class Solution1 {

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> paths = new ArrayList();
    psr(root, sum, new ArrayList(), paths);
    return paths;
  }

  private void psr(TreeNode root, int sum, ArrayList<Integer> path) {
    if (findSum(root, sum)) {
      path.add(root.val);
    }
    psr(root.left, sum - root.val, path);
    psr(root.right, sum - root.val, path);
  }

  private boolean findSum(TreeNode root, int sum) {
    if (root.left == null && root.right == null && sum == 0) {
      return true;
    }
    return findSum(root.left, sum - root.val) || findSum(root.right, sum - root.val);
  }

}
*/
