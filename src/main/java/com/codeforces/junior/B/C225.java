package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C225 {

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
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int x = scanner.nextInt();
    int y = scanner.nextInt();
    int[][] counts = new int[m][2];
    while (n-- > 0) {
      String str = scanner.next();
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == '.') {
          counts[i][0]++;
        } else {
          counts[i][1]++;
        }
      }
    }
    int[][] pre = new int[counts.length][2];
    pre[0][0] = counts[0][0];
    pre[0][1] = counts[0][1];
    for (int i = 1; i < pre.length; i++) {
      pre[i][0] = pre[i - 1][0] + counts[i][0];
      pre[i][1] = pre[i - 1][1] + counts[i][1];
    }
    System.out.println(Math.min(process(pre, 0, x, y, 0), process(pre, 0, x, y, 1)));
  }

  static int getSum(int[][] pre, int start, int end, int ch) {
    if (start > 0) {
      return pre[end][ch] - pre[start - 1][ch];
    }
    return pre[end][ch];
  }

  private static int process(int[][] pre, int start, int x, int y, int ch) {
    if (start >= pre.length) {
      return 0;
    }
    if(start+x>=pre.length){
      return Integer.MAX_VALUE;
    }

    int count = Integer.MAX_VALUE;
    for (int width = x; width <= y; width++) {
      if (start + width < pre.length) {
        int sum = getSum(pre, start, start + width - 1, ch == 0 ? 1 : 0);
        int val = process(pre, start + width, x, y, ch == 0 ? 1 : 0);
        if (val != Integer.MAX_VALUE) {
          count = Math.min(count, val + sum);
        }
      }
    }
    return count;
  }


}
