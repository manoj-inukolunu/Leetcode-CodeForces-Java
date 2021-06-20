package com.leetcode.random6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SteppingNumbers {

  Set<Integer> set = new HashSet<>();

  public List<Integer> countSteppingNumbers(int low, int high) {
    int first = Character.getNumericValue((low + "").charAt(0));
    for (int i = first; i <= 9; i++) {
      dfs(i, low, high);
    }
    List<Integer> list = new ArrayList<>(set);
    Collections.sort(list);
    return list;
  }

  private void dfs(long num, int low, int high) {
    if (num > high) {
      return;
    }
    if (num >= low && num <= high) {
      set.add((int) num);
    }
    if (num < 10) {
      if (num + 1 < 10) {
        dfs(num * 10 + (num + 1), low, high);
      }
      if (num != 0) {
        dfs(num * 10 + (num - 1), low, high);
      }
    } else {
      int lastDigit = (int) (num % 10);
      if (lastDigit != 9) {
        dfs(num * 10 + (lastDigit + 1), low, high);
      }
      if (lastDigit != 0) {
        dfs(num * 10 + (lastDigit - 1), low, high);
      }
    }
  }

  public static void main(String args[]) {
    SteppingNumbers s = new SteppingNumbers();
    System.out.println(s.countSteppingNumbers(709852790, 1686392249));
  }
}
