package com.leetcode.random4;

import java.util.HashMap;

public class MinChars {

  public int op1(String a, String b) {
    int ans = Integer.MAX_VALUE;
    for (char ch = 'b'; ch <= 'z'; ch++) {
      int res = 0;
      for (char c : a.toCharArray()) {
        if (c >= ch) {
          res++;
        }
      }
      for (char c : b.toCharArray()) {
        if (c < ch) {
          res++;
        }
      }
      ans = Math.min(ans, res);
    }
    return ans;
  }


  public int minCharacters(String a, String b) {
    int ans = Math.min(op1(a, b), op1(b, a));
    HashMap<Character, Integer> aMap = new HashMap<>();
    for (char ch : a.toCharArray()) {
      aMap.put(ch, aMap.getOrDefault(ch, 0) + 1);
    }
    HashMap<Character, Integer> bMap = new HashMap<>();
    for (char ch : b.toCharArray()) {
      bMap.put(ch, bMap.getOrDefault(ch, 0) + 1);
    }
    for (char ch = 'a'; ch <= 'z'; ch++) {
      int count = 0;
      if (!aMap.containsKey(ch)) {
        count += a.length();
      } else {
        count += a.length() - aMap.get(ch);
      }
      if (!bMap.containsKey(ch)) {
        count += b.length();
      } else {
        count += b.length() - bMap.get(ch);
      }
      ans = Math.min(ans, count);
    }
    return ans;
  }

  public static void main(String args[]) {
    MinChars m = new MinChars();
    System.out.println(m.minCharacters("bddae", "abbb"));
  }

}
