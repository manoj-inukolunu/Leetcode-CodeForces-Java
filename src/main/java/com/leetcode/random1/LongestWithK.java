package com.leetcode.random1;

import java.util.Arrays;

public class LongestWithK {

  public int longestSubstring(String s, int k) {
    int ans = 0;
    int[] freq = new int[26];
    for (int i = 0; i < s.length(); i++) {
      Arrays.fill(freq, 0);
      for (int j = i + 1; j < s.length(); j++) {
        freq[s.charAt(j) - 'a']++;
        if (atLeastK(freq, k)) {
          ans = Math.max(ans, j - i + 1);
        }
      }
    }

    return ans;
  }

  private boolean atLeastK(int freq[], int k) {
    for (int i = 0; i < freq.length; i++) {
      if (freq[i] != 0 && freq[i] < k) {
        return false;
      }
    }

    return true;
  }


}
