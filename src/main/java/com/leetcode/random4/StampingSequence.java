package com.leetcode.random4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StampingSequence {

  public int[] movesToStamp(String stamp, String target) {
    List<Integer> ret = new ArrayList<>();
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < stamp.length(); i++) {
      b.append("?");
    }
    while (true) {
      int idx = target.indexOf(stamp);
      if (idx != -1) {
        target = target.replace(stamp, b);
        ret.add(idx);
      } else {

      }
    }
  }

  public static void main(String[] args) {
    StampingSequence s = new StampingSequence();
    System.out.println(Arrays.toString(s.movesToStamp("abca", "aabcaca")));
  }

}
