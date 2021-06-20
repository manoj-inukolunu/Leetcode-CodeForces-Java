package com.leetcode.Tree.medium;

import com.leetcode.dfs.TreeNode;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author manoji on 5/1/20.
 */
public class CBTInserter {

  private TreeNode node;

  public CBTInserter(TreeNode root) {
    this.node = root;
  }

  public int insert(int v) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(node);
    HashSet<TreeNode> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      TreeNode current = queue.poll();
      if (!visited.contains(current)) {
        if (current.left == null) {
          current.left = new TreeNode(v);
          return current.val;
        } else if (current.right == null) {
          current.right = new TreeNode(v);
          return current.val;
        }
        visited.add(current);
        if (current.left != null) {
          queue.add(current.left);
        }
        if (current.right != null) {
          queue.add(current.right);
        }
      }
    }

    return -1;
  }

  public TreeNode get_root() {
    return node;
  }
}
