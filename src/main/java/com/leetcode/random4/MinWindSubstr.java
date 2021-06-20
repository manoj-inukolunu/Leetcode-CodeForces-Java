package com.leetcode.random4;

import java.util.HashMap;

public class MinWindSubstr {

  class Pair {

    boolean found;
    String str;

    public Pair(boolean found, String str) {
      this.found = found;
      this.str = str;
    }
  }

  public String minWindow(String s, String t) {

    HashMap<Character, Integer> tMap = new HashMap<>();
    for (char ch : t.toCharArray()) {
      tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
    }
    int low = t.length(), high = s.length();
    String ans = "";
    while (low <= high) {
      int mid = low + (high - low) / 2;
      Pair p = exists(mid, s, tMap);
      if (p.found) {
        ans = p.str;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    Pair p = exists(t.length(), s, tMap);
    if (p.found) {
      return p.str;
    }
    return ans;
  }

  private Pair exists(int mid, String str, HashMap<Character, Integer> tMap) {
    HashMap<Character, Integer> sMap = new HashMap<>();
    for (int i = 0; i < mid; i++) {
      sMap.put(str.charAt(i), sMap.getOrDefault(str.charAt(i), 0) + 1);
    }
    if (valid(sMap, tMap)) {
      return new Pair(true, str.substring(0, mid));
    }
    String ret = "";
    boolean found = false;
    for (int i = 1; i + mid <= str.length(); i++) {
      char c = str.charAt(i - 1);
      sMap.put(c, sMap.get(c) - 1);
      if (sMap.get(c) <= 0) {
        sMap.remove(c);
      }
      char n = str.charAt(i + mid - 1);
      sMap.put(n, sMap.getOrDefault(n, 0) + 1);
      if (valid(sMap, tMap)) {
        ret = str.substring(i, i + mid);
        found = true;
      }
    }
    return new Pair(found, ret);
  }

  private boolean valid(HashMap<Character, Integer> sMap, HashMap<Character, Integer> tMap) {
    for (char ch : tMap.keySet()) {
      if (!(sMap.containsKey(ch) && sMap.get(ch) >= tMap.get(ch))) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    MinWindSubstr m = new MinWindSubstr();
    System.out.println(m.minWindow("abc", "ac"));
  }
}
