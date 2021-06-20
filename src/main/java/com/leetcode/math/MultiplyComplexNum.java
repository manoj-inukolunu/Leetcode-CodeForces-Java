package com.leetcode.math;

public class MultiplyComplexNum {

  public String complexNumberMultiply(String a, String b) {
    String[] first = a.split("\\+");
    String[] second = b.split("\\+");

    int constant = Integer.parseInt(first[0]) * Integer.parseInt(second[0]);
    int constantI = Integer.parseInt(first[0]) * Integer.parseInt(second[1].substring(0, second[1].indexOf('i')))
        + Integer.parseInt(first[1].substring(0, first[1].indexOf('i'))) * Integer.parseInt(second[0]);
    int iSquare = Integer.parseInt(first[1].substring(0, first[1].indexOf('i'))) * Integer.parseInt(second[1].substring(0, second[1].indexOf('i')));

    int one = (constant - iSquare);

    return one + "+" + constantI + "i";
  }

  public static void main(String args[]) {
    MultiplyComplexNum m = new MultiplyComplexNum();
    System.out.println(m.complexNumberMultiply("1+-1i", "1+-1i"));
  }

}
