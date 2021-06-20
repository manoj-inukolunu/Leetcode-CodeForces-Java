package com.leetcode.slidingwindow;

import java.util.HashMap;

/**
 * @author manoji on 7/4/20.
 */
public class LongestDuplicateSubstring {

  public String longestDupSubstring(String S) {
    HashMap<String, Integer> map = new HashMap<>();

    String max = "";
    for (int i = 0; i < S.length(); i++) {
      for (int j = i + 1; j < S.length(); j++) {
        String str = S.substring(i, j);
        if (map.containsKey(str) && str.length() > max.length()) {
          max = str;
        } else {
          map.put(str, 1);
        }
      }
    }
    return max;

  }

}
