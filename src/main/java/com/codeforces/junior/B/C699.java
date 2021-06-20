package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C699 {

  static class FastScanner {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    int[] readArray(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextInt();
      }
      return a;
    }

    Integer[] readArrayInt(int n) {
      Integer[] a = new Integer[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextInt();
      }
      return a;
    }

    long nextLong() {
      return Long.parseLong(next());
    }
  }

  static int ans = Integer.MAX_VALUE;

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();

    int days = scanner.nextInt();
    int[] arr = scanner.readArray(days);
    int[][] dp = new int[days + 1][3];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    System.out.println(dfs(arr, 0, -1, dp));
  }

  private static int dfs(int[] arr, int idx, int prev, int[][] dp) {
    if (idx >= arr.length) {
      return 0;
    }
    if (prev != -1 && dp[idx][prev] != -1) {
      return dp[idx][prev];
    }
    int val = 0;
    switch (arr[idx]) {
      case 0:
        val = 1 + dfs(arr, idx + 1, 0, dp);
        break;
      case 1:
        if (prev != 1) {
          val = Math.min(dfs(arr, idx + 1, 1, dp), 1 + dfs(arr, idx + 1, 0, dp));
        } else {
          val = 1 + dfs(arr, idx + 1, 0, dp);
        }
        break;
      case 2:
        if (prev != 2) {
          val = Math.min(dfs(arr, idx + 1, 2, dp), 1 + dfs(arr, idx + 1, 0, dp));
        } else {
          val = 1 + dfs(arr, idx + 1, 0, dp);
        }
        break;
      case 3:
        if (prev == -1) {
          val = Math.min(1 + dfs(arr, idx + 1, 0, dp), Math.min(dfs(arr, idx + 1, 1, dp), dfs(arr, idx + 1, 2, dp)));
        } else if (prev != 1 && prev != 2) {
          val = Math.min(1 + dfs(arr, idx + 1, 0, dp), Math.min(dfs(arr, idx + 1, 1, dp), dfs(arr, idx + 1, 2, dp)));
        } else if (prev != 1) {
          val = Math.min(1 + dfs(arr, idx + 1, 0, dp), dfs(arr, idx + 1, 1, dp));
        } else {
          val = Math.min(1 + dfs(arr, idx + 1, 0, dp), dfs(arr, idx + 1, 2, dp));
        }
    }
    if (prev != -1) {
      dp[idx][prev] = val;
    }
    return val;
  }


}
