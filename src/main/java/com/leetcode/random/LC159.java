package com.leetcode.random;

import java.util.HashMap;

public class LC159 {

  public int lengthOfLongestSubstringTwoDistinct(String s) {
    HashMap<Character, Integer> map = new HashMap<>();
    int i = 0;
    int ans = Integer.MIN_VALUE;
    int left = 0, right = 0;
    while (i < s.length()) {
      Character ch = s.charAt(i);
      if (map.isEmpty() || map.containsKey(ch)) {
        map.put(ch, map.getOrDefault(ch, 0) + 1);
        right++;
      } else if (map.size() < 2) {
        map.put(ch, map.getOrDefault(ch, 0) + 1);
        right++;
      } else if (map.size() >= 2) {
        while (map.size() >= 2) {
          int count = map.get(s.charAt(left));
          if (count - 1 == 0) {
            map.remove(s.charAt(left));
          } else {
            map.put(s.charAt(left), count - 1);
          }
          left++;
        }
        map.put(ch, map.getOrDefault(ch, 0) + 1);
        right++;
      }
      ans = Math.max(ans, right - left);
      i++;
    }
    return ans;
  }

  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    HashMap<Character, Integer> map = new HashMap<>();
    int i = 0;
    int ans = Integer.MIN_VALUE;
    int left = 0, right = 0;
    while (i < s.length()) {
      Character ch = s.charAt(i);
      if (map.isEmpty() || map.containsKey(ch)) {
        map.put(ch, map.getOrDefault(ch, 0) + 1);
        right++;
      } else if (map.size() < k) {
        map.put(ch, map.getOrDefault(ch, 0) + 1);
        right++;
      } else if (map.size() >= k) {
        while (map.size() >= k) {
          int count = map.get(s.charAt(left));
          if (count - 1 == 0) {
            map.remove(s.charAt(left));
          } else {
            map.put(s.charAt(left), count - 1);
          }
          left++;
        }
        map.put(ch, map.getOrDefault(ch, 0) + 1);
        right++;
      }
      ans = Math.max(ans, right - left);
      i++;
    }
    return ans;
  }

  public static void main(String args[]) {
    LC159 l = new LC159();
    System.out.println(l.lengthOfLongestSubstringTwoDistinct("eceba"));
  }

}
