package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BSort {

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


    Integer[] readArray(int n) {
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
    FastScanner f = new FastScanner();
    int len = f.nextInt();
    Integer[] arr = f.readArray(len);
    Integer[] temp = Arrays.copyOf(arr, arr.length);
    Arrays.sort(arr);
    int begin = -1, end = -1;
    for (int i = 0; i < arr.length; i++) {
      if (!arr[i].equals(temp[i])) {
        begin = i;
        break;
      }
    }
    for (int j = arr.length - 1; j >= 0; j--) {
      if (!arr[j].equals(temp[j])) {
        end = j;
        break;
      }
    }
    if (begin == -1 && end == -1) {
      System.out.println("yes");
      System.out.print(1 + " " + 1);
      return;
    }
    reverse(temp, begin, end);
    for (int i = 1; i < temp.length; i++) {
      if (temp[i] < temp[i - 1]) {
        System.out.println("no");
        return;
      }
    }

    System.out.println("yes");
    System.out.println((begin + 1) + " " + (end + 1));

  }

  public static void reverse(Integer[] arr, int l, int r) {
    int d = (r - l + 1) / 2;
    for (int i = 0; i < d; i++) {
      int t = arr[l + i];
      arr[l + i] = arr[r - i];
      arr[r - i] = t;
    }
    // print array here
  }

}
