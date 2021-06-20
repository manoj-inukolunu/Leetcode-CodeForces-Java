package com.leetcode.random1;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GoodLeaf {


  int ans = 0;

  public int countPairs(TreeNode root, int distance) {
    HashMap<TreeNode, TreeNode> parentMap = new HashMap();
    List<TreeNode> lList = new ArrayList();
    dfs(root, lList, parentMap, null);
    for (TreeNode curr : lList) {
      HashSet<TreeNode> visited = new HashSet<>();
      dfs(curr, distance, parentMap, 0, visited);
    }
    return ans / 2;
  }

  private void dfs(TreeNode curr, int dist, HashMap<TreeNode, TreeNode> parentMap, int currDist, HashSet<TreeNode> visited) {
    if (curr == null || currDist > dist || visited.contains(curr)) {
      return;
    }
    visited.add(curr);
    if (curr.left == null && curr.right == null && currDist != 0) {
      ans++;
    }
    dfs(curr.left, dist, parentMap, currDist + 1, visited);
    dfs(curr.right, dist, parentMap, currDist + 1, visited);
    dfs(parentMap.get(curr), dist, parentMap, currDist + 1, visited);
  }

  private void dfs(TreeNode curr, List<TreeNode> lList, HashMap<TreeNode, TreeNode> map, TreeNode parent) {
    if (curr == null) {
      return;
    }
    if (curr.left == null && curr.right == null) {
      lList.add(curr);
    }
    if (parent != null) {
      map.put(curr, parent);
    }
    dfs(curr.left, lList, map, curr);
    dfs(curr.right, lList, map, curr);
  }

  public static void main(String args[]) {
    TreeNode root = new Codec().deserialize("1,2,3,4,5,6,7");
    GoodLeaf g = new GoodLeaf();
    System.out.println(g.countPairs(root, 3));
  }
}
