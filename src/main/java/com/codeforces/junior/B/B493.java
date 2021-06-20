package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.StringTokenizer;

public class B493 {

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

  static class Pair {

    int first;
    int second;

    public Pair(int first, int second) {
      this.first = first;
      this.second = second;
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
      return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
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

  private static void buildGraph(HashMap<Integer, Set<Integer>> map, int first, int second) {
    Set<Integer> set = map.getOrDefault(first, new HashSet<>());
    set.add(second);
    map.put(first, set);
    set = map.getOrDefault(second, new HashSet<>());
    set.add(first);
  }

  public static void main(String args[]) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      FastScanner scanner = new FastScanner();
      int n = scanner.nextInt();
      List<Integer> a = new ArrayList<>();
      List<Integer> b = new ArrayList<>();
      long cA = 0, cB = 0;
      String last = "";
      while (n-- > 0) {
        int curr = scanner.nextInt();
        if (curr > 0) {
          a.add(curr);
          cA += curr;
          last = "first";
        } else {
          b.add(Math.abs(curr));
          last = "second";
          cB += Math.abs(curr);
        }
      }
      if (cA == cB) {
        if (a.size() <= b.size()) {
          for (int i = 0; i < a.size(); i++) {
            if (b.get(i) > a.get(i)) {
              out.println("second");
              return;
            } else if (a.get(i) > b.get(i)) {
              out.println("first");
              return;
            }
          }
          out.println(last);
        } else {
          for (int i = 0; i < b.size(); i++) {
            if (a.get(i) > b.get(i)) {
              out.println("first");
              return;
            } else if (b.get(i) > a.get(i)) {
              out.println("second");
              return;
            }
          }
          out.println(last);
        }
      } else {
        out.println(cA > cB ? "first" : "second");
      }

    }

  }
}
