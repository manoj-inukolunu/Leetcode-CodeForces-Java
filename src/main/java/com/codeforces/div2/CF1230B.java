package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF1230B {

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

  public static void main(String args[]) {
    FastScanner f = new FastScanner();
    int len = f.nextInt();

    int k = f.nextInt();
    if (len == 1 && k > 0) {
      System.out.println("0");
      return;
    }
    String str = f.next();
    StringBuffer buffer = new StringBuffer(str);
    if (k == 0) {
      System.out.println(buffer.toString());
      return;
    }
    if (buffer.charAt(0) == '1') {
      int count = 0, i = 1;
      while (count < k && i < buffer.length()) {
        if (buffer.charAt(i) != '0') {
          buffer.setCharAt(i, '0');
          count++;
        }
        i++;
      }
    } else {
      buffer.setCharAt(0, '1');
      int count = 1, i = 1;
      while (count < k && i < buffer.length()) {
        if (buffer.charAt(i) != '0') {
          buffer.setCharAt(i, '0');
          count++;
        }
        i++;
      }
    }
    System.out.println(buffer.toString());
  }

}
