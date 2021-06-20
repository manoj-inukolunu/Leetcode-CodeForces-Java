package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.StringTokenizer;

public class B682 {

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
      int len = scanner.nextInt();
      int[] arr = scanner.readArray(len);
      TreeMap<Integer, Integer> map = new TreeMap<>();
      for (int num : arr) {
        map.put(num, map.getOrDefault(num, 0) + 1);
      }
      int max = map.lastKey();
      for (int i = 1; i <= max; i++) {
        if (map.containsKey(i)) {
          map.put(i, map.get(i) - 1);
          if (map.get(i) <= 0) {
            map.remove(i);
          }
        } else {
          Integer higher = map.higherKey(i);
          if (higher == null) {
            out.println(i);
            return;
          } else {
            map.put(higher, map.get(higher) - 1);
            if (map.get(higher) <= 0) {
              map.remove(higher);
            }
          }
        }
      }
      out.println(max + 1);
    }

  }
}
