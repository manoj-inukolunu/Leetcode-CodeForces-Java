package com.leetcode.queue;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllPossibleBT {

  HashMap<Integer, List<TreeNode>> map = new HashMap<>();

  public List<TreeNode> allPossibleFBT(int N) {
    if (map.containsKey(N)) {
      return map.get(N);
    }
    if (N == 2 || N == 0) {
      return new ArrayList();
    }
    if (N == 1) {
      TreeNode node = new TreeNode(0);
      ArrayList<TreeNode> res = new ArrayList();
      res.add(node);
      return res;
    }
    List<TreeNode> ans = new ArrayList();

    for (int i = 1; i <= N; i++) {
      List<TreeNode> left = allPossibleFBT(i - 1);
      List<TreeNode> right = allPossibleFBT(N - i);
      if (left != null && right != null && !left.isEmpty() && !right.isEmpty()) {
        for (int j = 0; j < left.size(); j++) {
          for (int k = 0; k < right.size(); k++) {
            TreeNode root = new TreeNode(0);
            root.left = left.get(j);
            root.right = right.get(k);
            ans.add(root);
            /*if (N == 7) {
              System.out.println(new Codec().serialize(root));
            }*/
          }
        }
      }
    }
    map.put(N, ans);
    return ans;
  }

  public static void main(String args[]) {
    AllPossibleBT a = new AllPossibleBT();
    List<TreeNode> list = a.allPossibleFBT(7);
    System.out.println();
    list.forEach(treeNode -> System.out.println(new Codec().serialize(treeNode)));
  }

}
