package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B152 {

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

    double nextdouble() {
      return Double.parseDouble(next());
    }
  }

  static boolean inside(double x, double y, int mX, int mY) {
    return x > 0 && y > 0 && x <= mX && y <= mY;
  }

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    double currX = scanner.nextInt();
    double currY = scanner.nextInt();
    int k = scanner.nextInt();
    long steps = 0;
    while (k-- > 0) {
      int dx = scanner.nextInt();
      int dy = scanner.nextInt();

      double maxSteps = getSteps(currX, currY, n, m, dx, dy);

      currX += maxSteps * dx;
      currY += maxSteps * dy;
      steps += maxSteps;
    }
    System.out.println(steps);

  }

  private static double getSteps(double currX, double currY, int n, int m, double dx, double dy) {
    int low = 0, high = (int) 10e9, ans = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (inside(currX + (double) mid * dx, currY + (double) mid * dy, n, m)) {
        low = mid + 1;
        ans = mid;
      } else {
        high = mid - 1;
      }
    }
    return ans;
  }
}

