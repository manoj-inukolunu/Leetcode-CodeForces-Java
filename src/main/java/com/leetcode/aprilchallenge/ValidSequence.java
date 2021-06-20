package com.leetcode.aprilchallenge;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 4/30/20.
 */
public class ValidSequence {

  public boolean isValidSequence(TreeNode root, int[] arr) {
    return valid(root, 0, arr);
  }


  private boolean valid(TreeNode root, int index, int[] arr) {

    if (root == null) {
      return false;
    }
    if (index >= arr.length) {
      return false;
    }
    if (root.left == null && root.right == null && root.val == arr[index] && index == arr.length - 1) {
      return true;
    }
    return (valid(root.left, index + 1, arr) || valid(root.right, index + 1, arr)) && root.val == arr[index];
  }


  public static void main(String args[]) {
		/*
		[8,3,null,2,1,5,4]
[8]
		 */
    TreeNode root = new Codec().deserialize("8,3,null,2,1,5,4");
    int[] arr = new int[]{8};

    ValidSequence validSequence = new ValidSequence();

    System.out.println(validSequence.isValidSequence(root, arr));
  }

}
