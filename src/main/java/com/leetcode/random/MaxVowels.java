package com.leetcode.random;

import java.util.HashMap;
import java.util.HashSet;

public class MaxVowels {

  public int maxVowels(String s, int k) {
    HashSet<Character> set = new HashSet<>();
    set.add('a');
    set.add('e');
    set.add('i');
    set.add('o');
    set.add('u');
    int max = Integer.MIN_VALUE, curr = 0;
    for (int i = 0; i < k; i++) {
      if (set.contains(s.charAt(i))) {
        curr++;
      }
    }
    max = Math.max(max, curr);
    for (int i = 1; i + k <= s.length(); i++) {
      Character next = s.charAt(i + k - 1);
      Character last = s.charAt(i - 1);
      if ((!set.contains(next) && !set.contains(last)) || (set.contains(next) && set.contains(last))) {
        max = Math.max(curr, max);
      } else if (set.contains(next) && !set.contains(last)) {
        max = Math.max(curr + 1, max);
        curr++;
      } else if (!set.contains(next) && set.contains(last)) {
        max = Math.max(curr - 1, max);
        curr--;
      }
    }
    return max == Integer.MAX_VALUE ? 0 : max;
  }

  public static void main(String args[]) {
    MaxVowels m = new MaxVowels();
    System.out.println(m.maxVowels("ibpbhixfiouhdljnjfflpapptrxgcomvnb", 33));
  }


}
