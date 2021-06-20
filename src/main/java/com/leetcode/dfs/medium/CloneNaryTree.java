package com.leetcode.dfs.medium;

import com.google.common.collect.Lists;
import com.leetcode.dfs.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.checkerframework.checker.units.qual.C;

public class CloneNaryTree {

  private Node ans;
  private boolean updated = false;

  public Node cloneTree(Node root) {
    dfs(root, null, root);
    return ans;
  }

  private void dfs(Node node, Node parent, Node mainRoot) {
    if (node == null) {
      return;
    }
    Node curr = new Node(node.val, new ArrayList<>());
    if (!updated) {
      ans = curr;
      updated = true;
    }
    if (parent != null) {
      parent.children.add(curr);
    }
    for (Node child : mainRoot.children) {
      dfs(child, curr, child);
    }
  }


  public static void main(String args[]) {
    CloneNaryTree c = new CloneNaryTree();
    Node node = new Node(1, Lists.newArrayList(new Node(3, new ArrayList<>()), new Node(2), new Node(4)));
    node.children.get(0).children.addAll(Lists.newArrayList(new Node(5), new Node(6)));
    c.cloneTree(node);
  }

}
