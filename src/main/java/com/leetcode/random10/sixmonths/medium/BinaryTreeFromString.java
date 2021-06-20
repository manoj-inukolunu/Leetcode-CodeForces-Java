package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

/**
 * @author manoji on 7/26/20.
 */
public class BinaryTreeFromString {


  public TreeNode str2tree(String s) {
    if (s.isEmpty()) {
      return null;
    }
    return dfs(s, 0);
  }

  private TreeNode dfs(String s, int index) {
    if (s == null || index >= s.length() || s.isEmpty()) {
      return null;
    }

    String rootStr = getInteger(s, index);
    TreeNode root = new TreeNode(Integer.parseInt(rootStr));
    index += rootStr.length();

    String left = getSubTree(s, index);
    root.left = dfs(left, 0);
    String right = getSubTree(s, index + left.length() + 2);
    root.right = dfs(right, 0);

    return root;
  }

  private String getInteger(String s, int index) {
    StringBuffer buffer = new StringBuffer();
    while (index < s.length() && (s.charAt(index) == '-' || Character.isDigit(s.charAt(index)))) {
      buffer.append(s.charAt(index++));
    }
    return buffer.toString();
  }

  private String getSubTree(String s, int index) {
    if (index >= s.length()) {
      return "";
    }
    int count = 0;
    StringBuffer buffer = new StringBuffer();
    while (true) {
      Character ch = s.charAt(index++);
      if (ch == '(') {
        count++;
      } else if (ch == ')') {
        count--;
      }
      buffer.append(ch);
      if (count == 0) {
        break;
      }
    }
    if (buffer.length() > 1) {
      return buffer.substring(1, buffer.length() - 1);
    }
    return "";
  }

  public static void main(String args[]) {
    BinaryTreeFromString b = new BinaryTreeFromString();

//		System.out.println(b.getSubTree("(2(3)(1))(6(5))"));

    System.out.println(new Codec().serialize(b.str2tree("-4(2(3)(1))(6(5)(7))")));
  }

}
