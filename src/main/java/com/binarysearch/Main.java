package com.binarysearch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

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
    Scanner scanner = new Scanner(System.in);
    String first = scanner.nextLine();
    String second = scanner.nextLine();
    String str = scanner.nextLine();

    HashMap<Character, Character> map = new HashMap<>();
    for (int i = 0; i < first.length(); i++) {
      map.put(first.charAt(i), second.charAt(i));
    }

    StringBuffer buffer = new StringBuffer();

    for (int i = 0; i < str.length(); i++) {
      Character ch = str.charAt(i);
      if (Character.isUpperCase(ch)) {
        buffer.append(Character.toUpperCase(map.get(Character.toLowerCase(ch))));
      } else if (Character.isDigit(ch)) {
        buffer.append(ch);
      } else {
        buffer.append(map.get(ch));
      }
    }
    System.out.println(buffer.toString());
  }
}
