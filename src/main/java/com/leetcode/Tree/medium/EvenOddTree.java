package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

public class EvenOddTree {

  class Pair {

    int level;
    TreeNode node;

    public Pair(int level, TreeNode node) {
      this.level = level;
      this.node = node;
    }
  }

  public boolean isEvenOddTree(TreeNode root) {
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(0, root));
    TreeNode prevNode = null;
    int prevLevel = -1;
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.level == 0 && curr.node.val % 2 == 0) {
        return false;
      }
      if (prevLevel != curr.level) {
        prevNode = curr.node;
      } else if (prevNode != null) {
        if (curr.level % 2 == 0 && !(prevNode.val < curr.node.val && curr.node.val % 2 != 0)) {
          return false;
        } else if (curr.level % 2 != 0 && !(prevNode.val > curr.node.val && curr.node.val % 2 == 0)) {
          return false;
        }
      }
      if (curr.node.left != null) {
        queue.add(new Pair(curr.level + 1, curr.node.left));
      }
      if (curr.node.right != null) {
        queue.add(new Pair(curr.level + 1, curr.node.right));
      }
      prevLevel = curr.level;
    }

    return true;
  }

  public static void main(String args[]) {
    EvenOddTree e = new EvenOddTree();
    TreeNode node = new Codec().deserialize("11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17");
    System.out.println(e.isEvenOddTree(node));
  }


}
