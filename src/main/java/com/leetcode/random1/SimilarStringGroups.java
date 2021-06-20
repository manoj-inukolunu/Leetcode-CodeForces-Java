package com.leetcode.random1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SimilarStringGroups {

  private boolean similar(String a, String b) {
    if (a.length() == b.length()) {
      int count = 0;
      HashSet<Character> set = new HashSet<>();
      for (int i = 0; i < a.length(); i++) {
        if (a.charAt(i) != b.charAt(i)) {
          if (count > 2) {
            return false;
          }
          set.add(a.charAt(i));
          count++;
        }
      }
      return set.size() == 2;
    }
    return false;
  }


  public int numSimilarGroups(String[] A) {

    HashMap<Integer, Set<Integer>> map = new HashMap<>();

    for (int i = 0; i < A.length; i++) {
      for (int j = i + 1; j < A.length; j++) {
        if (similar(A[i], A[j])) {
          Set<Integer> set = map.getOrDefault(i, new HashSet<>());
          set.add(j);
          map.put(i, set);
          set = map.getOrDefault(j, new HashSet<>());
          set.add(i);
          map.put(j, set);
        }
      }
    }
    HashSet<Integer> visited = new HashSet<>();
    int ans = 0;
    for (int key : map.keySet()) {
      if (!visited.contains(key)) {
        dfs(key, visited, map);
        ans++;
      }
    }
    return ans;
  }

  private void dfs(int curr, HashSet<Integer> visited, HashMap<Integer, Set<Integer>> map) {
    if (!visited.contains(curr)) {
      visited.add(curr);
      if (map.containsKey(curr)) {
        for (int child : map.get(curr)) {
          dfs(child, visited, map);
        }
      }
    }
  }

  public static void main(String args[]) {
    SimilarStringGroups s = new SimilarStringGroups();
    System.out.println(s.similar("rats", "arts"));
  }


}
