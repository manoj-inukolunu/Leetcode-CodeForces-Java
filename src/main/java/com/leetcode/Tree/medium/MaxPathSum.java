package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author manoji on 4/29/20.
 */
public class MaxPathSum {

  class Pair {

    int sum;
    boolean incl;

    public Pair(int sum, boolean incl) {
      this.sum = sum;
      this.incl = incl;
    }

  }


  private int max = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    Pair pair = dfs(root);

    System.out.println(pair.sum);
    return max;
  }

  private Pair dfs(TreeNode root) {
    if (root == null) {
      return null;
    }

    int sum = root.val;
    Pair left = dfs(root.left);
    Pair right = dfs(root.right);

    if (left != null && right != null) {
      if (left.incl && right.incl) {
        int rootInclSum = root.val + left.sum + right.sum;
        if (rootInclSum > root.val + left.sum && rootInclSum > root.val + right.sum) {
          return new Pair(rootInclSum, true);
        }
      }

    } else if (left == null && right != null) {

    } else if (left != null && right == null) {

    }

    return new Pair(sum, true);


  }

  public static void main(String args[]) {
    MaxPathSum m = new MaxPathSum();
    TreeNode root = new Codec().deserialize("1");
    System.out.println(m.maxPathSum(root));
  }

}
