package com.leetcode.random4;

import java.util.HashMap;

public class MaxFreqSubStr {

  public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
    int ans = 0;
    HashMap<String, Integer> map = new HashMap<>();
    for (int i = minSize; i <= maxSize; i++) {
      count(i, s, maxLetters, map);
    }

    for (String key : map.keySet()) {
      System.out.println(key + " " + map.get(key));
      if (map.get(key) >= 1) {
        ans = Math.max(ans, map.get(key));
      }
    }
    return ans;
  }

  private void count(int len, String str, int maxL, HashMap<String, Integer> map) {
    StringBuffer b = new StringBuffer();
    HashMap<Character, Integer> sMap = new HashMap<>();
    for (int i = 0; i < len; i++) {
      sMap.put(str.charAt(i), sMap.getOrDefault(str.charAt(i), 0) + 1);
      b.append(str.charAt(i));
    }
    if (sMap.size() <= maxL) {
      String s = b.toString();
      map.put(s, map.getOrDefault(s, 0) + 1);
    }
    for (int i = 1; i + len <= str.length(); i++) {
      b.deleteCharAt(0);
      b.append(str.charAt(i + len - 1));
      sMap.put(str.charAt(i - 1), sMap.get(str.charAt(i - 1)) - 1);
      if (sMap.get(str.charAt(i - 1)) == 0) {
        sMap.remove(str.charAt(i - 1));
      }
      sMap.put(str.charAt(i + len - 1), sMap.getOrDefault(str.charAt(i + len - 1), 0) + 1);
      String s = b.toString();
      if (sMap.size() <= maxL && map.containsKey(s)) {
        map.put(s, map.get(s) + 1);
      } else if (sMap.size() <= maxL) {
        map.put(s, 1);
      }
    }
  }

  public static void main(String args[]) {
    MaxFreqSubStr m = new MaxFreqSubStr();
    System.out.println(m.maxFreq("bccaabac", 2, 2, 2));
  }

}
