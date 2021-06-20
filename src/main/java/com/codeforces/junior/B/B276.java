package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.StringTokenizer;

public class B276 {

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

  class Pair {

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

  static boolean isPalin(HashMap<Character, Integer> map) {
    int oddCount = 0;
    for (int val : map.values()) {
      if (val % 2 != 0) {
        oddCount++;
      }
    }
    return oddCount == 1 || oddCount == 0;
  }

  public static void main(String args[]) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      FastScanner scanner = new FastScanner();
      String str = scanner.next();
      HashMap<Character, Integer> map = new HashMap<>();
      for (char c : str.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
      }
      int turn = 0;
      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
      while (true) {
        if (isPalin(map)) {
          out.println(turn == 0 ? "First" : "Second");
          return;
        }
        boolean found = false;
        for (char c : lowerCaseLetters.toCharArray()) {
          if (map.containsKey(c)) {
            map.put(c, map.get(c) - 1);
            if (map.get(c) <= 0) {
              map.remove(c);
            }
            if (isPalin(map)) {
              map.put(c, map.getOrDefault(c, 0) + 1);
            } else {
              found = true;
              break;
            }
          }
        }
        if (!found) {
          break;
        }
        turn = (turn == 0 ? 1 : 0);
      }
      out.println(turn == 0 ? "Second" : "First");
    }
  }
}
