package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BPolyCarp {

  static class FS {

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
    FS f = new FS();
    int len = f.nextInt();
    String str = f.next();
    List<Integer> list = new ArrayList<>();
    list.add(0);
    for (int i = 0; i < str.length(); i++) {
      if (Character.isUpperCase(str.charAt(i))) {
        list.add(i + 1);
      }
    }

    list.add(str.length());
    int max = Integer.MIN_VALUE;
    for (int i = 0; i + 1 < list.size(); i++) {
      HashSet<Character> set = new HashSet<>();
      for (int j = list.get(i); j < list.get(i + 1); j++) {
        if (!Character.isUpperCase(str.charAt(j))) {
          set.add(str.charAt(j));
        }
      }
      max = Math.max(max, set.size());
    }

    System.out.println(max);
  }

}
