package com.leetcode.dfs;

/**
 * @author manoji on 3/6/20.
 */
public class SortedArrayToBST {

  public TreeNode sortedArrayToBST(int[] nums) {
    return getTree(nums, 0, nums.length);
  }


  private TreeNode getTree(int[] nums, int start, int end) {
    if (start == end && start >= 0 && start <= nums.length - 1) {
      return new TreeNode(nums[start]);
    }
    if (start >= end) {
      return null;
    }

    int mid = start + ((end - start) / 2);
    TreeNode root = new TreeNode(nums[mid]);
    root.left = getTree(nums, start, mid - 1);
    root.right = getTree(nums, mid + 1, end);
    return root;
  }

  public static void main(String args[]) {
    SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
    System.out.println(sortedArrayToBST.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
  }
}
