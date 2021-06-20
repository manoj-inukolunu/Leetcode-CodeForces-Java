package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.StringTokenizer;

public class B371 {

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
    int c;

    public Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    public Pair(int a, int b, int c) {
      this.first = a;
      this.second = b;
      this.c = c;
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
      int a = scanner.nextInt();
      int b = scanner.nextInt();
      Queue<Pair> queue = new LinkedList<>();
      queue.add(new Pair(a, b, 0));
      HashSet<Pair> visited = new HashSet<>();
      while (!queue.isEmpty()) {
        Pair curr = queue.poll();
        if (curr.first == curr.second) {
          out.println(curr.c);
          return;
        }
        if (!visited.contains(curr)) {
          visited.add(curr);
          if (curr.first % 2 == 0) {
            queue.add(new Pair(curr.first / 2, curr.second, curr.c + 1));
          }
          if (curr.second % 2 == 0) {
            queue.add(new Pair(curr.first, curr.second / 2, curr.c + 1));
          }
          if (curr.first % 3 == 0) {
            int val = curr.first / 3;
            queue.add(new Pair(curr.first - (2 * val), curr.second, curr.c + 1));
          }
          if (curr.second % 3 == 0) {
            int val = curr.second / 3;
            queue.add(new Pair(curr.first, curr.second - (2 * val), curr.c + 1));
          }
          if (curr.first % 5 == 0) {
            int val = curr.first / 5;
            queue.add(new Pair(curr.first - (4 * val), curr.second, curr.c + 1));
          }
          if (curr.second % 5 == 0) {
            int val = curr.second / 5;
            queue.add(new Pair(curr.first, curr.second - (4 * val), curr.c + 1));
          }
        }
      }
      out.println(-1);
    }

  }
}
