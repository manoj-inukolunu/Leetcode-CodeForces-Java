package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 7/12/20.
 */
public class GrayCode {

  public List<Integer> grayCode(int n) {

    StringBuffer buffer = new StringBuffer();
    for (int i = 1; i <= n; i++) {
      buffer.append("0");
    }

    List<Integer> ans = new ArrayList<>();
    recur(ans, buffer, (int) Math.pow(2, n), 0);
    return new ArrayList<>(ans);
  }


  private void recur(List<Integer> list, StringBuffer buffer, int count, int pos) {
    if (list.size() == count) {
      return;
    }
    for (int i = 0; i < buffer.length(); i++) {
      if (!list.contains(Integer.parseInt(buffer.toString(), 2))) {
        list.add(Integer.parseInt(buffer.toString(), 2));
      }
      Character character = buffer.charAt(i);
      if (character == '0') {
        buffer.setCharAt(i, '1');
      } else {
        buffer.setCharAt(i, '0');
      }
      if (!list.contains(Integer.parseInt(buffer.toString(), 2))) {
        recur(list, buffer, count, pos + 1);
      }
      buffer.setCharAt(i, character);
    }
  }


  public static void main(String args[]) {
    GrayCode g = new GrayCode();

    System.out.println(g.grayCode(3));
  }


}
