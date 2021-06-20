package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 7/30/20.
 */
public class FlattenMultiLevel {

  public static class Node {

    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val) {
      this.val = val;
    }
  }

  public Node flatten(Node head) {

    List<Node> nodes = flattenNode(head);

    for (int i = 1; i < nodes.size(); i++) {
      nodes.get(i).prev = nodes.get(i - 1);
      if (i + 1 < nodes.size()) {
        nodes.get(i).next = nodes.get(i + 1);
      }
      nodes.get(i).child = null;
    }
    nodes.get(0).next = nodes.get(1);
    nodes.get(0).child = null;
    nodes.get(0).prev = null;
    return nodes.get(0);

  }

  private List<Node> flattenNode(Node node) {
    List<Node> list = new ArrayList<>();
    Node temp = node;
    while (temp != null) {
      list.add(temp);
      if (temp.child != null) {
        list.addAll(flattenNode(temp.child));
      }
      temp = temp.next;
    }
    return list;
  }


  public static void main(String args[]) {
    Node node = new Node(1);
    node.child = new Node(3);
    node.child.next = null;
    node.next = new Node(2);

    FlattenMultiLevel f = new FlattenMultiLevel();

    System.out.println(f.flatten(node));
  }

}
