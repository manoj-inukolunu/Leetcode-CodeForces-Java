package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class B520 {

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
    int m = scanner.nextInt();
    System.out.println(dfs(n, m));
  }

  private static int dfs(int n, int m) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{n, 0});
    int ans = Integer.MAX_VALUE;
    Set<Integer> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      if (curr[0] <= 0) {
        continue;
      }
      if (curr[0] > m) {
        ans = Math.min(ans, curr[1] + curr[0] - m);
        continue;
      }
      if (curr[0] == m) {
        ans = Math.min(ans, curr[1]);
        return ans;
      }
      if (!visited.contains(curr[0])) {
        visited.add(curr[0]);
        queue.add(new int[]{curr[0] - 1, curr[1] + 1});
        queue.add(new int[]{curr[0] * 2, curr[1] + 1});
      }
    }
    return ans;
  }
}
