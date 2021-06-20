package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BPresident {

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

  public static void main(String args[]) throws IOException {
    FastScanner f = new FastScanner();
    int rows = f.nextInt(), col = f.nextInt();
    char ch = f.next().charAt(0);
    List<String> list = new ArrayList<>();
    while (rows-- > 0) {
      list.add(f.next());
    }

    HashSet<Character> set = new HashSet<>();
    for (int i = 0; i < list.size(); i++) {
      String str = list.get(i);
      for (int j = 0; j < str.length(); j++) {
        char curr = str.charAt(j);
        if (curr == ch) {
          if (j - 1 >= 0 && str.charAt(j - 1) != ch) {
            set.add(str.charAt(j - 1));
          }
          if (j + 1 < str.length() && str.charAt(j + 1) != ch) {
            set.add(str.charAt(j + 1));
          }
          if (i - 1 >= 0 && list.get(i - 1).charAt(j) != ch) {
            set.add(list.get(i - 1).charAt(j));
          }
          if (i + 1 < list.size() && list.get(i + 1).charAt(j) != ch) {
            set.add(list.get(i + 1).charAt(j));
          }
        }
      }
    }
    int count = 0;
    for (char c : set) {
      if (c != ' ' && c != '.') {
        count++;
      }
    }
    System.out.println(count);

  }

}
