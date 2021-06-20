package com.leetcode.random;

import java.util.HashSet;
import java.util.Set;

public class MaxUniqueSplit {

  int max = Integer.MIN_VALUE;
  boolean reachedEnd = false;
  String str;

  public int maxUniqueSplit(String s) {
    Set<String> set = new HashSet<>();
    str = s;
    dfs(set, s, 0);
    if (reachedEnd) {
      return max;
    }
    return 1;
  }

  private void dfs(Set<String> set, String str, int start) {
    if (start >= str.length()) {
      max = Math.max(max, set.size());
      return;
    }
    for (int i = start; i <= str.length(); i++) {
      String curr = str.substring(start, i);
      if (!set.contains(curr) && !curr.isEmpty()) {
        set.add(curr);
        dfs(set, str, i);
        max = Math.max(max, set.size());
        StringBuffer buffer = new StringBuffer();
        for (String _str : set) {
          buffer.append(_str);
        }
        if (buffer.toString().equals(str)) {
          reachedEnd = true;
        }
        set.remove(curr);
      }
    }
  }

  public static void main(String args[]) {
    String str = "aa";
    MaxUniqueSplit m = new MaxUniqueSplit();
    System.out.println(m.maxUniqueSplit(str));
  }

}
