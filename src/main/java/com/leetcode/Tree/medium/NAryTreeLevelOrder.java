package com.leetcode.Tree.medium;

import com.leetcode.dfs.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 2020-01-16.
 */
public class NAryTreeLevelOrder {

  public List<List<Integer>> levelOrder(Node root) {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    List<List<Integer>> list = new ArrayList();

    dfs(root, map, 0);

    for (Integer level : map.keySet()) {
      Collections.reverse(map.get(level));
      list.add(map.get(level));
    }
    return list;

  }

  private void dfs(Node root, HashMap<Integer, List<Integer>> map, int level) {
    if (root == null) {
      return;
    }
    if (map.containsKey(level)) {
      List<Integer> list = map.get(level);
      list.add(root.val);
      map.put(level, list);
    } else {
      List<Integer> list = new ArrayList<>();
      list.add(root.val);
      map.put(level, list);
    }

    for (Node child : root.children) {
      dfs(child, map, level + 1);
    }
  }

  public static void main(String args[]) {
    NAryTreeLevelOrder nAryTreeLevelOrder = new NAryTreeLevelOrder();

    //nAryTreeLevelOrder.levelOrder()
  }

}
