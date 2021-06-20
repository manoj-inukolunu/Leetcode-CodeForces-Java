package com.leetcode.random1;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {


  List<List<String>> ans = new ArrayList<>();

  public List<List<String>> partition(String s) {
    dfs(s, 0, new ArrayList<>());
    return ans;

  }

  private void dfs(String s, int idx, List<String> hold) {
    if (idx >= s.length()) {
      ans.add(new ArrayList<>(hold));
      return;
    }
    for (int i = idx; i <= s.length(); i++) {
      String curr = s.substring(idx, i);
      if (!curr.isEmpty() && isPalin(curr)) {
        hold.add(curr);
        dfs(s, idx + curr.length(), hold);
        hold.remove(curr);
      }
    }
  }

  public boolean isPalin(String str) {
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    PalindromePartition p = new PalindromePartition();
    System.out.println(p.partition("aab"));
  }

}
