package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B253 {

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

  public static void main(String args[]) throws Exception {
    String inLocal = "/Users/manoji/Projects/ManojExperiments/JLox/src/main/java/com/codeforces/junior/B/input.txt";
    String in = "input.txt";
    String outLocal = "/Users/manoji/Projects/ManojExperiments/JLox/src/main/java/com/codeforces/junior/B/output.txt";
    String out = "output.txt";
    BufferedReader reader = new BufferedReader(new FileReader(in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(out)));
    int len = Integer.parseInt(reader.readLine());
    Integer[] arr = new Integer[len];
    String[] split = reader.readLine().split(" ");
    for (int i = 0; i < split.length; i++) {
      arr[i] = Integer.parseInt(split[i]);
    }
    Arrays.sort(arr);
    int low = 0, high = len - 2, ans = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (possible(mid, arr)) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    pw.println(ans);
    pw.close();
  }

  private static boolean possible(int mid, Integer[] arr) {
    int len = arr.length - mid;
    boolean ret = (2 * arr[0] >= arr[len - 1]);
    if (ret) {
      return ret;
    }
    for (int i = 1; i + len <= arr.length; i++) {
      ret = ret || (2 * arr[i] >= arr[i + len - 1]);
      if (ret) {
        return ret;
      }
    }
    return ret;
  }

}
