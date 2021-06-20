package com.leetcode.random10.sixmonths.medium;

import java.util.Scanner;

public class Main {

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.isEmpty()) {
        break;
      }
      String[] split = line.split(" ");
      Long distance = Long.parseLong(split[0]);
      Long speed = Long.parseLong(split[2]);
      Long time = Long.parseLong(split[1]);
      String res = (distance / speed) <= time ? "Yes" : "No";
      System.out.println(res);
    }
  }


}