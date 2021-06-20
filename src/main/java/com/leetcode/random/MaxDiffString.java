package com.leetcode.random;

public class MaxDiffString {

  public int maxDiff(int num) {
    String str = num + "";
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int k = 0; k < 2; k++) {
      for (int i = 0; i <= 9; i++) {
        for (int j = 0; j <= 9; j++) {
          str = str.replaceAll(i + "", j + "");
          if (str.charAt(0) != '0') {
            min = Math.min(Integer.parseInt(str), min);
            max = Math.max(Integer.parseInt(str), max);
          }
          str = num + "";
        }
      }
    }
    return max - min;
  }

  public static void main(String args[]) {
    MaxDiffString m = new MaxDiffString();
    System.out.println(m.maxDiff((int) Math.pow(10, 8)));
  }

}
