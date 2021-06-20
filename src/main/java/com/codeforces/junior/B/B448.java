package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class B448 {

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
    PrintWriter out = new PrintWriter(System.out);
    String s = scanner.next();
    String t = scanner.next();
    if (s.length() == t.length() && chars(s).equals(chars(t))) {
      out.println("array");
    } else if (s.length() < t.length()) {
      out.println("need tree");
      out.close();
    } else if (s.indexOf(t) > 0 || subseq(t, s, 0, 0)) {
      out.println("automaton");
    } else {
      HashMap<Character, Integer> sMap = chars(s);
      for (Entry<Character, Integer> c : chars(t).entrySet()) {
        if (!(sMap.containsKey(c.getKey()) && sMap.get(c.getKey()) >= c.getValue())) {
          out.println("need tree");
          out.close();
          return;
        }
      }
      out.println("both");
    }
    out.close();
  }

  private static boolean subseq(String t, String s, int it, int is) {
    if (it >= t.length()) {
      return true;
    }
    if (is >= s.length()) {
      return false;
    }
    if (t.charAt(it) == s.charAt(is)) {
      return subseq(t, s, it + 1, is + 1);
    } else {
      return subseq(t, s, it, is + 1);
    }
  }

  private static HashMap<Character, Integer> chars(String str) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (char c : str.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map;
  }
}
