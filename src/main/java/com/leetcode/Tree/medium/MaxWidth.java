package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author manoji on 4/10/20.
 */
public class MaxWidth {


  private HashMap<Integer, Integer[]> map = new HashMap();

  public int widthOfBinaryTree(TreeNode root) {
    int max = Integer.MAX_VALUE;
    dfs(root, 0, 1);

    for (int level : map.keySet()) {
      Integer[] data = map.get(level);
      if (data[1] - data[0] > max) {
        max = data[1] - data[0];
      }
    }
    return max;

  }

  private void dfs(TreeNode root, int level, int position) {
    if (root == null) {
      return;
    }

    if (map.containsKey(level)) {
      Integer[] data = map.get(level);
      int min = data[0];
      int max = data[0];
      if (position < min) {
        data[0] = position;
      }
      if (position > max) {
        data[1] = position;
      }
      map.put(level, data);
    } else {
      map.put(level, new Integer[]{position, position});
    }

    dfs(root.left, level + 1, 2 * position);
    dfs(root.right, level + 1, 2 * position + 1);


  }


  public static void main(String args[]) {
    String tree = "1";

    TreeNode root = new Codec().deserialize(tree);

    MaxWidth maxWidth = new MaxWidth();

    System.out.println(maxWidth.widthOfBinaryTree(root));
  }


}
