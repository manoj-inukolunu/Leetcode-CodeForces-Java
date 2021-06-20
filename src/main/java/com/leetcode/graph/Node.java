package com.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 4/20/20.
 */
public class Node {

  public int val;
  public List<Node> neighbors;


  public Node(int val) {
    this.val = val;
    this.neighbors = new ArrayList<>();
  }

  public Node(int val, ArrayList<Node> neighbors) {
    this.val = val;
    this.neighbors = neighbors;
  }


}
