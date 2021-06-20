package com.leetcode.random5;

import java.util.HashSet;
import java.util.Set;

public class GrayBin {

  public boolean hasAllCodes(String s, int k) {

    Set<Integer> set = new HashSet<>();
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < k; i++) {
      b.append(s.charAt(i));
    }
    set.add(Integer.parseInt(b.toString(), 2));
    int min = 0, max = (int) Math.pow(2, k);
    for (int i = 1; i + k <= s.length(); i++) {
      b.deleteCharAt(0);
      b.append(s.charAt(i + k - 1));
      int val = Integer.parseInt(b.toString(), 2);
      if (val >= min && val <= max) {
        set.add(val);
      }
    }
    //System.out.println(set);
    return set.size() == max;
  }


  public static void main(String args[]) {
    GrayBin g = new GrayBin();
    System.out.println(g.hasAllCodes("0000000001011100", 4));
  }

}
