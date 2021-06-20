package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B27 {

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
    int[] arr = f.readArray(len);

    int i = 0;
    Stack<int[]> stack = new Stack<>();
    while (i < arr.length) {
      int curr = arr[i];
      if (stack.isEmpty() || stack.peek()[1] <= curr) {
        stack.push(new int[]{i, curr});
      } else {
        int[] diff = stack.pop();
        if (!stack.isEmpty()) {
          System.out.println(3);
          System.out.println((stack.peek()[0] + 1) + " " + (diff[0] + 1) + " " + (i + 1));
          return;
        } else {
          stack.push(new int[]{i, curr});
        }
      }
      i++;
    }
    stack.clear();
    i = 0;
    while (i < arr.length) {
      int curr = arr[i];
      if (stack.isEmpty() || stack.peek()[1] >= curr) {
        stack.push(new int[]{i, curr});
      } else {
        int[] diff = stack.pop();
        if (!stack.isEmpty()) {
          System.out.println(3);
          System.out.println((stack.peek()[0] + 1) + " " + (diff[0] + 1) + " " + (i + 1));
          return;
        } else {
          stack.push(new int[]{i, curr});
        }
      }
      i++;
    }
    System.out.println(0);
  }
}


