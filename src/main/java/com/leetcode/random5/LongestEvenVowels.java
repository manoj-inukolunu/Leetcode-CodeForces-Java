package com.leetcode.random5;

import java.util.HashMap;

public class LongestEvenVowels {

  public int findTheLongestSubstring(String s) {
    int ans = 0;
    String vowels = "aeiou";
    int[] mask = new int[]{16, 8, 4, 2, 1};
    HashMap<Integer, Integer> seen = new HashMap<>();
    int state = 0;
    seen.put(state, -1);
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      int idx = vowels.indexOf(ch);
      if (idx != -1) {
        state = state ^ mask[idx];
        if (seen.containsKey(state)) {
          ans = Math.max(i - seen.get(state), ans);
        } else {
          seen.put(state, i);
        }
      } else {
        if (seen.containsKey(state)) {
          ans = Math.max(i - seen.get(state), ans);
        }
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    LongestEvenVowels l = new LongestEvenVowels();
    System.out.println(l.findTheLongestSubstring("bcbcbc"));
  }

}
