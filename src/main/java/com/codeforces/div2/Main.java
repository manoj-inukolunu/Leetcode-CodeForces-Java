package com.codeforces.div2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    String[] str = scanner.nextLine().split(" ");
    int numTestCases = Integer.parseInt(scanner.nextLine());
    Set<String> set = new HashSet<>();
    set.add(str[0]);
    set.add(str[1]);
    int count = 0;
    System.out.println();
    for (String s : set) {
      System.out.print(s + " ");
    }
    while (count < numTestCases) {
      str = scanner.nextLine().split(" ");
      if (set.contains(str[0])) {
        set.remove(str[0]);
        set.add(str[1]);
      } else {
        set.remove(str[1]);
        set.add(str[0]);
      }
      System.out.println();
      for (String s : set) {
        System.out.print(s + " ");
      }
      count++;
    }
  }

}
