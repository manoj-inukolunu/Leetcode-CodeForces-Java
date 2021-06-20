package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B236 {

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
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    int c = scanner.nextInt();
    int mod = 1073741824;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 1; i <= a; i++) {
      for (int j = 1; j <= b; j++) {
        for (int k = 1; k <= c; k++) {
          map.put(i * j * k, map.getOrDefault(i * j * k, 0) + 1);
        }
      }
    }
    long divCount = 0;
    for (int num : map.keySet()) {
      Set<Integer> set = new HashSet<>();
      for (int i = 1; i <= Math.sqrt(num); i++) {
        if (num % i == 0) {
          set.add(num / i);
          set.add(i);
        }
      }
      divCount += ((long) map.get(num) * set.size());
      divCount%=mod;
    }
    System.out.println(divCount);
  }
}
