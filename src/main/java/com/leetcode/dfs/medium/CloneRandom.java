/*
package com.leetcode.dfs.medium;

import java.util.HashMap;
import java.util.HashSet;

public class CloneRandom {

  class NodeCopy extends Node {

    public NodeCopy(int i) {
      super(i);
    }
  }


  public static class Node {

    int val;
    Node left;
    Node right;
    Node random;

    Node() {
    }

    public Node(int val) {
      this.val = val;
    }

    Node(int val, Node left, Node right, Node random) {
      this.val = val;
      this.left = left;
      this.right = right;
      this.random = random;
    }
  }

  HashMap<Node, NodeCopy> visited = new HashMap<>();

  boolean val = false;
  NodeCopy res = null;

  public NodeCopy copyRandomBinaryTree(Node root) {
    dfs(root, 0);
    return res;
  }

  private void dfs(Node root, int index) {
    if (root == null) {
      return;
    }
    if (!visited.containsKey(root)) {
      NodeCopy nodeCopy = new NodeCopy(root.val);
      nodeCopy.left = new NodeCopy(root.left.val);
      nodeCopy.right = new NodeCopy(root.right.val);
      if (visited.containsKey(root.random)) {
        nodeCopy.random = visited.get(root.random);
      } else {
        nodeCopy.random = new NodeCopy(root.random.val);
      }
      visited.put(root, nodeCopy);
      if (!val) {
        res = nodeCopy;
      }
      dfs(root.left, index + 1);
      dfs(root.right, index + 1);
      dfs(root.random, index + 1);
    }
  }





}
*/
