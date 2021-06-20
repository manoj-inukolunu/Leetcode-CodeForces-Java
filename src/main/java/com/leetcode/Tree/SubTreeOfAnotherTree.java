package com.leetcode.Tree;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 5/5/20.
 */
public class SubTreeOfAnotherTree {


  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    }
    if ((s == null && t != null) || (s != null && t == null)) {
      return false;
    }

    if (s != null && t != null && s.val == t.val) {
      return isSubtree(s.left, t.left) && isSubtree(s.right, t.right);
    } else {
      return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
  }

  public static void main(String args[]) {
    SubTreeOfAnotherTree s = new SubTreeOfAnotherTree();
    System.out.println(s.isSubtree(new Codec().deserialize("1,1"), new Codec().deserialize("1")));
  }


}
