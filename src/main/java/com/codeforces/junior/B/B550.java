package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B550 {

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

  static int count = 0;

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    int n = scanner.nextInt();
    int l = scanner.nextInt();
    int r = scanner.nextInt();
    int x = scanner.nextInt();
    int[] arr = scanner.readArray(n);
    Arrays.sort(arr);
    dfs(0, l, r, 0, x, arr, new ArrayList<>());
    out.println(count);
    out.close();
  }

  private static void dfs(int idx, int l, int r, int sum, int x, int[] arr, ArrayList<Integer> list) {
    if (idx >= arr.length) {
      if (sum >= l && sum <= r && list.get(list.size() - 1) - list.get(0) >= x) {
        count++;
      }
      return;
    }
    if (sum <= r) {
      list.add(arr[idx]);
      dfs(idx + 1, l, r, sum + arr[idx], x, arr, list);
      list.remove(list.size() - 1);
      dfs(idx + 1, l, r, sum, x, arr, list);
    }
  }
}
