package com.leetcode.random1;

import java.util.HashMap;

public class MinWindow {


  String ans = "";

  public String minWindow(String s, String t) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
    }
    int low = t.length(), high = s.length();
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (found(s, map, mid)) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return ans;
  }

  private boolean found(String s, HashMap<Character, Integer> tMap, int mid) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < mid; i++) {
      map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }
    if (map.equals(tMap)) {
      return true;
    }
    for (int i = 1; i + mid < s.length(); i++) {
      Integer count = map.get(s.charAt(i - 1));
      if (count == null) {
        map.put(s.charAt(i - 1), 1);
      } else if (count == 1) {
        map.remove(s.charAt(i - 1));
      } else {
        map.put(s.charAt(i - 1), count - 1);
      }
      map.put(s.charAt(i + mid), map.getOrDefault(s.charAt(i + mid), 0) + 1);
      if (map.equals(tMap)) {
        ans = s.substring(i, i + mid);
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    MinWindow m = new MinWindow();
    System.out.println(m.minWindow("ADOBECODEBANC", "ABC"));
  }

}
