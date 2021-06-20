package com.leetcode.random;

import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class KClosest {

  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    List<Integer> list = new ArrayList();
    dfs(root, list);
    PriorityQueue<Double[]> p = new PriorityQueue((Comparator<Double[]>) (o1, o2) -> Double.compare(o1[0], o2[0]));
    for (Integer i : list) {
      p.add(new Double[]{i - target, Double.valueOf(i)});
    }

    List<Integer> ans = new ArrayList<>();
    while (k > 0) {
      ans.add(p.poll()[1].intValue());
      k--;
    }
    return ans;
  }


  private void dfs(TreeNode root, List<Integer> list) {
    if (root == null) {
      return;
    }
    dfs(root.left, list);
    list.add(root.val);
    dfs(root.right, list);
  }

}
