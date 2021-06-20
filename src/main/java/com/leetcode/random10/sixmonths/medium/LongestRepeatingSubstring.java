package com.leetcode.random10.sixmonths.medium;

import java.util.HashSet;

public class LongestRepeatingSubstring {

  public int longestRepeatingSubstring(String S) {
    int low = 0, high = S.length();
    int mid, len = 0;
    while (low <= high) {
      mid = low + ((high - low) / 2);

      if (mid != 0 && search(S, mid)) {
        len = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return len;
  }

  public boolean search(String str, int len) {
    HashSet<String> set = new HashSet<>();
    for (int i = 0; i + len <= str.length(); i++) {
      String sub = str.substring(i, i + len);
      if (set.contains(sub)) {
        return true;
      } else {
        set.add(sub);
      }
    }
    return false;
  }

  public static void main(String args[]) {
    LongestRepeatingSubstring l = new LongestRepeatingSubstring();

    System.out.println(l.longestRepeatingSubstring("aaaaa"));

    System.out.println(l.search("aaaaa", 4));

  }
}
