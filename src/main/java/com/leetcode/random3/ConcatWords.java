package com.leetcode.random3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatWords {

  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    Set<String> set = new HashSet<>();
    for (String str : words) {
      set.add(str);
    }

    List<String> ans = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      Boolean[][] dp = new Boolean[words[i].length() + 1][words[i].length() + 1];
      if (isConcat(words[i], set, 0, words[i].length(), 0, dp)) {
        ans.add(words[i]);
      }
    }
    return ans;
  }

  private boolean isConcat(String str, Set<String> words, int begin, int end, int count, Boolean[][] dp) {
    if (dp[begin][end] != null) {
      return dp[begin][end];
    }
    if (begin >= end) {
      return count > 1;
    }
    boolean ret;
    for (int i = begin; i <= end; i++) {
      String left = str.substring(begin, i);
      if (!left.isEmpty() && words.contains(left)) {
        ret = isConcat(str, words, i, end, count + 1, dp);
        if (ret && words.contains(left)) {
          dp[begin][end] = true;
          return true;
        }
      }
    }
    dp[begin][end] = false;
    return false;
  }

  public static void main(String args[]) {
    ConcatWords c = new ConcatWords();
    Set<String> set = new HashSet<>();
    String[] arr = new String[]{"cat", "dog", "catdog"};
    String[] arr2 = new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};

    System.out.println(c.findAllConcatenatedWordsInADict(arr2));
  }

}
