package com.binarysearch;

import java.util.HashMap;
import java.util.TreeSet;

public class SwapCharsOnce {

  public int solve(String s, String t) {
    int count = 0;
    HashMap<Character, TreeSet<Integer>> sMap = new HashMap();
    for (int i = 0; i < s.length(); i++) {
      TreeSet<Integer> set = sMap.getOrDefault(s.charAt(i), new TreeSet());
      set.add(i);
      sMap.put(s.charAt(i), set);
      if (s.charAt(i) != t.charAt(i)) {
        count++;
      }
    }
    if (count == 0) {
      return 0;
    }
    int ans = count;
    int val = ans;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != t.charAt(i)) {
        TreeSet<Integer> set = sMap.get(t.charAt(i));
        if (set != null) {
          int curr = i;
          while (true) {
            Integer high = set.higher(curr);
            if (high == null) {
              break;
            }
            if (t.charAt(high) == s.charAt(i)) {
              return ans - 2;
            }
            if (t.charAt(high) != s.charAt(high)) {
              val = Math.min(val, count - 1);
            }
            curr = high;

          }
          while (true) {
            Integer low = set.lower(curr);
            if (low == null) {
              break;
            }
            if (t.charAt(low) == s.charAt(i)) {
              return ans - 2;
            }
            if (t.charAt(low) != s.charAt(low)) {
              val = Math.min(val, count - 1);
            }
            curr = low;
          }

        }
      }
    }

    return Math.min(count, val);
  }

  public static void main(String args[]) {
    SwapCharsOnce s = new SwapCharsOnce();
    System.out.println(s.solve("abc", "abc"));
  }
}
