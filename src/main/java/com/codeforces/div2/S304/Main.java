package com.codeforces.div2.S304;


import java.util.Scanner;

public class Main {

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] splits = line.split(" ");
      long ans = 0L;
      long k = Long.parseLong(splits[0]);
      long n = Long.parseLong(splits[1]);
      long w = Long.parseLong(splits[2]);
      for (int i = 1; i <= w; i++) {
        ans += (i * k);
      }
      if (ans <= n) {
        System.out.println(0);
      } else {
        System.out.println(ans - n);
      }
    }
  }
}
