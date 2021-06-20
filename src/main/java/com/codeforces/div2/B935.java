package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B935 {

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
    int n = f.nextInt();
    String str = f.next();
    int x = 0, y = 0;
    int count = 0;
    int prevX, prevY;
    for (int i = 0; i < str.length(); i++) {
      prevX = x;
      prevY = y;
      if (str.charAt(i) == 'U') {
        y++;
      } else {
        x++;
      }
      if (x == y && i + 1 < str.length()) {
        char next = str.charAt(i + 1);
        int nextX = x, nextY = y;
        if (next == 'U') {
          nextY++;
        } else {
          nextX++;
        }
        if ((prevX == x - 1 && nextX == x + 1) || (prevY == y - 1 && nextY == y + 1)) {
          count++;
        }
      }
    }
    System.out.println(count);
  }

}
