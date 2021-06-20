package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 7/26/20.
 */
public class Node {

  public int val;
  public Node left;
  public Node right;
  public Node parent;

  public Node() {
  }

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, Node _left, Node _right, Node _parent) {
    val = _val;
    left = _left;
    right = _right;
    parent = _parent;
  }
}
