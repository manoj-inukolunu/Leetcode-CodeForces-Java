package com.leetcode.hard;

import java.util.HashMap;

public class LC1542 {

  public int longestAwesome(String s) {
    HashMap<String, Integer> map = new HashMap<>();
    int[] arr = new int[10];
    int best = 1;
    map.put("0000000000", -1);
    for (int i = 0; i < s.length(); i++) {
      int num = Character.getNumericValue(s.charAt(i));
      arr[num]++;
      arr[num] %= 2;
      StringBuffer str = new StringBuffer("0000000000");
      for (int j = 0; j < arr.length; j++) {
        if (arr[j] == 1) {
          str.setCharAt(j, '1');
        }
      }
      boolean add = false;
      if (map.containsKey(str.toString())) {
        int prevIdx = map.get(str.toString());
        best = Math.max(best, i - prevIdx);
      } else {

        add = true;
      }
      for (int j = 0; j < arr.length; j++) {
        if (str.charAt(j) == '0') {
          str.setCharAt(j, '1');
          if (map.containsKey(str.toString())) {
            best = Math.max(best, i - map.get(str.toString()));
          }
          str.setCharAt(j, '0');
        } else {
          str.setCharAt(j, '0');
          if (map.containsKey(str.toString())) {
            best = Math.max(best, i - map.get(str.toString()));
          }
          str.setCharAt(j, '1');
        }

      }
      if (add) {
        map.put(str.toString(), i);
      }
    }
    return best;
  }

  public static void main(String[] args) {
    LC1542 l = new LC1542();
    System.out.println(l.longestAwesome("213123"));
  }
}
