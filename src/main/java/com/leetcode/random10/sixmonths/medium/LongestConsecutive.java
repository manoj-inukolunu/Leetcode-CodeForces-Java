package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 8/8/20.
 */
public class LongestConsecutive {


  int ans = Integer.MIN_VALUE;

  public int longestConsecutive(TreeNode root) {
    int[] val = dfs(root);

    return findMax(val);
  }

  private int findMax(int[] arr) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      max = Math.max(arr[i], max);
    }
    return max;
  }

  private int[] dfs(TreeNode root) {
    if (root == null) {
      return new int[]{0, 0, 0, 0};
    }

    int[] left = dfs(root.left);
    int[] right = dfs(root.right);

    //ans = Math.max(ans, Math.max(findMax(left), findMax(right)));

    if (root.left == null && root.right == null) {
      return new int[]{0, 0, 1, 1};
    }

    int decr = 1, incr = 1;
    if (root.left != null && root.right != null) {
      if (root.right.val == root.val + 1 && root.val == root.left.val + 1) {
        decr = left[3] + right[3] + 1;
      }
      if (root.left.val == root.val + 1 && root.val == root.right.val + 1) {
        incr = left[2] + right[2] + 1;
      }
      if (root.right.val == root.val + 1 && root.val != root.left.val + 1) {
        incr = right[3] + 1;
      }
      if (root.right.val != root.val + 1 && root.val == root.left.val + 1) {
        incr = left[3] + 1;
      }

      if (root.left.val == root.val + 1 && root.val != root.right.val + 1) {
        decr = left[2] + 1;
      }
      if (root.left.val != root.val + 1 && root.val == root.right.val + 1) {
        decr = right[2] + 1;
      }

    } else if (root.left == null && root.right != null) {
      if (root.right.val == root.val + 1) {
        decr = right[3] + 1;
      }
      if (root.right.val + 1 == root.val) {
        incr = right[2] + 1;
      }
    } else if (root.left != null && root.right == null) {
      if (root.left.val == root.val + 1) {
        decr = left[3] + 1;
      }
      if (root.left.val + 1 == root.val) {
        incr = left[2] + 1;
      }
    }

    return new int[]{findMax(left), findMax(right), incr, decr};
  }


  public static void main(String args[]) {
    TreeNode root = new Codec().deserialize("1,5,4,2,null,null,null,3");

    LongestConsecutive l = new LongestConsecutive();

    System.out.println(l.longestConsecutive(root));
  }

}
