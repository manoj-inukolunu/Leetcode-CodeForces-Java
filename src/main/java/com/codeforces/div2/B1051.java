package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B1051 {

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

    long nextLong() {
      return Long.parseLong(next());
    }
  }

  static List<Integer> list = new ArrayList<>();

  private static void gen(String val) {
    if (!val.isEmpty() && Long.parseLong(val) >= Math.pow(10, 9)) {
      return;
    }
    if (!val.isEmpty()) {
      list.add(Integer.parseInt(val));
    }
    gen(val + "4");
    gen(val + "7");
  }

  public static void main(String args[]) {
    FastScanner f = new FastScanner();
    gen("");
    Collections.sort(list);
    int n = f.nextInt();
    for (int i = 0; i < list.size(); i++) {
      if (n == list.get(i)) {
        System.out.println(i + 1);
        break;
      }
    }
  }


}
