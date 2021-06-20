package com.leetcode.dfs.medium;

import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 2020-01-17.
 */
public class MaximumBinaryTree {

  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return cmbtr(nums, 0, nums.length);
  }

  private TreeNode cmbtr(int[] nums, int startIndex, int endIndex) {
    if (startIndex >= endIndex) {
      return null;
    }
    Pair max = getMax(nums, startIndex, endIndex);
    TreeNode root = new TreeNode(max.max);
    root.left = cmbtr(nums, startIndex, max.index);
    root.right = cmbtr(nums, max.index + 1, endIndex);
    return root;
  }

  class Pair {

    int max;
    int index;
  }

  private Pair getMax(int[] nums, int start, int end) {
    int max = Integer.MIN_VALUE;
    int index = -1;
    for (int i = start; i < end; i++) {
      if (nums[i] > max) {
        max = nums[i];
        index = i;
      }
    }
    Pair pair = new Pair();
    pair.max = max;
    pair.index = index;
    return pair;
  }

  public static void main(String args[]) {
    MaximumBinaryTree binaryTree = new MaximumBinaryTree();
    System.out.println(binaryTree.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5}));
  }
}
