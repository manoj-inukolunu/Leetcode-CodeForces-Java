package com.random3;


import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class LC113 {


  List<List<Integer>> collector = new ArrayList<>();
  int sum = 0;

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    dfs(root, new ArrayList<>());
    return new ArrayList<>(collector);
  }

  public int sumEvenGrandparent(TreeNode root) {
    dfs(root, new ArrayList<>());
    return sum;
  }

  private List<List<Integer>> dfs(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    if (root.left == null && root.right == null) {
      List<Integer> list = new ArrayList<>();
      list.add(root.val);
      List<List<Integer>> ret = new ArrayList<>();
      ret.add(list);
      return ret;
    }
    List<List<Integer>> left = dfs(root.left);
    List<List<Integer>> right = dfs(root.right);
    for (List<Integer> list : left) {
      list.add(0, root.val);
    }
    for (List<Integer> list : right) {
      list.add(0, root.val);
    }
    left.addAll(right);
    return left;
  }

  private void dfs(TreeNode root, List<Integer> path) {
    if (root == null) {
      return;
    }
    path.add(root.val);
    if (root.left == null && root.right == null) {
      path.remove(path.size() - 1);
      return;
    }
    if (path.size() > 2) {
      int idx = path.size() - 3;
      if (path.get(idx) % 2 == 0) {
        sum += path.get(idx);
      }
    }
    dfs(root.left, path);
    dfs(root.right, path);
    path.remove(path.size() - 1);
  }

  public static void main(String[] args) {
    LC113 l = new LC113();
    TreeNode node = new Codec().deserialize("6,7,8,2,7,1,3,9,null,1,4,null,null,null,5");
    System.out.println(l.sumEvenGrandparent(node));
  }
}




