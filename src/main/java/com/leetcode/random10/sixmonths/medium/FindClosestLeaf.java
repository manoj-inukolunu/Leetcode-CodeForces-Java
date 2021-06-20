package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author manoji on 7/26/20.
 */
public class FindClosestLeaf {

  private HashMap<Integer, TreeNode> parentMap = new HashMap<>();

  public class Pair {

    TreeNode node;
    int dist;

    public Pair(TreeNode node, int dist) {
      this.node = node;
      this.dist = dist;
    }
  }

  private TreeNode kNode;

  public int findClosestLeaf(TreeNode root, int k) {
    dfs(root, null, k);
    Pair pair = new Pair(kNode, 0);
    Queue<Pair> queue = new LinkedList<>();
    queue.add(pair);
    int min = Integer.MAX_VALUE;
    int nodeVal = Integer.MAX_VALUE;
    HashSet<Integer> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (isLeaf(curr.node)) {
        if (curr.dist < min) {
          min = curr.dist;
          nodeVal = curr.node.val;
        }
      }
      List<TreeNode> children = new ArrayList<>();
      children.add(curr.node.left);
      children.add(curr.node.right);
      children.add(parentMap.get(curr.node.val));

      for (TreeNode node : children) {
        if (node != null) {
          if (!visited.contains(node.val)) {
            visited.add(node.val);
            queue.add(new Pair(node, curr.dist + 1));
          }
        }
      }
    }
    return nodeVal;
  }

  private boolean isLeaf(TreeNode node) {
    return node.left == null && node.right == null;
  }

  private void dfs(TreeNode root, TreeNode parent, int k) {
    if (root == null) {
      return;
    }

    parentMap.put(root.val, parent);
    if (root.val == k) {
      kNode = root;
    }

    dfs(root.left, root, k);
    dfs(root.right, root, k);
  }

  public static void main(String args[]) {
    FindClosestLeaf f = new FindClosestLeaf();

    System.out.println(f.findClosestLeaf(new Codec().deserialize("1"), 1));
  }

}
