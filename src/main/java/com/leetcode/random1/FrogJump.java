package com.leetcode.random1;

import java.util.HashSet;
import java.util.Set;

public class FrogJump {


  public boolean canCross(int[] stones) {
    if (stones[1] != 1) {
      return false;
    }
    Set<Integer> posSet = new HashSet<>();
    int last = 0;
    for (int num : stones) {
      posSet.add(num);
      last = num;
    }
    return jump(posSet, 1, 1, last, new HashSet<String>());
  }

  private boolean jump(Set<Integer> posSet, int pos, int k, int last, HashSet<String> visited) {
    String key = pos + "|" + k;
    if (!posSet.contains(pos) || visited.contains(key)) {
      return false;
    }
    visited.add(key);
    if (pos == last) {
      return true;
    }
    return jump(posSet, pos + (k - 1), k - 1, last, visited) ||
        jump(posSet, pos + k, k, last, visited) ||
        jump(posSet, pos + k + 1, k + 1, last, visited);

  }

  public static void main(String args[]) {
    FrogJump f = new FrogJump();
    int[] arr = new int[]{0, 1, 2, 3, 4, 8, 9, 11};
    System.out.println(f.canCross(arr));
  }

}
