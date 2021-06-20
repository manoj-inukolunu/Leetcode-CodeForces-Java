package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.StringTokenizer;

public class B580 {

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
      int d = scanner.nextInt();
      List<Pair> list = new ArrayList<>();
      while (n-- > 0) {
        list.add(new Pair(scanner.nextInt(), scanner.nextInt()));
      }
      list.sort(Comparator.comparingInt(o -> o.first));

      long[] pre = new long[list.size()];
      pre[0] = list.get(0).second;
      for (int i = 1; i < list.size(); i++) {
        pre[i] = pre[i - 1] + list.get(i).second;
      }
      Long max = Long.MIN_VALUE;
      for (int i = 0; i < list.size(); i++) {
        int idx = getIdx(list, i, list.get(i).first + d);
        if (idx != -1) {
          max = Math.max(max, getSum(pre, i, idx));
        }
      }
      out.println(max);
    }
  }

  static int getIdx(List<Pair> list, int start, int num) {
    int low = start, high = list.size() - 1;
    int ret = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (list.get(mid).first < num) {
        ret = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return ret;
  }

  static long getSum(long[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }
}
