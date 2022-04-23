package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LC2246 {

  class Node {
    int val;
    char ch;
    List<Node> nei;

    public Node(int val, char ch) {
      this.val = val;
      this.ch = ch;
      this.nei = new ArrayList<>();
    }

    @Override
    public String toString() {
      return String.valueOf(ch) + nei.toString();
    }
  }

  public int longestPath(int[] parent, String s) {
    Node root = new Node(0, s.charAt(0));
    HashMap<Integer, Node> map = new HashMap<>();
    map.put(0, root);
    for (int i = 1; i < parent.length; i++) {
      if (map.containsKey(parent[i])) {
        Node n = new Node(i, s.charAt(i));
        map.get(parent[i]).nei.add(n);
        if (!map.containsKey(i)) {
          map.put(i, n);
        }
      } else {
        map.put(i, new Node(i, s.charAt(i)));
      }
    }
    System.out.println(map);
    int val = dfs(root);
    max = Math.max(max, val);
    return max;
  }

  int max = 0;

  private int dfs(Node root) {
    if (root == null) {
      return 0;
    }
    int ret = 1;
    List<Integer> list = new ArrayList<>();
    for (Node node : root.nei) {
      System.out.println(node.ch + " " + node.nei);
      int val = dfs(node);
      if (node.ch != root.ch) {
        ret = Math.max(1 + val, ret);
        list.add(val);
        max = Math.max(max, 1 + val);
      }
    }
    Collections.sort(list);
    if (list.size() >= 2) {
      max = Math.max(max, 1 + list.get(list.size() - 1) + list.get(list.size() - 2));
    }

    return ret;
  }

  public static void main(String[] args) {
    LC2246 l = new LC2246();
    System.out.println(l.longestPath(new int[] {-1, 0, 0, 0}, "aabc"));
  }
}
