package com.leetcode.random5;

import java.util.HashSet;

public class BinarySubStrings {

  public boolean queryString(String S, int N) {

    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < S.length(); i++) {
      int start = i + 1, end = start + 30;
      for (int j = start; j < end; j++) {
        int val = Integer.parseInt(S.substring(i, j), 2);
        if (val >= 1 && val <= N) {
          set.add(val);
        }
      }
    }
    return set.size() == N;
  }

  public static void main(String args[]) {
    BinarySubStrings b = new BinarySubStrings();
    System.out.println(b.queryString("0110", 4));
  }

}
