package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class B79 {

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

  static class Pair {

    int row;
    int col;

    public Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return row == pair.row && col == pair.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int k = scanner.nextInt();
    int t = scanner.nextInt();
    List<Integer> list = new ArrayList<>();
    while (k-- > 0) {
      int r = scanner.nextInt();
      int c = scanner.nextInt();
      list.add((r - 1) * m + c);
    }
    Collections.sort(list);
    String[] strings = new String[]{"Carrots", "Kiwis", "Grapes"};
    while (t-- > 0) {
      int r = scanner.nextInt();
      int c = scanner.nextInt();
      int val = (r - 1) * m + c;
      int idx = Collections.binarySearch(list, val);
      if (idx >= 0) {
        System.out.println("Waste");
        continue;
      }
      idx = -(idx + 1);
      if (idx == 0) {
        System.out.println(strings[((val - 1) % 3)]);
      } else {
        System.out.println(strings[((val - idx - 1) % 3)]);
      }
    }

  }
}
