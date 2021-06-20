package com.leetcode.random4;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

  public String getPermutation(int n, int k) {
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      list.add(i);
    }
    return permute(list, k);
  }

  private int fact(int k) {
    if (k == 1) {
      return 1;
    }
    return k * fact(k - 1);
  }

  private String permute(List<Integer> list, int k) {
    if (k == 1) {
      StringBuffer b = new StringBuffer();
      for (int i = 0; i < list.size(); i++) {
        b.append(list.get(i));
      }
      return b.toString();
    }
    if (list.size() == 1) {
      return list.iterator().next() + "";
    }
    int blockSize = fact(list.size() - 1);
    int num = (int) Math.ceil((double) k / blockSize);
    int val = list.get(num - 1);
    list.remove(num - 1);
    return val + "" + permute(list, k - (num - 1) * blockSize);
  }

  public static void main(String args[]) {
    PermutationSequence p = new PermutationSequence();
    System.out.println(p.getPermutation(3, 3));
  }

}
