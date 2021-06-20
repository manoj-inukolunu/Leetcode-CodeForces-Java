package com.leetcode.random;

import java.util.HashSet;
import java.util.Set;

public class MaxProdOfWordLengths {

  public int maxProduct(String[] words) {

    Set<Character>[] set = new HashSet[words.length];

    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      for (char ch : word.toCharArray()) {
        set[i].add(ch);
      }
    }

    int max = Integer.MIN_VALUE;

    for (int i = 0; i < set.length; i++) {
      for (int j = i + 1; j < set.length; j++) {
        if (check(set[i], set[j])) {
          max = Math.max(max, words[i].length() * words[j].length());
        }
      }
    }
    return max;

  }

  private boolean check(Set<Character> a, Set<Character> b) {
    for (char ch : a) {
      if (b.contains(ch)) {
        return false;
      }
    }
    return true;
  }


}
