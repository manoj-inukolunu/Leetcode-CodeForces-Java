package com.codeforces.junior.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class A899 {

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

  static int test;

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    test++;
    PrintWriter out = new PrintWriter(System.out);
    int n = scanner.nextInt();
    int[] arr = scanner.readArray(n);
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : arr) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    Integer twos = map.get(2);
    if (twos == null) {
      out.println(map.get(1) / 3);
    } else {
      Integer ones = map.get(1);
      if (ones == null) {
        out.println(0);
      } else if (twos >= ones) {
        out.println(ones);
      } else {
        int count = twos;
        ones -= count;
        count += (ones / 3);
        out.println(count);
      }
    }
    out.close();
  }
}
