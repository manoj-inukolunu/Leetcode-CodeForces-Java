package com.leetcode.Tree.medium;

import com.google.common.collect.Lists;
import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author manoji on 2020-01-15.
 */
public class VerticalOrder {

  static class Pair {

    Integer x;
    Integer y;
    TreeNode node;

    Pair(int x, int y, TreeNode node) {
      this.x = x;
      this.y = y;
      this.node = node;
    }


  }

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    HashMap<Pair, List<Integer>> map = new HashMap();
    List<Pair> pairs = new ArrayList();
    Pair rootPair = new Pair(0, 0, root);
    dfs(root, pairs, rootPair);
    List<List<Integer>> lists = new ArrayList();

    TreeMap<Integer, List<Pair>> listMap = new TreeMap<>();
    for (Pair pair : pairs) {
      if (listMap.containsKey(pair.x)) {
        List<Pair> list = listMap.get(pair.x);
        list.add(pair);
        listMap.put(pair.x, list);
      } else {
        List<Pair> list = new ArrayList();
        list.add(pair);
        listMap.put(pair.x, list);
      }
    }

    for (List<Pair> list : listMap.values()) {
      Collections.sort(list, new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
          if (o1.x.equals(o2.x) && o1.y.equals(o2.y)) {
            return o1.node.val <= o2.node.val ? -1 : 1;
          }
          return o1.y.compareTo(o2.y) == 1 ? -1 : 1;
        }
      });
      lists.add(list.stream().map(pair -> pair.node.val).collect(Collectors.toList()));
    }
    return lists;
  }

  private void dfs(TreeNode root, List<Pair> map, Pair level) {
    if (root == null) {
      return;
    }

    map.add(level);
    dfs(root.left, map, new Pair(level.x - 1, level.y - 1, root.left));
    dfs(root.right, map, new Pair(level.x + 1, level.y - 1, root.right));
  }

  public static void main(String args[]) {
    VerticalOrder verticalOrder = new VerticalOrder();
    System.out.println(verticalOrder.verticalTraversal(new Codec().deserialize("1,2,3,4,5,6,7")));
  }

}
