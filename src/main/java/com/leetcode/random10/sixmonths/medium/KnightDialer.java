package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KnightDialer {

  private void buildReachableFrom(HashMap<Integer, List<Integer>> map) {
    for (int i = 0; i <= 9; i++) {
      map.put(i, new ArrayList<>());
    }
    map.get(0).add(4);
    map.get(0).add(6);
    map.get(1).add(8);
    map.get(1).add(6);
    map.get(2).add(7);
    map.get(2).add(9);
    map.get(3).add(8);
    map.get(3).add(4);
    map.get(4).add(9);
    map.get(4).add(3);
    map.get(4).add(0);
    map.get(6).add(1);
    map.get(6).add(7);
    map.get(6).add(0);
    map.get(7).add(2);
    map.get(7).add(6);
    map.get(8).add(1);
    map.get(8).add(3);
    map.get(9).add(2);
    map.get(9).add(4);
  }


  public int knightDialer(int n) {
    int mod = (int) (Math.pow(10, 9) + 7);
    if (n == 1) {
      return 10;
    }
    HashMap<Integer, List<Integer>> reachableFrom = new HashMap<>();
    buildReachableFrom(reachableFrom);
    int dp[][] = new int[10][n + 1];
    int sum = 0;
    for (int i = 0; i <= 9; i++) {
      dp[i][1] = 1;
    }

    for (int j = 2; j <= n; j++) {
      for (int i = 0; i <= 9; i++) {
        for (int k : reachableFrom.get(i)) {
          dp[i][j] = (dp[i][j] % mod) + (dp[k][j - 1] % mod);
        }
        if (j == n) {
          sum = (sum % mod) + (dp[i][j] % mod);
        }
      }
    }
    return sum % mod;
  }

  public static void main(String args[]) {
    KnightDialer k = new KnightDialer();
    System.out.println(k.knightDialer(5000));
  }

}
