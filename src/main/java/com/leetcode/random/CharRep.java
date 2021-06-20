package com.leetcode.random;

import java.util.HashSet;

public class CharRep {

  public int characterReplacement(String s, int k) {
    HashSet<Character> set = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      set.add(s.charAt(i));
    }
    int ans = Integer.MIN_VALUE;
    for (char ch : set) {
      int start = 0, end = 0, count = k, max = 0;
      while (start < s.length() && end < s.length()) {
        char curr = s.charAt(end);
        if (curr == ch) {
          end++;
          max = Math.max(end - start, max);
        } else if (count > 0) {
          end++;
          count--;
          max = Math.max(end - start, max);
        } else if (s.charAt(start) != ch) {
          start++;
          count++;
          max = Math.max(end - start, max);
        } else {
          start++;
          max = Math.max(end - start, max);
        }
      }
      ans = Math.max(ans, max);
    }
    return ans == Integer.MIN_VALUE ? 0 : ans;
  }

  public static void main(String args[]) {
    CharRep c = new CharRep();
    System.out.println(c.characterReplacement("AABABBA", 1));

  }

}
