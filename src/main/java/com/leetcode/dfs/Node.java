package com.leetcode.dfs;

import java.util.List;

/**
 * @author manoji on 2019-12-28.
 */
public class Node {

  public int val;
  public List<Node> children;

  public Node() {
  }

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
}
