package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.HashMap;

/**
 * @author manoji on 3/24/20.
 */
public class SmallestStringFromLeaf {

  HashMap<Integer, Character> map = new HashMap<>();

  public String smallestFromLeaf(TreeNode root) {
    return dfs(root);
  }

  private String dfs(TreeNode root) {
    if (root == null) {
      return "";
    }

    String left = dfs(root.left);
    String right = dfs(root.right);
    if (left.isEmpty() && !right.isEmpty()) {
      return right + (char) (root.val + 97);
    } else if (right.isEmpty() && !left.isEmpty()) {
      return left + (char) (root.val + 97);
    }

    left = left + (char) (root.val + 97);
    right = right + (char) (root.val + 97);
    int lex = left.compareTo(right);
    if (lex < 0) {
      return left;
    } else if (lex > 0) {
      return right;
    } else {
      return left;
    }
  }

  public static void main(String args[]) {
    SmallestStringFromLeaf s = new SmallestStringFromLeaf();
    System.out.println(s.smallestFromLeaf(new Codec().deserialize("25,1,null,0,0,1,null,null,null,0")));
    System.out.println("ababz".compareTo("abz"));
    System.out.println("0101".compareTo("01"));
  }

}
