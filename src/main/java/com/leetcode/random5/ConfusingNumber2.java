package com.leetcode.random5;

import java.util.HashMap;

public class ConfusingNumber2 {


  HashMap<Integer, Integer> map = new HashMap<>();
  int count = 0;

  public int confusingNumberII(int N) {
    map.put(0, 0);
    map.put(1, 1);
    map.put(6, 9);
    map.put(8, 8);
    map.put(9, 6);
    dfs(0, N);
    return count;
  }

  private void dfs(long num, int n) {
    if (valid(num)) {
      count++;
    }
    for (int key : map.keySet()) {
      long next = num * 10 + key;
      if (next <= n && next != 0) {
        dfs(next, n);
      }
    }

  }

  private boolean valid(long n) {
    long src = n;
    long res = 0;
    while (n > 0) {
      res = res * 10 + map.get((int) n % 10);
      n /= 10;
    }
    return res != src;
  }

  public static void main(String args[]) {
    ConfusingNumber2 c = new ConfusingNumber2();
    int max = (int) Math.pow(10, 9);
    System.out.println(Math.pow(3, 15));
//    System.out.println(c.confusingNumberII(max));
  }

}
