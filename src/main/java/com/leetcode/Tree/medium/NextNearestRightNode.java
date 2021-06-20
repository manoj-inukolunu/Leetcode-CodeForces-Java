package com.leetcode.Tree.medium;

import com.leetcode.dfs.TreeNode;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class NextNearestRightNode {

  class Pair {

    TreeNode node;
    int level;

    public Pair(TreeNode node, int level) {
      this.node = node;
      this.level = level;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return level == pair.level &&
          Objects.equals(node, pair.node);
    }

    @Override
    public int hashCode() {
      return Objects.hash(node, level);
    }
  }

  public TreeNode findNeartestRightNode(TreeNode root, TreeNode u) {
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(root, 0));
    HashSet<Pair> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.node.val == u.val) {
        if (queue.peek() != null && queue.peek().level == curr.level) {
          return queue.peek().node;
        }
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        if (curr.node.left != null) {
          queue.add(new Pair(curr.node.left, curr.level + 1));
        }
        if (curr.node.right != null) {
          queue.add(new Pair(curr.node.right, curr.level + 1));
        }
      }
    }
    return null;
  }

}
