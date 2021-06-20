package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 4/13/20.
 */
public class FlipBinaryTree {


  public void preorder(TreeNode root) {
    if (root == null) {
      return;
    }

    System.out.println(root.val);
    preorder(root.left);
    preorder(root.right);
  }

  public static void main(String args[]) {
    FlipBinaryTree flipBinaryTree = new FlipBinaryTree();

    flipBinaryTree.preorder(new Codec().deserialize("1,2,3,4,5,6,7"));
  }

  public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
    return new ArrayList<>();
  }

}
