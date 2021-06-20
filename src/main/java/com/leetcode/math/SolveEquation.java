package com.leetcode.math;

import java.util.Scanner;

public class SolveEquation {

  public String solveEquation(String equation) {
    String[] splits = equation.split("=");
    int[] lefts = process(splits[0]);
    int[] rights = process(splits[1]);
    int left = lefts[0] - rights[0];
    int right = rights[1] - lefts[1];
    if (left != 0) {
      return "x=" + (right / left);
    } else {
      if (right == 0) {
        return "Infinite solutions";
      } else {
        return "No solution";
      }
    }
  }

  private int[] process(String data) {
    Scanner sc = new Scanner(data).useDelimiter("(?=[-+])");
    int with = 0, withOut = 0;
    while (sc.hasNext()) {
      String next = sc.next();
      if (next.contains("x")) {
        next = next.replaceAll("x", "");
        if (next.equals("-")) {
          next = "-1";
        } else if (next.equals("+")) {
          next = "1";
        }
        if (next.isEmpty()) {
          with += 1;
        } else {
          next = next.replaceAll("\\+", "");
          with += Integer.parseInt(next.isEmpty() ? "1" : next);
        }
      } else {
        next = next.replaceAll("\\+", "");
        withOut += (Integer.parseInt(next));
      }
    }
    return new int[]{with, withOut};
  }

  public static void main(String args[]) {
    SolveEquation s = new SolveEquation();
    System.out.println(s.solveEquation("-x=-1"));
  }

}
