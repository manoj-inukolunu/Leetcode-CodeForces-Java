package com.leetcode.random5;

import java.util.HashMap;

public class NumSubStrAll3 {


  public int numberOfSubstrings(String s) {
    int count = 0, start = 0, end = 0;
    HashMap<Character, Integer> map = new HashMap<>();
    while (end < s.length()) {
      while (map.size() < 3) {
        map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);
        end++;
      }
      while (map.size() == 3) {
        count += (s.length() - end + 1);
        map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
        if (map.get(s.charAt(start)) <= 0) {
          map.remove(s.charAt(start));
        }
        start++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    NumSubStrAll3 n = new NumSubStrAll3();
    System.out.println(n.numberOfSubstrings("abcabc"));
  }

}
