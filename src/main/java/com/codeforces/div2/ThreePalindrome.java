package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ThreePalindrome {

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
    FastScanner fastScanner = new FastScanner();
    int len = fastScanner.nextInt();
    if (len == 1) {
      System.out.println("a");
    } else if (len == 2) {
      System.out.println("ab");
    } else if (len == 3) {
      System.out.println("abb");
    } else {
      StringBuffer buffer = new StringBuffer();
      buffer.append("abb");
      for (int i = 3; i < len; i++) {
        if (buffer.length() >= 3) {
          if (buffer.charAt(buffer.length() - 2) == 'a') {
            buffer.append('b');
          } else {
            buffer.append('a');
          }
        } else {
          buffer.append("a");
        }
      }
      System.out.println(buffer.toString());
     /* System.out.println(buffer.toString().length());
      for (int i = 0; i + 3 < buffer.length(); i++) {
        if (isPalin(buffer.substring(i, i + 3))) {
          System.out.println("Fail");
        }
      }*/
    }
  }

  public static boolean isPalin(String str) {
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

}
