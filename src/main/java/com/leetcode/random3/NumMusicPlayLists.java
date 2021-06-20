package com.leetcode.random3;

import java.util.Arrays;

public class NumMusicPlayLists {

  int mod = (int) (Math.pow(10, 9) + 7);

  public int numMusicPlaylists(int N, int L, int K) {
    int[][] dp = new int[L + 1][N + 1];
    for (int[] r : dp) {
      Arrays.fill(r, -1);
    }
    return solve(0, 0, K, N, L, dp);
  }

  private int solve(int currLen, int usedSongs, int K, int total, int len, int[][] dp) {
    if (currLen == len) {
      return (usedSongs == total) ? 1 : 0;
    }

    if (dp[currLen][usedSongs] != -1) {
      return dp[currLen][usedSongs];
    }

    int ans = (solve(currLen + 1, usedSongs, K, total, len, dp) * Math.max(usedSongs - K, 0)) % mod;
    ans += (solve(currLen + 1, usedSongs + 1, K, total, len, dp) * (total - usedSongs)) % mod;
    dp[currLen][usedSongs] = ans % mod;
    return dp[currLen][usedSongs];
  }


  public static void main(String args[]) {
    NumMusicPlayLists n = new NumMusicPlayLists();
    System.out.println(n.numMusicPlaylists(3, 3, 1));
  }

}
