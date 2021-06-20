package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B405 {

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

  private static void buildGraph(HashMap<Integer, Set<Integer>> map, int first, int second) {
    Set<Integer> set = map.getOrDefault(first, new HashSet<>());
    set.add(second);
    map.put(first, set);
    set = map.getOrDefault(second, new HashSet<>());
    set.add(first);
    map.put(second, set);
  }

  static long vertices = 0, edges = 0;

  public static void main(String args[]) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      FastScanner scanner = new FastScanner();

      int n = scanner.nextInt();
      int m = scanner.nextInt();
      HashMap<Integer, Set<Integer>> map = new HashMap<>();
      Set<Integer> set = new HashSet<>();
      while (m-- > 0) {
        int first = scanner.nextInt();
        set.add(first);
        int second = scanner.nextInt();
        buildGraph(map, first, second);
      }

      HashSet<Integer> visited = new HashSet<>();
      for (int i = 1; i <= n; i++) {
        if (!visited.contains(i)) {
          vertices = 0;
          edges = 0;
          dfs(i, map, visited);
          if (edges/2 != vertices * (vertices - 1) / 2) {
            out.println("NO");
            return;
          }
        }
      }
      out.println("YES");
    }
  }

  private static void dfs(int curr, HashMap<Integer, Set<Integer>> map, HashSet<Integer> visited) {
    if (!visited.contains(curr)) {
      visited.add(curr);
      vertices++;
      if (map.containsKey(curr)) {
        for (int child : map.get(curr)) {
          edges++;
          dfs(child, map, visited);
        }
      }
    }
  }


}
