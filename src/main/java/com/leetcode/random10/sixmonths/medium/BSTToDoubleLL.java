package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 7/26/20.
 */
public class BSTToDoubleLL {

  public Node treeToDoublyList(Node root) {
    List<Node> list = new ArrayList();
    dfs(root, list);

    for (int i = 1; i < list.size(); i++) {
      Node node = list.get(i);
      node.left = list.get(i - 1);
      if (i + 1 < list.size()) {
        node.right = list.get(i + 1);
      }
    }

    list.get(0).left = list.get(list.size() - 1);
    list.get(list.size() - 1).right = list.get(0);
    list.get(0).right = list.get(1);

    return list.get(0);
  }

  private void dfs(Node root, List<Node> list) {
    if (root == null) {
      return;
    }

    dfs(root.left, list);
    list.add(root);
    dfs(root.right, list);
  }


  public static void main(String args[]) {
    Node node = new Node(4);
    node.right = new Node(5);
    node.left = new Node(2);
    node.left.left = new Node(1);
    node.left.right = new Node(3);

    BSTToDoubleLL b = new BSTToDoubleLL();

    Node ans = b.treeToDoublyList(node);
  }

}
