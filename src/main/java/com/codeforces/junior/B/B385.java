package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B385 {

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

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    String str = scanner.next();
    StringBuffer b = new StringBuffer(str);
    int i = 0;
    List<Integer> list = new ArrayList<>();
    while (i < b.length()) {
      int idx = b.indexOf("bear", i);
      if (idx >= 0) {
        list.add(idx);
        i = idx + 4;
      } else {
        break;
      }
    }
    long count = 0;
    int len = b.length();
    for (int j = 0; j < list.size(); j++) {
      int leftChars = len - (list.get(j) + 3);
      int numChars;
      if (j != 0) {
        numChars = list.get(j) - list.get(j - 1);
      } else {
        numChars = list.get(j) + 1;
      }
      count += ((long) numChars * leftChars);
    }
    System.out.println(count);
  }
}
