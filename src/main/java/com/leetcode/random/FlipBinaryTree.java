package com.leetcode.random;

import com.leetcode.random10.sixmonths.medium.Node;
import java.util.ArrayList;
import java.util.List;

public class FlipBinaryTree {


  private List<Node> list = new ArrayList<>();

  public Node flipBinaryTree(Node root, Node leaf) {
    dfs(root, leaf, new ArrayList<>());
    for (int i = list.size() - 1; i > 0; i--) {
      Node curr = list.get(i);
      Node oParent = curr.parent;
      if (curr.left != null) {
        curr.right = curr.left;
      }
      curr.left = oParent;
      if (oParent.left.val == curr.val) {
        oParent.left = null;
      } else {
        oParent.right = null;
      }
    }
    root.left = null;
    root.parent = list.get(1);
    leaf.parent = null;
    dfs(leaf);
    return leaf;
  }

  private void dfs(Node root) {
    if (root == null) {
      return;
    }
    System.out.println(root.val);
    if (root.left != null) {
      root.left.parent = root;
    }
    if (root.right != null) {
      root.right.parent = root;
    }
    dfs(root.left);
    dfs(root.right);
  }

  private void dfs(Node root, Node leaf, List<Node> hold) {
    if (root == null) {
      return;
    }
    hold.add(root);
    if (root.val == leaf.val) {
      list.addAll(hold);
      return;
    }
    dfs(root.left, leaf, hold);
    dfs(root.right, leaf, hold);
    hold.remove(root);
  }


  public static void main(String args[]) {

    Node root = new Node(3);
    root.left = new Node(5);
    root.left.parent = root;
    root.left.left = new Node(6);
    root.left.left.parent = root.left;
    root.right = new Node(1);
    root.right.parent = root;
    root.right.left = new Node(0);
    root.right.left.parent = root.right;
    root.right.right = new Node(8);
    root.right.right.parent = root.right;
    root.left.right = new Node(2);
    root.left.right.parent = root.left;
    root.left.right.left = new Node(7);
    root.left.right.left.parent = root.left.right;
    root.left.right.right = new Node(4);
    root.left.right.right.parent = root.left.right;

    FlipBinaryTree f = new FlipBinaryTree();

    Node node = f.flipBinaryTree(root, root.left.right.left);

    System.out.println(node);
  }

}
