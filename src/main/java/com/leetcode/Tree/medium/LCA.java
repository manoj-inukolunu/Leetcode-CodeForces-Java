/*
package com.leetcode.Tree.medium;

import com.leetcode.dfs.TreeNode;

*/
/**
 * @author manoji on 2020-01-17.
 *//*

public class LCA {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    if (root == null) {
      return null;
    }

    if (root.val == p.val || root.val == q.val) {
      return root;
    }

    TreeNode lcaLeft = lowestCommonAncestor(root.left, p, q);
    TreeNode lcaRight = lowestCommonAncestor(root.right, p, q);

    if (lcaLeft == null && lcaRight != null) {
      return lcaRight;
    }

    if (lcaLeft != null && lcaLeft == null) {
      return lcaRight;
    }


  }

}
*/
