package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author manoji on 7/26/20.
 */
public class VerticalOrderTraversal {

  TreeMap<Integer, List<Integer[]>> map = new TreeMap<>();

  public List<List<Integer>> verticalOrder(TreeNode root) {
    dfs(root, 0, 0);
    List<List<Integer>> ans = new ArrayList<>();
    for (int xCoord : map.keySet()) {
      List<Integer[]> integers = map.get(xCoord);
      Collections.sort(integers, Comparator.comparingInt(value -> value[1]));
      ans.add(integers.stream().map(value -> value[0]).collect(Collectors.toList()));
    }
    return ans;
  }

  private void dfs(TreeNode root, int xCoord, int yCoord) {
    if (root == null) {
      return;
    }
    List<Integer[]> xList = map.getOrDefault(xCoord, new ArrayList<>());
    xList.add(new Integer[]{root.val, yCoord});
    map.put(xCoord, xList);

    dfs(root.left, xCoord - 1, yCoord + 1);
    dfs(root.right, xCoord + 1, yCoord + 1);
  }


  public static void main(String args[]) {
    VerticalOrderTraversal v = new VerticalOrderTraversal();
    System.out.println(v.verticalOrder(new Codec().deserialize("3,9,20,null,null,15,7")));
  }
}
