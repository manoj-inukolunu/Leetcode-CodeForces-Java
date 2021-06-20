package com.leetcode.Tree;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 3/25/20.
 */
public class Forest {


  public int longestUnivaluePath(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 0;
    }
    if (root.left.val == root.val && root.right.val == root.val) {
      return 2 + longestUnivaluePath(root.left) + longestUnivaluePath(root.right);
    } else if (root.left.val == root.val && root.right.val != root.val) {
      return 1 + longestUnivaluePath(root.left);
    } else if (root.right.val == root.val && root.left.val != root.val) {
      return 1 + longestUnivaluePath(root.right);
    } else {
      return Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right));
    }

  }

  public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    List<TreeNode> list = new ArrayList();
    list.add(root);
    for (int i = 0; i < to_delete.length; i++) {
      for (int j = 0; j < list.size(); j++) {
        if (delete(list.get(j), null, to_delete[i], list)) {
          break;
        }
      }
    }

    return list;
  }

  private boolean delete(TreeNode root, TreeNode parent, int del, List<TreeNode> list) {
    if (root == null) {
      return false;
    }
    if (root.val == del) {
      if (parent != null && parent.left != null && parent.left.val == root.val) {
        if (root.left != null) {
          list.add(root.left);
        }
        if (root.right != null) {
          list.add(root.right);
        }
        parent.left = null;
      }

      if (parent != null && parent.right != null && parent.right.val == root.val) {
        if (root.left != null) {
          list.add(root.left);
        }
        if (root.right != null) {
          list.add(root.right);
        }
        parent.right = null;
      }

      if (parent == null && root.val == del) {
        list.remove(root);
        if (root.left != null) {
          list.add(root.left);
        }
        if (root.right != null) {
          list.add(root.right);
        }
      }

      return true;
    }

    return delete(root.left, root, del, list) || delete(root.right, root, del, list);

  }


  public boolean repeatedSubstringPattern(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (func(s.substring(0, i), s)) {
        return true;
      }
    }

    return false;
  }

  private boolean func(String str1, String str2) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < str2.length(); i++) {
      buffer.append(str1);
      if (buffer.toString().equalsIgnoreCase(str2)) {
        return true;
      }
    }

    return false;
  }


  public static void main(String args[]) {
    TreeNode node = new Codec().deserialize("5,4,5,1,1,5");

    Forest forest = new Forest();

    System.out.println(forest.repeatedSubstringPattern("ababab"));


  }

}
