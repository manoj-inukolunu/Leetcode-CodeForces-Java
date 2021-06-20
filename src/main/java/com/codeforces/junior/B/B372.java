package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B372 {

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
    StringBuffer b = new StringBuffer(scanner.next());
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < 26 && i < b.length(); i++) {
      map.put(b.charAt(i), map.getOrDefault(b.charAt(i), 0) + 1);
    }
    if (valid(map)) {
      String str = getString(b, 0, 26, map);
      out.println(str);
      out.close();
      return;
    } else {
      for (int i = 1; i + 26 <= b.length(); i++) {
        char c1 = b.charAt(i - 1);
        char c2 = b.charAt(i + 25);
        map.put(c1, map.get(c1) - 1);
        map.put(c2, map.getOrDefault(c2, 0) + 1);
        if (map.get(c1) <= 0) {
          map.remove(c1);
        }
        if (valid(map)) {
          out.println(getString(b, i, i + 26, map));
          out.close();
          return;
        }
      }
    }
    out.println("-1");
    out.close();
  }

  private static String getString(StringBuffer str, int start, int end, HashMap<Character, Integer> map) {
    for (int i = start; i < end; i++) {
      if (str.charAt(i) == '?') {
        for (char ch : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
          if (!map.containsKey(ch)) {
            str.setCharAt(i, ch);
            map.put(ch, 1);
            break;
          }
        }
      }
    }
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '?') {
        str.setCharAt(i, 'A');
      }
    }
    return str.toString();
  }

  private static boolean valid(HashMap<Character, Integer> map) {
    if (map.containsKey('?')) {
      return map.get('?') + map.size() - 1 == 26;
    } else {
      return map.size() == 26;
    }
  }
}
