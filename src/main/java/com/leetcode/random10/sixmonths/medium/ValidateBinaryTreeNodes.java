package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ValidateBinaryTreeNodes {

  public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

    int[] indegree = new int[n];
    Arrays.fill(indegree, 0);
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      List<Integer> list = map.getOrDefault(i, new ArrayList<>());
      if (leftChild[i] != -1) {
        indegree[leftChild[i]]++;
        list.add(leftChild[i]);
      }
      if (rightChild[i] != -1) {
        indegree[rightChild[i]]++;
        list.add(rightChild[i]);
      }
      map.put(i, list);
    }
    int root = -1;
    for (int i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) {
        if (root != -1) {
          return false;
        }
        root = i;
      }
    }
    if (root == -1) {
      return false;
    }

    HashSet<Integer> set = new HashSet<>();
    boolean ans = dfs(root, set, map);
    if (set.size() != n) {
      return false;
    }
    return ans;
  }

  private boolean dfs(int root, HashSet<Integer> set, HashMap<Integer, List<Integer>> map) {
    if (!set.contains(root)) {
      set.add(root);
      boolean res = true;
      if (map.containsKey(root) && map.get(root).size() > 2) {
        return false;
      } else {
        if (map.containsKey(root)) {
          for (Integer child : map.get(root)) {
            res = res && dfs(child, set, map);
          }
        }
      }
      return res;
    } else {
      return false;
    }
  }


  public static void main(String args[]) {
    ValidateBinaryTreeNodes v = new ValidateBinaryTreeNodes();

    System.out.println(v.validateBinaryTreeNodes(6, new int[]{1, -1, -1, 4, -1, -1}, new int[]{2, -1, -1, 5, -1, -1}));
  }

}
