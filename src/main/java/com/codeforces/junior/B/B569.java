package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class B569 {

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

    long nextLong() {
      return Long.parseLong(next());
    }
  }

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    int p = scanner.nextInt();
    int q = scanner.nextInt();
    int l = scanner.nextInt();
    int r = scanner.nextInt();
    List<int[]> list = new ArrayList<>();
    while (p-- > 0) {
      int f = scanner.nextInt();
      int s = scanner.nextInt();
      list.add(new int[]{f, s});
    }
    Set<Integer> set = new HashSet<>();
    while (q-- > 0) {
      int f = scanner.nextInt();
      int s = scanner.nextInt();
      for (int[] interval : list) {
        for (int i = l; i <= r; i++) {
          if (Math.max(f + i, interval[0]) <= Math.min(s + i, interval[1])) {
            set.add(i);
          }
        }
      }
    }
    System.out.println(set.size());
  }
}
