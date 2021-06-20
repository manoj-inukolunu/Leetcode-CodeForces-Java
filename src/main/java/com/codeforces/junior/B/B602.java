package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class B602 {

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
  }

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    int n = scanner.nextInt();
    int[] arr = scanner.readArray(n);
    TreeMap<Integer, Integer> map = new TreeMap<>();
    int start = 0, end = 0, ans = 1;
    while (end < arr.length) {
      if (map.isEmpty()) {
        map.put(arr[end++], 1);
      } else {
        int min = map.firstKey();
        int max = map.lastKey();
        min = Math.min(min, arr[end]);
        max = Math.max(max, arr[end]);
        if (max - min <= 1) {
          map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);
          ans = Math.max(ans, end - start + 1);
          end++;
        } else {
          map.put(arr[start], map.getOrDefault(arr[start], 0) - 1);
          if (map.get(arr[start]) <= 0) {
            map.remove(arr[start]);
          }
          start++;
        }
      }
    }
    out.println(ans);
    out.close();
  }
}
