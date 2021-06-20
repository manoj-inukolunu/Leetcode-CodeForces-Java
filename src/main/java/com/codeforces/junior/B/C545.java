package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C545 {

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

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();

    int n = scanner.nextInt();
    int[] pos = new int[n];
    int[] heights = new int[n];
    int i = 0;
    while (n-- > 0) {
      pos[i] = scanner.nextInt();
      heights[i] = scanner.nextInt();
      i++;
    }
    int[][] dp = new int[pos.length + 1][3];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    System.out.println(maxFell(pos, heights, 0, 0, dp));
  }

  private static int maxFell(int[] pos, int[] heights, int idx, int prev, int[][] dp) {
    if (idx == heights.length - 1) {
      return 1;
    }
    int max;
    if (dp[idx][prev] != -1) {
      return dp[idx][prev];
    }
    if (idx == 0) {
      max = 1 + maxFell(pos, heights, idx + 1, 1, dp);
    } else {
      if (prev == 0 || prev == 1) {
        if (pos[idx] - heights[idx] > pos[idx - 1]) {
          max = 1 + maxFell(pos, heights, idx + 1, 1, dp);
        } else if (pos[idx] + heights[idx] < pos[idx + 1]) {
          max = Math.max(1 + maxFell(pos, heights, idx + 1, 2, dp), maxFell(pos, heights, idx + 1, 0, dp));
        } else {
          max = maxFell(pos, heights, idx + 1, 0, dp);
        }
      } else {
        if (pos[idx - 1] + heights[idx - 1] < pos[idx] - heights[idx]) {
          max = 1 + maxFell(pos, heights, idx + 1, 1, dp);
        } else if (pos[idx] + heights[idx] < pos[idx + 1]) {
          max = Math.max(1 + maxFell(pos, heights, idx + 1, 2, dp), maxFell(pos, heights, idx + 1, 0, dp));
        } else {
          max = maxFell(pos, heights, idx + 1, 0, dp);
        }
      }
    }
    dp[idx][prev] = max;
    return max;
  }
}
