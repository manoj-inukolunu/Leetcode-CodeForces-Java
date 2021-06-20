package com.leetcode.string;

import java.util.HashMap;

/**
 * @author manoji on 6/21/20.
 */
public class IsAlienSorted {

  HashMap<Character, Integer> map = new HashMap<>();

  public boolean isAlienSorted(String[] words, String order) {

    char[] ord = order.toCharArray();
    for (int i = 0; i < ord.length; i++) {
      map.put(ord[i], i);
    }

    for (int i = 1; i < words.length; i++) {
      if (isGreaterThan(words[i], words[i - 1])) {
        continue;
      }
      return false;
    }

    return true;
  }

  private boolean isGreaterThan(String word1, String word2) {
    char[] w1 = word1.toCharArray();
    char[] w2 = word2.toCharArray();
    int length = Math.min(w1.length, w2.length);
    for (int i = 0; i < length; i++) {
      if (w1[i] != w2[i]) {
        return map.get(w1) - map.get(w2) > 0;
      }
    }
    if (w1.length != w2.length) {
      return w1.length - w2.length > 0;
    }
    return true;
  }

}
