package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 5/21/20.
 */
public class HouseRobber3 {

  public int rob(TreeNode root) {
    int[] arr = robRecur(root);

    return Math.max(arr[0], arr[1]);
  }


  //{incl,excl}
  public int[] robRecur(TreeNode root) {
    if (root == null) {
      return new int[]{0, 0};
    }

    int[] left = robRecur(root.left);
    int[] right = robRecur(root.right);

    return new int[]{Math.max(root.val + left[1], root.val + right[1]), Math.max(Math.max(left[0] + right[0],
        left[1] + right[0]),
        Math.max(left[0] + right[1],
            left[1] + right[1]))};

  }


  public static void main(String args[]) {
    HouseRobber3 h = new HouseRobber3();

    TreeNode root = new Codec().deserialize("3,2,3,null,3,null,1");

    System.out.println(h.rob(root));
  }


}
