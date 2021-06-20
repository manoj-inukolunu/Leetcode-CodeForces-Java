package com.leetcode.random;

import java.util.HashMap;
import java.util.HashSet;
import org.checkerframework.checker.units.qual.K;

public class KLengthRep {

  public int numKLenSubstrNoRepeats(String S, int K) {
    if (K > S.length()) {
      return 0;
    }

    HashMap<Character, Integer> map = new HashMap<>();
    int count = 0;
    for (int i = 0; i < K; i++) {
      if (map.containsKey(S.charAt(i))) {
        map.put(S.charAt(i), map.get(S.charAt(i)) + 1);
      } else {
        map.put(S.charAt(i), 1);
      }
    }
    if (map.size() == K) {
      count++;
    }
    for (int i = 1; i + K <= S.length(); i++) {
      Integer prevCount = map.get(S.charAt(i - 1));
      if (prevCount - 1 == 0) {
        map.remove(S.charAt(i - 1));
      } else {
        map.put(S.charAt(i - 1), prevCount - 1);
      }
      map.put(S.charAt(i + K - 1), map.getOrDefault(S.charAt(i + K - 1), 0) + 1);
      if (map.size() == K) {
        count++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    KLengthRep k = new KLengthRep();
    System.out.println(k.numKLenSubstrNoRepeats("home", 5));
  }
}
