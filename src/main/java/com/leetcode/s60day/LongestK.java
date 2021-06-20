package com.leetcode.s60day;

import java.util.HashMap;

public class LongestK {

  public int lengthOfLongestSubstring(String s) {
    HashMap<Character, Integer> map = new HashMap<>();
    int start = 0, end = 0;
    int ans = 0;
    while (end < s.length()) {
      char ch = s.charAt(end);
      if (!map.containsKey(ch)) {
        map.put(ch, end);
        end++;
        ans = Math.max(ans, end - start);
      } else {
        start = map.get(ch) + 1;
        end = map.get(ch) + 1;
        map.clear();
        ans = Math.max(ans, end - start);
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    LongestK l = new LongestK();
    System.out.println(l.lengthOfLongestSubstring("abba"));
  }

}
