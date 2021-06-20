package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class B445 {

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
    int m = scanner.nextInt();
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    while (m-- > 0) {
      int first = scanner.nextInt();
      int second = scanner.nextInt();
      Set<Integer> set = map.getOrDefault(first, new HashSet<>());
      set.add(second);
      map.put(first, set);
      set = map.getOrDefault(second, new HashSet<>());
      set.add(first);
      map.put(second, set);
    }
    Set<Integer> visited = new HashSet<>();
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      if (!visited.contains(i)) {
        int size = visited.size();
        dfs(i, map, visited);
        list.add(visited.size() - size);
      }
    }
    list.sort(Collections.reverseOrder());
    System.out.println(list);
    long count = 1;
    for (Integer compSize : list) {
      count *= Math.pow(2, compSize - 1);
    }

    System.out.println(count);
  }

  private static void dfs(int node, HashMap<Integer, Set<Integer>> map, Set<Integer> visited) {
    if (!visited.contains(node)) {
      visited.add(node);
      if (map.containsKey(node)) {
        for (int child : map.get(node)) {
          dfs(child, map, visited);
        }
      }
    }
  }
}
