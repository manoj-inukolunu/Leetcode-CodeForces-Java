package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class B514 {

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

  static class Equation {

    //ax+by=c;
    int a, b, c;

    public Equation(int x1, int y1, int x2, int y2) {
      int a = y2 - y1;
      int b = x1 - x2;
      int c = a * x1 + b * y1;
    }

    public Equation(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    public boolean isPointOnLine(int x, int y) {
      return a * x + b * y == c;
    }
  }


  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    int n = scanner.nextInt();
    int x = scanner.nextInt();
    int y = scanner.nextInt();
    List<int[]> list = new ArrayList<>();
    while (n-- > 0) {
      list.add(new int[]{scanner.nextInt(), scanner.nextInt()});
    }
    int count = 0;
    Set<Integer> used = new HashSet<>();
    for (int i = 0; i < list.size(); i++) {
      if (used.contains(i)) {
        continue;
      }
      int[] curr = list.get(i);
      int a = curr[1] - y;
      int b = x - curr[0];
      int c = a * x + b * y;
      Equation eq = new Equation(a, b, c);
      for (int j = i + 1; j < list.size(); j++) {
        if (used.contains(j)) {
          continue;
        }
        if (eq.isPointOnLine(list.get(j)[0], list.get(j)[1])) {
          used.add(j);
        }
      }
      count++;
    }
    System.out.println(count);
  }
}
